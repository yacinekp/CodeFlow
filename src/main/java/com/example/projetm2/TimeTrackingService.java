package com.example.projetm2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class TimeTrackingService extends Service {

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
        if (intent != null && intent.hasExtra("username")) {
            username = intent.getStringExtra("username");
            startTime = SystemClock.elapsedRealtime();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (username != null && startTime > 0) {
            long endTime = SystemClock.elapsedRealtime();
            long sessionTime = (endTime - startTime) / 1000;
            db.addTime(username, sessionTime);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}