package com.club.jalvara2.polarclub2.services;

import android.content.Intent;
import android.util.Log;

import com.club.jalvara2.polarclub2.task.HeartCounterTimer;

import java.util.Timer;

/**
 * Created by jalvara2 on 7/02/18.
 */

public class HeartEmulatorService extends HeartService {
    private static Timer mTimer = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mTimer == null) {
            mTimer = new Timer();
            long delay = 1000;
            long period = 1000;
            mTimer.scheduleAtFixedRate(new HeartCounterTimer(getApplicationContext()), delay, period);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        mTimer = null;
        super.onDestroy();
    }
}
