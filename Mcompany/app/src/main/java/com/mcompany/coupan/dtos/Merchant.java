
package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Merchant implements Parcelable
{

    private String id;
    private String name;
    private String type;
    private Address address;
    private String bestDealThreshold;
    private List<Deal> deals = new ArrayList<Deal>();
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
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        this.bestDealThreshold = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.deals, (Deal.class.getClassLoader()));
    }

    public Merchant() {
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

    public String getBestDealThreshold() {
        return bestDealThreshold;
    }

    public void setBestDealThreshold(String bestDealThreshold) {
        this.bestDealThreshold = bestDealThreshold;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        dest.writeValue(type);
        dest.writeValue(address);
        dest.writeValue(bestDealThreshold);
        dest.writeList(deals);
    }

    public int describeContents() {
        return  0;
    }

}
