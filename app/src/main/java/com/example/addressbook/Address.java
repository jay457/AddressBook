package com.example.addressbook;

import android.media.Image;

public class Address {

    public Address(String FName, String LName, String Email, String Phone) {
        fName = FName;
        lName = LName;
        email = Email;
        phone = Phone;
    }

    private android.media.Image image;

    public Image getImage() {
        return image;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    private String fName = "";
    private String lName = "";
    private String email = "";
    private String phone = "";






}
