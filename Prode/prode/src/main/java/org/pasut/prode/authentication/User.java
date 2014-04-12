package org.pasut.prode.authentication;

import android.os.Parcel;
import android.os.Parcelable;

import org.pasut.prode.entities.Entity;

/**
 * Created by marcelo on 19/03/14.
 */
public class User implements Parcelable, Entity {
    private final String _id;
    private final String userId;

    private final UserType type;
    private final String username;
    private final String device;
    private String name;

    public User(String userId, String username, UserType type, String device) {
        this(null, userId, username, type, device, null);
    }

    public User(String userId, String username, UserType type, String device, String name) {
        this(null, userId, username, type, device, name);
    }

    public User(String _id, String userId, String username, UserType type, String device, String name) {
        this._id = _id;
        this.userId = userId;
        this.username = username;
        this.type = type;
        this.device = device;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getUniqueId() {
        return type.toString() + "-" + userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.userId);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.username);
        dest.writeString(this.device);
        dest.writeString(this.name);
    }

    private User(Parcel in) {
        this._id = in.readString();
        this.userId = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : UserType.values()[tmpType];
        this.username = in.readString();
        this.device = in.readString();
        this.name = in.readString();
    }

    public static Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String getId() {
        return _id;
    }

    public String getUserId() {
        return userId;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getDevice() {
        return device;
    }

    public String getName() {
        return name;
    }
}
