package com.club.jalvara2.polarclub2.services;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.club.jalvara2.polarclub2.utils.DailyHeart;

import static android.hardware.Sensor.TYPE_HEART_RATE;
import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

/**
 * Created by jalvara2 on 7/02/18.
 */

public class HeartService extends Service implements SensorEventListener {

    public static final String TAG = "HeartService";
    public static final String HEARTBEAT_COUNT_MESSAGE = "heartcount_message";
    public static final String HEARTBEAT_COUNT_VALUE = "heart_count_value";

    private SensorManager sensorManager;
    private Sensor countSensor;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        countSensor = sensorManager.getDefaultSensor(TYPE_HEART_RATE);
        boolean bool = sensorManager.registerListener(this, countSensor, SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == TYPE_HEART_RATE) {
            DailyHeart.updateHearbeat((int)event.values[0]);
            sendHeartbeatCountUpdate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        sendHeartbeatCountUpdate();
    }

    private void sendHeartbeatCountUpdate() {
        Intent intent = new Intent();
        intent.setAction(HEARTBEAT_COUNT_MESSAGE);
        intent.putExtra(HEARTBEAT_COUNT_VALUE, DailyHeart.getHearbeat());
        sendBroadcast(intent);
    }
}
