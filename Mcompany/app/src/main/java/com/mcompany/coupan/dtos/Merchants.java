
package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Merchants implements Parcelable
{

    private List<Merchant> merchants = new ArrayList<Merchant>();
    public final static Creator<Merchants> CREATOR = new Creator<Merchants>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Merchants createFromParcel(Parcel in) {
            return new Merchants(in);
        }

        public Merchants[] newArray(int size) {
            return (new Merchants[size]);
        }

    }
    ;

    protected Merchants(Parcel in) {
        in.readList(this.merchants, (Merchant.class.getClassLoader()));
    }

    public Merchants() {
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(merchants);
    }

    public int describeContents() {
        return  0;
    }

}
