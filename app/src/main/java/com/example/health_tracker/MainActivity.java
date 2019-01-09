package com.example.health_tracker;

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

    public int counter = 0;
    public long startTime = 0;
    public long nowMilis;
    public boolean isRunning = false;
    TimerTask task;

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

    public void onStopwatchStart(View v) {
        if(isRunning) {
            return;
        }
        isRunning = true;
        Timer stopwatch = new Timer();
        startTime = System.currentTimeMillis();

        int delay = 0; // Delay in milliseconds
        int period = 10; // Period in milliseconds


        task = new TimerTask() {
            @Override
            public void run() {
                TextView textView = findViewById(R.id.stopwatch_text);
                textView.setText(setStopwatch());
            }
        };
        stopwatch.scheduleAtFixedRate(task, delay, period);

    }

    public void onStopwatchStop(View v) {
        isRunning = false;
        task.cancel();
    }

    public void onStopwatchReset(View v) {
        if(isRunning) {
            task.cancel();
        }
        TextView textView = findViewById(R.id.stopwatch_text);
        textView.setText("0:00:00.000");

        if(isRunning) {
            Timer stopwatch = new Timer();
            startTime = System.currentTimeMillis();

            int delay = 0; // Delay in milliseconds
            int period = 10; // Period in milliseconds


            task = new TimerTask() {
                @Override
                public void run() {
                    TextView textView = findViewById(R.id.stopwatch_text);
                    textView.setText(setStopwatch());
                }
            };
            stopwatch.scheduleAtFixedRate(task, delay, period);
        }
    }

    public String setStopwatch() {
        long nowMilis = System.currentTimeMillis();
        long diff = nowMilis - startTime - (16*60*60*1000); // Time zone adjust
        Date date = new Date(diff);
        SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm:ss.SSS");
        return timeFormat.format(date);
    }




}
