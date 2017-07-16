package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonName implements Parcelable {

    private final String title;
    private final String first;
    private final String last;

    public PersonName(String title,
                      String first,
                      String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    private PersonName(Parcel in) {
        this.title = in.readString();
        this.first = in.readString();
        this.last = in.readString();
    }

    public static final Parcelable.Creator<PersonName> CREATOR = new Parcelable.Creator<PersonName>() {
        @Override
        public PersonName createFromParcel(Parcel source) {
            return new PersonName(source);
        }

        @Override
        public PersonName[] newArray(int size) {
            return new PersonName[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.first);
        dest.writeString(this.last);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
}
