package com.example.clikentertainment.live;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class AddEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Button createEvent = findViewById(R.id.button);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText eventName = findViewById(R.id.eventName);
                EditText eventCode = findViewById(R.id.eventCode);
                EditText adminCode = findViewById(R.id.adminCode);

                EventList.getEventList().addEvent(eventName.getText().toString(), eventCode.getText().toString(), adminCode.getText().toString());

                finish();
            }
        });
    }
}
