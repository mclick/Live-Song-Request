package com.example.clikentertainment.live;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class SongActivity extends ListActivity {

    private String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            eventTitle = (String) intent.getCharSequenceExtra("event");
            String attempt = (String) intent.getCharSequenceExtra("attempt");
            if (EventList.getEventList().findEvent(eventTitle).codeCheck(attempt)) {
                //continue
            } else {
                //end
                Toast.makeText(this, "Code Incorrect", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        setListAdapter(new SongAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addSong:
                Intent intent1 = new Intent(this, SongRequest.class);
                intent1.putExtra("event", eventTitle);
                this.startActivity(intent1);
                break;
            case R.id.admin:
                Intent intent2 = new Intent(this, AdminCodeActivity.class);
                intent2.putExtra("event", eventTitle);
                this.startActivity(intent2);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private class SongAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        private SongAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            EventList.getEventList().findEvent(eventTitle);
            return EventList.getEventList().findEvent(eventTitle).getSongList().getSongListCount();//songList.getCount();
        }

        @Override
        public Object getItem(int i) {
            return EventList.getEventList().findEvent(eventTitle).getSongList().getSong(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView (final int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (convertView == null) {
                if (inflater == null)
                    inflater = (LayoutInflater) SongActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_song, parent, false);
            }

            //Get Elements
            TextView songName = row.findViewById(R.id.title);
            TextView artistName = row.findViewById(R.id.artist);
            TextView vote = row.findViewById(R.id.votes);
            TextView notes = row.findViewById(R.id.songNotes);

            MyView up = row.findViewById(R.id.arrowUp);
            MyView down = row.findViewById(R.id.arrowDown);

            Song song = EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position);
            songName.setText(song.getSongName());
            artistName.setText(song.getArtistName());
            vote.setText(Integer.toString(song.getVoteCount()));
            notes.setText(song.getNotes());

            up.setUpColor(EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().getColorUp());
            down.setDownColor(EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().getColorDown());
            up.setArrowUp();
            down.setArrowDown();

            up.setOnClickListener(new MyView.OnClickListener() {
                public void onClick(View view) {
                    State.Answer nowState = EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().getUp();
                    if (State.Answer.UP.equals(nowState)) {
                        //Up button was clicked while arrow was already up
                        //Set button color to black
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.BLACK);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.BLACK);
                        //Set State to NONE
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.NONE);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.NONE);
                        //Decrease Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).downVote();
                        //Reimage
                        recreate();
                    } else if (State.Answer.DOWN.equals(nowState)) {
                        //Up button was clicked while the down button was clicked
                        //Set down button color to black
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.BLACK);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.BLACK);
                        //Set State Down to NONE
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.NONE);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.NONE);
                        //Increase Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).upVote();
                        //Reimage
                        recreate();
                    } else {
                        //Up button was clicked while no button was clicked
                        //Set up button color to red
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.RED);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.BLACK);
                        //Set state to UP
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.UP);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.UP);
                        //Increase Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).upVote();
                        //Reimage
                        recreate();
                    }
                }
            });

            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    State.Answer nowState = EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().getDown();
                    if (State.Answer.UP.equals(nowState)) {
                        //Down button was clicked while arrow was already up
                        //Set up button color to black
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.BLACK);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.BLACK);
                        //Set State to NONE
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.NONE);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.NONE);
                        //Decrease Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).downVote();
                        //Reimage
                        recreate();
                    } else if (State.Answer.DOWN.equals(nowState)) {
                        //Down button was clicked while the down button was clicked
                        //Set down button color to black
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.BLACK);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.BLACK);
                        //Set State Down to NONE
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.NONE);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.NONE);
                        //Increase Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).upVote();
                        //Reimage
                        recreate();
                    } else {
                        //Down button was clicked while no button was clicked
                        //Set down button color to red
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorDown(Color.RED);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setColorUp(Color.BLACK);
                        //Set state to UP
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setUp(State.Answer.DOWN);
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).getVote().setDown(State.Answer.DOWN);
                        //Increase Vote
                        EventList.getEventList().findEvent(eventTitle).getSongList().getSong(position).downVote();
                        //Reimage
                        recreate();
                    }
                }
            });

            //Return this view row
            return row;
        }
    }

}
