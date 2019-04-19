package com.ulj.slicky.javafakesocial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.ulj.slicky.javafakesocial.model.person.Person;

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
        String[] words = string.split(" ");
        String[] capitalized = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            capitalized[i] = capitalize(words[i]);
        }
        return TextUtils.join(" ", capitalized);
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

    public static void shakeContext(Context context, View... views) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator == null) {
            return;
        }
        vibrator.vibrate(SHAKE_DURATION);
        for (View view : views) {
            shakeView(view, SHAKE_DURATION, SHAKE_COUNT);
        }
    }

    private static void shakeView(View view, int duration, int count) {
        RotateAnimation shake = new RotateAnimation(
                -3f, 3f, view.getWidth() / 2f, view.getHeight() / 2f
        );
        shake.setRepeatCount(count);
        shake.setRepeatMode(Animation.REVERSE);
        shake.setDuration(duration / count);

        view.startAnimation(shake);
    }

    public static void startShareActivity(Activity activity, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        activity.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public static void startBrowseActivity(Activity activity, String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(browserIntent);
    }

}
