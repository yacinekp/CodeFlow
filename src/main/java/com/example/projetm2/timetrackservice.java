package com.example.projetm2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;

public class timetrackservice extends Service {

    private static final long INACTIVITY_LIMIT_MS = 15000; // 15s for testing
    private static final String CHANNEL_ID = "inactivity_channel";

    private long lastActivityTime;
    private Handler inactivityHandler;
    private Runnable inactivityRunnable;

    private database_Handler db;
    private String username;

    @Override
    public void onCreate() {
        super.onCreate();

        db = new database_Handler(this);
        inactivityHandler = new Handler();

        createNotificationChannel();

        lastActivityTime = SystemClock.elapsedRealtime();

        inactivityRunnable = new Runnable() {
            @Override
            public void run() {
                long now = SystemClock.elapsedRealtime();

                if (now - lastActivityTime >= INACTIVITY_LIMIT_MS) {
                    sendInactivityNotification();
                }

                inactivityHandler.postDelayed(this, 5000);
            }
        };

        inactivityHandler.postDelayed(inactivityRunnable, 5000); // checks every 5s
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        username = intent.getStringExtra("username");

        lastActivityTime = SystemClock.elapsedRealtime();

        return START_STICKY;
    }

    // Called by Activities to reset inactivity timer
    public void updateUserActivity() {
        lastActivityTime = SystemClock.elapsedRealtime();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        inactivityHandler.removeCallbacks(inactivityRunnable);

        db.addTime(username, (SystemClock.elapsedRealtime() - lastActivityTime) / 1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // ---------- NOTIFICATION SYSTEM ---------- //

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Inactivity Alerts",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Notifies when user is inactive for too long");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void sendInactivityNotification() {

        Intent intent = new Intent(this, log_in.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Inactivité détectée")
                .setContentText("Vous êtes inactif depuis 15 secondes.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }
}
