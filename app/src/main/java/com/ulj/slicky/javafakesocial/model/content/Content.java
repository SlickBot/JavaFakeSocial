package com.ulj.slicky.javafakesocial.model.content;

import android.os.Parcel;
import android.os.Parcelable;

import com.ulj.slicky.javafakesocial.model.person.Person;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class Content implements Parcelable {

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

    private final long id;
    private final Person owner;
    private final String text;
    private final long postedAt;

    public Content(long id,
                   Person owner,
                   String text,
                   long postedAt) {
        this.id = id;
        this.owner = owner;
        this.postedAt = postedAt;
        this.text = text;
    }

    private Content(Parcel in) {
        this.id = in.readLong();
        this.owner = in.readParcelable(Person.class.getClassLoader());
        this.text = in.readString();
        this.postedAt = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeParcelable(this.owner, flags);
        dest.writeString(this.text);
        dest.writeLong(this.postedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
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

    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Content))
            return false;
        Content content = (Content) o;
        return id == content.id;
    }

}
