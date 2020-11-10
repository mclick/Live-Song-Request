package com.example.clikentertainment.live;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminCodeActivity extends Activity {

    private String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_code);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            //get IDs
            TextView eventNamee = (TextView) findViewById(R.id.eventNamee);
            //set for display
            eventTitle = (String) intent.getCharSequenceExtra("event");
            eventNamee.setText(eventTitle);
        }

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText attemptET = findViewById(R.id.attempt);
                String attempt = attemptET.getText().toString();

                Intent intent = new Intent(AdminCodeActivity.this, AdminActivity.class);
                intent.putExtra("event", eventTitle);
                intent.putExtra("attempt", attempt);
                startActivity(intent);
            }
        });
    }
}
