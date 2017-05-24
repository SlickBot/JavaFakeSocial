package com.slicky.ulj.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class PersonLogin implements Parcelable {

    private final String username;
    private final String password;
    private final String salt;
    private final String md5;
    private final String sha1;
    private final String sha256;

    public PersonLogin(String username,
                       String password,
                       String salt,
                       String md5,
                       String sha1,
                       String sha256) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.md5 = md5;
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    private PersonLogin(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.salt = in.readString();
        this.md5 = in.readString();
        this.sha1 = in.readString();
        this.sha256 = in.readString();
    }

    public static final Parcelable.Creator<PersonLogin> CREATOR = new Parcelable.Creator<PersonLogin>() {
        @Override
        public PersonLogin createFromParcel(Parcel source) {
            return new PersonLogin(source);
        }

        @Override
        public PersonLogin[] newArray(int size) {
            return new PersonLogin[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.salt);
        dest.writeString(this.md5);
        dest.writeString(this.sha1);
        dest.writeString(this.sha256);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getMd5() {
        return md5;
    }

    public String getSha1() {
        return sha1;
    }

    public String getSha256() {
        return sha256;
    }
}
