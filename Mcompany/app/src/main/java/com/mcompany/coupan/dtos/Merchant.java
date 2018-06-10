
package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Merchant implements Parcelable
{

    private List<Merchants> merchants = new ArrayList<Merchants>();
    public final static Creator<Merchant> CREATOR = new Creator<Merchant>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Merchant createFromParcel(Parcel in) {
            return new Merchant(in);
        }

        public Merchant[] newArray(int size) {
            return (new Merchant[size]);
        }

    }
    ;

    protected Merchant(Parcel in) {
        in.readList(this.merchants, (Merchants.class.getClassLoader()));
    }

    public Merchant() {
    }

    public List<Merchants> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchants> merchants) {
        this.merchants = merchants;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(merchants);
    }

    public int describeContents() {
        return  0;
    }

}
