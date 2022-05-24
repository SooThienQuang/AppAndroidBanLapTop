package com.example.shopmoon.Model;

import java.io.Serializable;

public class Customer implements Serializable {
  public String  cusID,cusName,cusPhone,cusEmail,cusPassword,cusAddress,cusPhoto ;

    public Customer(String cusID, String cusName, String cusPhone, String cusEmail, String cusPassword, String cusAddress, String cusPhoto) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusEmail = cusEmail;
        this.cusPassword = cusPassword;
        this.cusAddress = cusAddress;
        this.cusPhoto = cusPhoto;
    }
}
