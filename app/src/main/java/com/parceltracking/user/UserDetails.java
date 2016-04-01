package com.parceltracking.user;

/**
 * Created by ioan.contiu on 2/24/2016.
 */
public class UserDetails {
    private int uid;
    private String fName;
    private String lName;
    private String phone;
    private String city;
    private String state;
    private String addressLine1;
    private String addressLine2;
    private String credit;

    public UserDetails() {
        this.uid = 0;
        this.fName = null;
        this.lName = null;
        this.phone = null;
        this.city = null;
        this.state = null;
        this.addressLine1 = null;
        this.addressLine2 = null;
        this.credit = null;
    }

    public UserDetails(int uid, String fName, String lName, String phone, String city, String state, String addressLine1, String addressLine2, String credit) {
        this.uid = uid;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.city = city;
        this.state = state;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.credit = credit;
    }

    public String getfName() {
        return this.fName;
    }

    public String getlName() {
        return this.lName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public String getCredit() {
        return this.credit;
    }

    public int getUid (){
        return this.uid;
    }

}
