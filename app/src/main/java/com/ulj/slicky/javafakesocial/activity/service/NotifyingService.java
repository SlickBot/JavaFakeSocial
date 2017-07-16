package com.ulj.slicky.javafakesocial.activity.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.NotificationCompat;

import com.ulj.slicky.javafakesocial.FakePreferences;
import com.ulj.slicky.javafakesocial.R;
import com.ulj.slicky.javafakesocial.activity.content.ContentActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static android.os.Build.VERSION.SDK_INT;

/**
 * Created by SlickyPC on 30.5.2017
 */
public class NotifyingService extends IntentService {
    private static final int NOTIFY_ID = 0xDEAD_BEEF;
    private static final String CHANNEL_ID = "0xDEAD_BEEF";
    private static final String CHANNEL_NAME = "NotifyingChannel";

    private FakePreferences prefs;

    public NotifyingService() {
        super("NotifyingService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new FakePreferences(this);

        if (SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{500});

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (prefs.isNotifyOn()) {
            try {
                int sleepInSeconds = prefs.isNotifyRandom()
                        ? new Random().nextInt(60)
                        : prefs.getNotifyDuration();
                TimeUnit.SECONDS.sleep(sleepInSeconds);
            } catch (InterruptedException ignored) {
            }
            displayNotification();
        }
    }

    private void displayNotification() {
        prefs.setNotifyOn(false);

        Intent callbackIntent = new Intent(getApplicationContext(), ContentActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, callbackIntent, 0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentIntent(pendingIntent)
                .setContentTitle(getString(R.string.app_name))
                .setSubText("Time ran out!")
                .setContentText("You should open Java Fake Social again!")
                .setAutoCancel(true)
                .setChannelId(CHANNEL_ID)
                .build();
        manager.notify(NOTIFY_ID, notification);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}
