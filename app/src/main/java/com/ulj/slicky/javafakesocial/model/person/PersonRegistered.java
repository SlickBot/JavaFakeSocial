package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by root on 01/08/2018.
 */
@SuppressWarnings("unused")
public class PersonRegistered implements Parcelable {

    private final Date date;
    private final int age;

    public PersonRegistered(Date date,
                            int age) {
        this.date = date;
        this.age = age;
    }

    private PersonRegistered(Parcel in) {
        long tmpDob = in.readLong();
        this.date = tmpDob == -1 ? null : new Date(tmpDob);
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<PersonRegistered> CREATOR = new Parcelable.Creator<PersonRegistered>() {
        @Override
        public PersonRegistered createFromParcel(Parcel source) {
            return new PersonRegistered(source);
        }

        @Override
        public PersonRegistered[] newArray(int size) {
            return new PersonRegistered[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeInt(this.age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Date getDate() {
        return date;
    }

    public int getAge() {
        return age;
    }

}
