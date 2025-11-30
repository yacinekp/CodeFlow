package com.example.projetm2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class timetrackservice extends Service {

    private long startTime;
    private String username;
    private database_Handler db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new database_Handler(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        username = intent.getStringExtra("username");

        // Start timer
        startTime = SystemClock.elapsedRealtime();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        long endTime = SystemClock.elapsedRealtime();
        long sessionTime = endTime - startTime;  // milliseconds

        // Convert to seconds
        long seconds = sessionTime / 1000;

        db.addTime(username, seconds);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
