package com.club.jalvara2.polarclub2.task;

import android.content.Context;
import android.content.Intent;

import com.club.jalvara2.polarclub2.utils.DailyHeart;

import java.util.TimerTask;

import static com.club.jalvara2.polarclub2.services.HeartService.HEARTBEAT_COUNT_MESSAGE;
import static com.club.jalvara2.polarclub2.services.HeartService.HEARTBEAT_COUNT_VALUE;

/**
 * Created by jalvara2 on 7/02/18.
 */

public class HeartCounterTimer extends TimerTask {
    private static int heartbeatCount;
    private Context context;

    public HeartCounterTimer(Context context) {
        this.context = context;
        heartbeatCount = 50 + (int) (Math.random() * 200);
        DailyHeart.updateHearbeat(heartbeatCount);
    }

    @Override
    public void run() {
        heartbeatCount = 50 + (int) (Math.random() * 200);
        DailyHeart.updateHearbeat(heartbeatCount);
        Intent intent = new Intent();
        intent.setAction(HEARTBEAT_COUNT_MESSAGE);
        intent.putExtra(HEARTBEAT_COUNT_VALUE, DailyHeart.getHearbeat());
        context.sendBroadcast(intent);
    }
}
