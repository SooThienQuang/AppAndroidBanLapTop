package com.example.shopmoon.Model;

import java.io.Serializable;

public class Cart_class implements Serializable {
    public String cartID, proID, proPhoto,proName, cusID, proPrice, proQuantity, proMoney;

    public Cart_class(String cartID, String proID, String proPhoto, String proName, String cusID, String proPrice, String proQuantity, String proMoney) {
        this.cartID = cartID;
        this.proID = proID;
        this.proPhoto = proPhoto;
        this.proName = proName;
        this.cusID = cusID;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proMoney = proMoney;
    }
}
