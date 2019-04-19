package com.ulj.slicky.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonQuery implements Parcelable {

    private final List<Person> results;
    private final PersonQueryInfo info;

    public PersonQuery(List<Person> results,
                       PersonQueryInfo info) {
        this.results = results;
        this.info = info;
    }

    private PersonQuery(Parcel in) {
        this.results = in.createTypedArrayList(Person.CREATOR);
        this.info = in.readParcelable(PersonQueryInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<PersonQuery> CREATOR = new Parcelable.Creator<PersonQuery>() {
        @Override
        public PersonQuery createFromParcel(Parcel source) {
            return new PersonQuery(source);
        }

        @Override
        public PersonQuery[] newArray(int size) {
            return new PersonQuery[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.results);
        dest.writeParcelable(this.info, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public List<Person> getResults() {
        return results;
    }

    public PersonQueryInfo getInfo() {
        return info;
    }
}
