package com.example.clikentertainment.live;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.app.ListActivity;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //addTestData();
    }

    private void addTestData() {
        EventList.getEventList().addEvent("test1", "test1", "test1");
        EventList.getEventList().addEvent("test2", "test2", "test2");
        EventList.getEventList().addEvent("test3", "test3", "test3");
        Event test1 = EventList.getEventList().findEvent("test1");
        test1.getSongList().addSong("Jailhouse Rock", "Elvis Presley", "My wedding song!");
        test1.getSongList().addSong("While My Guitar Gently Weeps", "The Beatles", "You have to play.");
        test1.getSongList().addSong("Back From Hell", "Run-D.M.C", "THIS ONE!");
        test1.getSongList().addSong("Bonzo’s Montreux", "Led Zeppelin", "This will make them go crazy");
        test1.getSongList().addSong("Little Miss Can’t Be Wrong", "Spin Doctors", "Love this song");
        test1.getSongList().addSong("Starfuckers, Inc.", "Nine Inch Nails", "Please!");
        test1.getSongList().addSong("Fat Lip", "Sum 41", "yes");
        test1.getSongList().addSong("A Little Piece of Heaven", "Avenged Sevenfold", "Not your typical song but very very very good");
        test1.getSongList().addSong("The Man Who Sold The World", "David Bowie", "");
        Event test2 = EventList.getEventList().findEvent("test2");
        test2.getSongList().addSong("A Little Piece of Heaven", "Avenged Sevenfold", "Not your typical song but very very very good");
        test2.getSongList().addSong("The Man Who Sold The World", "David Bowie", "");
    }

    @Override
    protected void onStart() {
        super.onStart();
        setListAdapter(new EventAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_menu, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addEvent:
                Intent intent1 = new Intent(this, AddEventActivity.class);
                this.startActivity(intent1);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private class EventAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private Context context;

        public EventAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            this.context=context;
        }

        @Override
        public int getCount() {
            return EventList.getEventList().getCount();
        }

        @Override
        public Object getItem(int i) {
            return EventList.getEventList();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (convertView == null) {
                if (inflater == null)
                    inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.event_list, parent, false);
            }

            //Get Elements
            Button button = row.findViewById(R.id.joinButton);

            //Get and Set Information to Elements
            final Event event = EventList.getEventList().getEvent(position);
            button.setText(event.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,EventCodeActivity.class);
                    intent.putExtra("event", event.getName());
                    context.startActivity(intent);
                }
            });

            //Return this view row
            return row;
        }
    }


}
