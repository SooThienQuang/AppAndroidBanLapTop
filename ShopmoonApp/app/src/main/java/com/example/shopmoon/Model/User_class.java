package com.example.shopmoon.Model;

import java.io.Serializable;
import java.security.PublicKey;

public class User_class implements Serializable {
   public String  cusID,cusName,cusPhone,cusEmail,cusPassword,cusAddress,cusPhoto;

    public User_class(String cusID, String cusName, String cusPhone, String cusEmail, String cusPassword, String cusAddress, String cusPhoto) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusEmail = cusEmail;
        this.cusPassword = cusPassword;
        this.cusAddress = cusAddress;
        this.cusPhoto = cusPhoto;
    }
}
