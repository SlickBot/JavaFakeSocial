package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class Person implements Parcelable {

    private final String gender;
    private final PersonName name;
    private final PersonLocation location;
    private final String email;
    private final PersonLogin login;
    private final PersonDOB dob;
    private final PersonRegistered registered;
    private final String phone;
    private final String cell;
    private final PersonID id;
    private final PersonPicture picture;
    private final String nat;

    public Person(String gender,
                  PersonName name,
                  PersonLocation location,
                  String email,
                  PersonLogin login,
                  PersonDOB dob,
                  PersonRegistered registered,
                  String phone,
                  String cell,
                  PersonID id,
                  PersonPicture picture,
                  String nat) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.login = login;
        this.dob = dob;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.id = id;
        this.picture = picture;
        this.nat = nat;
    }

    private Person(Parcel in) {
        this.gender = in.readString();
        this.name = in.readParcelable(PersonName.class.getClassLoader());
        this.location = in.readParcelable(PersonLocation.class.getClassLoader());
        this.email = in.readString();
        this.login = in.readParcelable(PersonLogin.class.getClassLoader());
        this.dob = in.readParcelable(PersonDOB.class.getClassLoader());
        this.registered = in.readParcelable(PersonRegistered.class.getClassLoader());
        this.phone = in.readString();
        this.cell = in.readString();
        this.id = in.readParcelable(PersonID.class.getClassLoader());
        this.picture = in.readParcelable(PersonPicture.class.getClassLoader());
        this.nat = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeParcelable(this.name, flags);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.email);
        dest.writeParcelable(this.login, flags);
        dest.writeParcelable(this.dob, flags);
        dest.writeParcelable(this.registered, flags);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
        dest.writeParcelable(this.id, flags);
        dest.writeParcelable(this.picture, flags);
        dest.writeString(this.nat);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getGender() {
        return gender;
    }

    public PersonName getName() {
        return name;
    }

    public PersonLocation getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public PersonLogin getLogin() {
        return login;
    }

    public PersonDOB getDob() {
        return dob;
    }

    public PersonRegistered getRegistered() {
        return registered;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public PersonID getId() {
        return id;
    }

    public PersonPicture getPicture() {
        return picture;
    }

    public String getNat() {
        return nat;
    }
}
