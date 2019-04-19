package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by root on 01/08/2018.
 */
@SuppressWarnings("unused")
public class PersonDOB implements Parcelable {

    private final Date date;
    private final int age;

    public PersonDOB(Date date,
                     int age) {
        this.date = date;
        this.age = age;
    }

    private PersonDOB(Parcel in) {
        long tmpDob = in.readLong();
        this.date = tmpDob == -1 ? null : new Date(tmpDob);
        this.age = in.readInt();
    }

    public static final Parcelable.Creator<PersonDOB> CREATOR = new Parcelable.Creator<PersonDOB>() {
        @Override
        public PersonDOB createFromParcel(Parcel source) {
            return new PersonDOB(source);
        }

        @Override
        public PersonDOB[] newArray(int size) {
            return new PersonDOB[size];
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