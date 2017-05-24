package com.slicky.ulj.javafakesocial;

import android.text.format.DateFormat;
import com.slicky.ulj.javafakesocial.model.person.Person;

import java.util.Date;
import java.util.Locale;

import static org.apache.commons.lang3.text.WordUtils.capitalize;

/**
 * Created by SlickyPC on 24.5.2017
 */
public class FakeUtils {

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
        String country = getCountry(person.getNat());
        return capitalize(city) + ", " + country;
    }

    public static CharSequence getFullDate(Date date) {
        return DateFormat.format("d. M. yyyy, hh:mm:ss", date);
    }

    public static CharSequence getDate(long d) {
        return DateFormat.format("d. M. yyyy", d);
    }

    public static String getCountry(String nat) {
        return new Locale("", nat).getDisplayCountry(new Locale("en"));
    }
}
