package com.example.projetm2;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class timetrackservice extends Service {

    private String username;
    private database_Handler db;
    private Handler handler;
    private Runnable runnable;
    private long sessionStartTime;

    private static final long UPDATE_INTERVAL = 1000 * 60; // 1 minute

    @Override
    public void onCreate() {
        super.onCreate();
        db = new database_Handler(this);
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        username = intent.getStringExtra("username");
        if (username == null || username.isEmpty()) {
            stopSelf();
            return START_NOT_STICKY;
        }

        sessionStartTime = System.currentTimeMillis();

        runnable = new Runnable() {
            @Override
            public void run() {
                long now = System.currentTimeMillis();
                long elapsedSeconds = (now - sessionStartTime) / 1000;
                db.addTime(username, elapsedSeconds);
                db.updateLastActive(username);

                // reset start time for next interval
                sessionStartTime = now;

                // Schedule next update
                handler.postDelayed(this, UPDATE_INTERVAL);
            }
        };

        // Start the periodic task
        handler.postDelayed(runnable, UPDATE_INTERVAL);

        // Return START_STICKY if you want service to continue in background
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        Toast.makeText(this, "Session tracking stopped for " + username, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // We are not binding, so return null
        return null;
    }
}
