package com.slicky.ulj.javafakesocial.activity.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;
import com.slicky.ulj.javafakesocial.FakePreferences;
import com.slicky.ulj.javafakesocial.R;
import com.slicky.ulj.javafakesocial.activity.content.ContentActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by SlickyPC on 30.5.2017
 */
public class NotifyingService extends IntentService {
    private static final int NOTIFY_ID = 0xDEAD_BEEF;

    private FakePreferences prefs;

    public NotifyingService() {
        super("NotifyingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new FakePreferences(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean isOn = prefs.isNotifyOn();
        boolean isRandom = prefs.isNotifyRandom();
        int duration = prefs.getNotifyDuration();

        if (isOn) {
            try {
                int sleepInSeconds = isRandom ? new Random().nextInt(60) : duration;
                TimeUnit.SECONDS.sleep(sleepInSeconds);
            } catch (InterruptedException ignored) {}
            displayNotification();
        }
    }

    private void displayNotification() {
        prefs.setNotifyOn(false);

        Intent callbackIntent = new Intent(getApplicationContext(), ContentActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, callbackIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_transparent)
                .setContentIntent(pendingIntent)
                .setContentTitle(getString(R.string.app_name))
                .setSubText("Time ran out!")
                .setContentText("You should open Java Fake Social again!")
                .setAutoCancel(true)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFY_ID, notification);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}
