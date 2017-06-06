package com.slicky.ulj.javafakesocial.model.person;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SlickyPC on 17.5.2017
 */
@SuppressWarnings("unused")
public class PersonQueryInfo implements Parcelable {

    private final String seed;
    private final int results;
    private final int page;
    private final String version;

    public PersonQueryInfo(String seed,
                           int results,
                           int page,
                           String version) {
        this.seed = seed;
        this.results = results;
        this.page = page;
        this.version = version;
    }

    private PersonQueryInfo(Parcel in) {
        this.seed = in.readString();
        this.results = in.readInt();
        this.page = in.readInt();
        this.version = in.readString();
    }

    public static final Parcelable.Creator<PersonQueryInfo> CREATOR = new Parcelable.Creator<PersonQueryInfo>() {
        @Override
        public PersonQueryInfo createFromParcel(Parcel source) {
            return new PersonQueryInfo(source);
        }

        @Override
        public PersonQueryInfo[] newArray(int size) {
            return new PersonQueryInfo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.seed);
        dest.writeInt(this.results);
        dest.writeInt(this.page);
        dest.writeString(this.version);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getSeed() {
        return seed;
    }

    public int getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public String getVersion() {
        return version;
    }
}
