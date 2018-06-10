
package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Merchants implements Parcelable
{

    private String id;
    private String name;
    private Address address;
    private List<Deal> deals = new ArrayList<Deal>();
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
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        in.readList(this.deals, (Deal.class.getClassLoader()));
    }

    public Merchants() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(address);
        dest.writeList(deals);
    }

    public int describeContents() {
        return  0;
    }

}
