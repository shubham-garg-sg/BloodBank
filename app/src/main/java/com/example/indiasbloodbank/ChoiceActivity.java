package com.example.indiasbloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {

    private Button donate_blood;
    private Button seek_blood;

    private View.OnClickListener onDonateBloodListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), DetailsActivity.class));

        }
    };

    private View.OnClickListener onSeekBloodListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        donate_blood = findViewById(R.id.donate_blood);
        seek_blood = findViewById(R.id.seek_blood);

        donate_blood.setOnClickListener(onDonateBloodListener);
        seek_blood.setOnClickListener(onSeekBloodListener);
    }
}