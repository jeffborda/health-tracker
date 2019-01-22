package com.example.health_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerExercise extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_exercise);
        // Following is how you would get the extra
//        TextView textView = findViewById(R.layout.stringToShow);
//        Intent i = getIntent();
//        textView.setText(i.getExtra("stringToShow"));
    }


    public void onFingerExerciserButtonClick(View v) {
        TextView tapCountText = findViewById(R.id.finger_exercise_text);
        TextView encouragementText = findViewById(R.id.encourage_text);
        String displayCount;
        counter++;
        // Set the encouragement text based on click counter
        if(counter % 50 == 0) {
            encouragementText.setText(R.string.finger_exercise_encouragement_2);
        }
        else if(counter % 10 == 0) {
            encouragementText.setText(R.string.finger_exercise_encouragement1);
        }
        // Set click counter, and pluralize 'click' if more than one click
        if(counter < 2) {
            displayCount = getString(R.string.finger_exercise_tap_count_singular, counter);
        }
        else {
            displayCount = getString(R.string.finger_exercise_tap_count_plural, counter);
        }
        tapCountText.setText(displayCount);
    }

}
