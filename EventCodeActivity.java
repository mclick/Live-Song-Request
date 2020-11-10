package com.example.clikentertainment.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventCodeActivity extends Activity {

    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_code);

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText attemptET = findViewById(R.id.attempt);
                String attempt = attemptET.getText().toString();

                Intent intent = new Intent(EventCodeActivity.this, SongActivity.class);
                intent.putExtra("event", event.getName());
                intent.putExtra("attempt", attempt);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            //get IDs
            TextView eventName = findViewById(R.id.eventName);
            //set for display
            String eventTitle = (String) intent.getCharSequenceExtra("event");
            this.event = EventList.getEventList().findEvent(eventTitle);
            eventName.setText(event.getName());
        }
    }
}
