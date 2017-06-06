package com.slicky.ulj.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonPicture implements Parcelable {

    private final String large;
    private final String medium;
    private final String thumbnail;

    public PersonPicture(String large,
                         String medium,
                         String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    private PersonPicture(Parcel in) {
        this.large = in.readString();
        this.medium = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<PersonPicture> CREATOR = new Parcelable.Creator<PersonPicture>() {
        @Override
        public PersonPicture createFromParcel(Parcel source) {
            return new PersonPicture(source);
        }

        @Override
        public PersonPicture[] newArray(int size) {
            return new PersonPicture[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.large);
        dest.writeString(this.medium);
        dest.writeString(this.thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
