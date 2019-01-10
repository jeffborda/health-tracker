package com.example.health_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    // Image Carousel
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.exercise1, R.drawable.exercise2, R.drawable.exercise3};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Image Carousel
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void goToFingerExercise(View v) {
        Intent fingerExerciseIntent = new Intent(this, FingerExercise.class);
        // Next line show how you can pass a string (or something else) over to the next view
        //fingerExerciseIntent.putExtra("stringToShow", "You're at finger exercises.");
        startActivity(fingerExerciseIntent);
    }

    public void goToStopwatch(View v) {
        Intent stopwatchIntent = new Intent(this, StopWatch.class);
        startActivity(stopwatchIntent);
    }




}
