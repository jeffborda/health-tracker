package com.example.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatch extends AppCompatActivity {

    public long startTime = 0;
    public boolean isRunning = false;
    TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
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
