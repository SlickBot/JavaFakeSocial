package com.slicky.ulj.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonID implements Parcelable {

    private final String name;
    private final String value;

    public PersonID(String name,
                    String value) {
        this.name = name;
        this.value = value;
    }

    private PersonID(Parcel in) {
        this.name = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<PersonID> CREATOR = new Parcelable.Creator<PersonID>() {
        @Override
        public PersonID createFromParcel(Parcel source) {
            return new PersonID(source);
        }

        @Override
        public PersonID[] newArray(int size) {
            return new PersonID[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
