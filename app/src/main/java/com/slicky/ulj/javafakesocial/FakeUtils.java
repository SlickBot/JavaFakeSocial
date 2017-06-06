package com.slicky.ulj.javafakesocial;

import android.content.Context;
import android.os.Vibrator;
import android.text.format.DateFormat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import com.slicky.ulj.javafakesocial.model.person.Person;

import java.util.Date;
import java.util.Locale;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * Created by SlickyPC on 24.5.2017
 */
public class FakeUtils {

    private static final int SHAKE_DURATION = 300;
    private static final int SHAKE_COUNT = 10;

    public static String getFullPersonName(Person person) {
        String name = person.getName().getFirst();
        String last = person.getName().getLast();
        return capitalize(name) + " " + capitalize(last);
    }

    public static String getFullPersonNameWithTitle(Person person) {
        String title = person.getName().getTitle();
        return capitalize(title) + ". " + getFullPersonName(person);
    }

    public static String getPersonInfo(Person person) {
        String city = person.getLocation().getCity();
        String country = getCountryFromCode(person.getNat());
        return capitalize(city) + ", " + country;
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
}
