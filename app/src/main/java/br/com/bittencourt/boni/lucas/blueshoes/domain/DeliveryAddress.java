package br.com.bittencourt.boni.lucas.blueshoes.domain;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import br.com.bittencourt.boni.lucas.blueshoes.R;

public class DeliveryAddress implements Parcelable {

     final static public String KEY = "delivery-address-key";

    String street;
    Integer number;
    String complement;
    String zipCode;
    String neighborhood;
    String city;
    Integer state;


    public DeliveryAddress(String street, Integer number, String complement, String zipCode, String neighborhood, String city, Integer state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    protected DeliveryAddress(Parcel in) {
        street = in.readString();
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        complement = in.readString();
        zipCode = in.readString();
        neighborhood = in.readString();
        city = in.readString();
        if (in.readByte() == 0) {
            state = null;
        } else {
            state = in.readInt();
        }
    }

    public static final Creator<DeliveryAddress> CREATOR = new Creator<DeliveryAddress>() {
        @Override
        public DeliveryAddress createFromParcel(Parcel in) {
            return new DeliveryAddress(in);
        }

        @Override
        public DeliveryAddress[] newArray(int size) {
            return new DeliveryAddress[size];
        }
    };

    public String getStateName(Context context) {
        return context
                .getResources()
                .getStringArray(R.array.states)[state];
    }


    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public Integer getState() {
        return state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(street);
        if (number == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(number);
        }
        parcel.writeString(complement);
        parcel.writeString(zipCode);
        parcel.writeString(neighborhood);
        parcel.writeString(city);
        if (state == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(state);
        }
    }
}
