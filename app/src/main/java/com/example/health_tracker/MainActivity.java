package com.example.health_tracker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



    // Image Carousel
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.exercise1, R.drawable.exercise2, R.drawable.exercise3};

    // For notification setting
    private static int notificationId = 1;
    private static int NOTIFICATION_INTERVAL = 1000 * 60 * 60 * 2; // 2 hours


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

    public void goToLogin(View v) {
        Intent exerciseDiaryIntent = new Intent(this, Login.class);
        startActivity(exerciseDiaryIntent);
    }

    public void goToExerciseDiary(View v) {
        Intent exerciseDiaryIntent = new Intent(this, ExerciseDiary.class);
        startActivity(exerciseDiaryIntent);
    }

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

    // From lecture: Day 28 Part 1
    public void sendNotification(View v) {

        // From Android docs: https://developer.android.com/training/notify-user/channels#java
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_name), name, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this, getString(R.string.channel_name))
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Health Tracker")
                                .setContentText("Reminder to drink water. At least 4 cups per hour while exercising your fingers.")
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("Time to Drink Water"))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(notificationId++, builder.build());
            }
        }, NOTIFICATION_INTERVAL, NOTIFICATION_INTERVAL);
    }


}
