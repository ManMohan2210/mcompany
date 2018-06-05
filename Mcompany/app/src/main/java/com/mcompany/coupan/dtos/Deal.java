package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

public class Deal implements Parcelable {

    private String code;
    private String endDate;
    private String message;
    private String imageUrl;
    public final static Creator<Deal> CREATOR = new Creator<Deal>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Deal createFromParcel(Parcel in) {
            return new Deal(in);
        }

        public Deal[] newArray(int size) {
            return (new Deal[size]);
        }

    };

    protected Deal(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Deal() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(endDate);
        dest.writeValue(message);
        dest.writeValue(imageUrl);
    }

    public int describeContents() {
        return 0;
    }
}
