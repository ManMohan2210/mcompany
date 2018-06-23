
package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by manmohansingh on 10-06-2018.
 */
public class Address implements Parcelable
{

    private String firstname;
    private String lastname;
    private String line1;
    private String line2;
    private String latitude;
    private String longitude;
    private String mobile;
    private String pin;
    public final static Creator<Address> CREATOR = new Creator<Address>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    }
    ;

    protected Address(Parcel in) {
        this.firstname = ((String) in.readValue((String.class.getClassLoader())));
        this.lastname = ((String) in.readValue((String.class.getClassLoader())));
        this.line1 = ((String) in.readValue((String.class.getClassLoader())));
        this.line2 = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.pin = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Address() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(firstname);
        dest.writeValue(lastname);
        dest.writeValue(line1);
        dest.writeValue(line2);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(mobile);
        dest.writeValue(pin);
    }

    public int describeContents() {
        return  0;
    }

}
