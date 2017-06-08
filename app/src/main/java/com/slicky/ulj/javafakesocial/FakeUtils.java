package com.slicky.ulj.javafakesocial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.slicky.ulj.javafakesocial.model.person.Person;

import java.util.Date;
import java.util.Locale;

/**
 * Created by SlickyPC on 24.5.2017
 */
@SuppressWarnings("unused")
public class FakeUtils {

    private static final int SHAKE_DURATION = 300;
    private static final int SHAKE_COUNT = 10;

    private static String capitalize(String string) {
        if (string.length() > 0 && Character.isLowerCase(string.charAt(0)))
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        else
            return string;
    }

    public static String capitalizeAll(String string) {
        String[] list = string.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String newString : list) {
            sb.append(capitalize(newString));
        }
        return sb.toString();
    }

    public static String getFullPersonName(Person person) {
        String name = person.getName().getFirst();
        String last = person.getName().getLast();
        return capitalizeAll(name) + " " + capitalizeAll(last);
    }

    public static String getFullPersonNameWithTitle(Person person) {
        String title = person.getName().getTitle();
        return capitalizeAll(title) + " " + getFullPersonName(person);
    }

    public static String getPersonInfo(Person person) {
        String city = person.getLocation().getCity();
        String country = getCountryFromCode(person.getNat());
        return capitalizeAll(city) + ", " + country;
    }

    public static CharSequence getFormattedWithTime(Date date) {
        return DateFormat.format("d. M. yyyy, hh:mm:ss", date);
    }

    public static CharSequence getFormatted(Date date) {
        return DateFormat.format("d. M. yyyy", date);
    }

    public static String getCountryFromCode(String nat) {
        return new Locale("", nat).getDisplayCountry(new Locale("en"));
    }

    public static void shake(Context context, View... views) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(SHAKE_DURATION);
        for (View view : views) {
            FakeUtils.shakeView(view, SHAKE_DURATION, SHAKE_COUNT);
        }
    }

    private static void shakeView(View view, int duration, int count) {
        RotateAnimation shake = new RotateAnimation(
                -3f, 3f,
                view.getWidth() / 2,
                view.getHeight() / 2
        );
        shake.setRepeatCount(count);
        shake.setRepeatMode(Animation.REVERSE);
        shake.setDuration(duration / count);

        view.startAnimation(shake);
    }

    public static void startBrowseActivity(Activity activity, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }
}
