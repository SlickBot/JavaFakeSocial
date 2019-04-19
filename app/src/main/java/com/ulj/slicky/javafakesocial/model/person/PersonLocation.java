package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonLocation implements Parcelable {

    private final String street;
    private final String city;
    private final String state;
    private final String postcode;

    public PersonLocation(String street,
                          String city,
                          String state,
                          String postcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }

    private PersonLocation(Parcel in) {
        this.street = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.postcode = in.readString();
    }

    public static final Parcelable.Creator<PersonLocation> CREATOR = new Parcelable.Creator<PersonLocation>() {
        @Override
        public PersonLocation createFromParcel(Parcel source) {
            return new PersonLocation(source);
        }

        @Override
        public PersonLocation[] newArray(int size) {
            return new PersonLocation[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.street);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.postcode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }
}
