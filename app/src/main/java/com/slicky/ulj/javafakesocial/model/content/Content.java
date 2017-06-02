package com.slicky.ulj.javafakesocial.model.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.slicky.ulj.javafakesocial.model.person.Person;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class Content implements Parcelable {

    private final Person owner;
    private final String text;
    private final long postedAt;

    public Content(Person owner,
                   String text,
                   long postedAt) {
        this.owner = owner;
        this.postedAt = postedAt;
        this.text = text;
    }

    private Content(Parcel in) {
        this.owner = in.readParcelable(Person.class.getClassLoader());
        this.text = in.readString();
        this.postedAt = in.readLong();
    }

    public static final Parcelable.Creator<Content> CREATOR = new Parcelable.Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.text);
        dest.writeLong(this.postedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Person getOwner() {
        return owner;
    }

    public String getText() {
        return text;
    }

    public long getPostedAt() {
        return postedAt;
    }
}
