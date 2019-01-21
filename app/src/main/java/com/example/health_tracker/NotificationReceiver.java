package com.example.health_tracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    // Referenced: https://gist.github.com/BrandonSmith/6679223
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification n = intent.getParcelableExtra("notification");
        int id = intent.getIntExtra("notification_id", 0);
        notificationManager.notify(id, n);
    }
}
