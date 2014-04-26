package org.pasut.prode.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by marcelo on 26/04/14.
 */
public class Tournament implements Entity, Parcelable {
    private final String _id;
    private final String name;
    private final List<Fixture> fixtures;

    public Tournament(String name, List<Fixture> fixtures) {
        this._id = null;
        this.name = name;
        this.fixtures = fixtures;
    }
    @Override
    public String getId() {
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.name);
        dest.writeTypedList(fixtures);
    }

    private Tournament(Parcel in) {
        this._id = in.readString();
        this.name = in.readString();
        List<Fixture> fixtures = Lists.newArrayList();
        in.readTypedList(fixtures, Fixture.CREATOR);
        this.fixtures = fixtures;
    }

    public static Parcelable.Creator<Tournament> CREATOR = new Parcelable.Creator<Tournament>() {
        public Tournament createFromParcel(Parcel source) {
            return new Tournament(source);
        }

        public Tournament[] newArray(int size) {
            return new Tournament[size];
        }
    };
}
