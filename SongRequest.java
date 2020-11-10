package com.example.clikentertainment.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class SongRequest extends Activity {

    String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_request);

        Button submitAndAgain = findViewById(R.id.submitAgain);
        submitAndAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText songName = findViewById(R.id.song);
                EditText artistName = findViewById(R.id.artist);
                EditText songNotes = findViewById(R.id.notes);

                EventList.getEventList().findEvent(eventTitle).getSongList().addSong(songName.getText().toString(), artistName.getText().toString(), songNotes.getText().toString());
                finish();
                startActivity(getIntent());
            }
        });

        Button submitAndFinish = findViewById(R.id.submitFinish);
        submitAndFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText songName = findViewById(R.id.song);
                EditText artistName = findViewById(R.id.artist);
                EditText songNotes = findViewById(R.id.notes);

                EventList.getEventList().findEvent(eventTitle).getSongList().addSong(songName.getText().toString(), artistName.getText().toString(), songNotes.getText().toString());
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            eventTitle = (String) intent.getCharSequenceExtra("event");
        }
    }
}
