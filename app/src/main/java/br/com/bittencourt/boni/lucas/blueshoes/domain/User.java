package br.com.bittencourt.boni.lucas.blueshoes.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class User  implements Parcelable {

    public static String KEY = "user-key";

    private String name;
    private int image;
    boolean status;

    public User(String name, int image, boolean status) {
        this.name = name;
        this.image = image;
        this.status = status;
    }

    protected User(Parcel in) {
        name = in.readString();
        image = in.readInt();
        status = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeByte((byte) (status ? 1 : 0));
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public boolean isStatus() {
        return status;
    }
}
