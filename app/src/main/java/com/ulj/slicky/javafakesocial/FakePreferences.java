package com.ulj.slicky.javafakesocial;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SlickyPC on 30.5.2017
 */
public class FakePreferences {

    private static final String TAG = FakePreferences.class.getCanonicalName();
    private static final String NOTIFY_ON_OFF = "notifyOnOff";
    private static final String NOTIFY_RANDOM = "notifyRandom";
    private static final String NOTIFY_DURATION = "notifyDuration";

    private final SharedPreferences prefs;

    public FakePreferences(Context context) {
        prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public boolean isNotifyOn() {
        return prefs.getBoolean(NOTIFY_ON_OFF, false);
    }

    public boolean isNotifyRandom() {
        return prefs.getBoolean(NOTIFY_RANDOM, false);
    }

    public int getNotifyDuration() {
        return prefs.getInt(NOTIFY_DURATION, 10);
    }

    public void setNotifyOn(boolean isOn) {
        prefs.edit().putBoolean(NOTIFY_ON_OFF, isOn).apply();
    }

    public void setNotifyRandom(boolean isRandom) {
        prefs.edit().putBoolean(NOTIFY_RANDOM, isRandom).apply();
    }

    public void setNotifyDuration(int duration) {
        prefs.edit().putInt(NOTIFY_DURATION, duration).apply();
    }

}
