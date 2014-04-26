package org.pasut.prode.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marcelo on 22/04/14.
 */
public class Fixture implements Entity, Parcelable {
    private final String _id;
    private final String name;
    private final String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getId() {
        return _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    public Fixture(String _id, String name, String description) {
        this._id = _id;
        this.name = name;
        this.description = description;
    }

    private Fixture(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static Parcelable.Creator<Fixture> CREATOR = new Parcelable.Creator<Fixture>() {
        public Fixture createFromParcel(Parcel source) {
            return new Fixture(source);
        }

        public Fixture[] newArray(int size) {
            return new Fixture[size];
        }
    };
}
