package org.pasut.prode.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by marcelo on 22/04/14.
 */
public class Fixture implements Entity, Parcelable {
    private final String _id;
    private final String name;
    private final String description;
    private final Date closeDate;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCloseDate() {
        return closeDate;
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
        dest.writeLong(this.closeDate.getTime());
    }

    public Fixture(String _id, String name, String description, Date closeDate) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.closeDate = closeDate;
    }

    private Fixture(Parcel in) {
        this(in.readString(), in.readString(), in.readString(), new Date(in.readLong()));
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
