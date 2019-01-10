package com.example.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerExercise extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_exercise);
    }


    public void onFingerExerciserButtonClick(View v) {
        TextView textView1 = findViewById(R.id.finger_exercise_text);
        TextView textView2 = findViewById(R.id.encourage_text);
        counter++;
        if(counter % 50 == 0) {
            textView2.setText("Oh my, you're amazing!!!");
        }
        else if(counter % 10 == 0) {
            textView2.setText("Keep going, I believe in you!!");
        }
        textView1.setText("Ooooh yeah! You have " + counter + " presses.");
    }

}
