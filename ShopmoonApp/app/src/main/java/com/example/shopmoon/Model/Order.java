package com.example.shopmoon.Model;

public class Order {
    public String orderID ,proID ,proPhoto ,proName,cusID ,cusName ,cusPhone ,cusEmail,cusAddress,proPrice,proQuantity ,proMoney ,orderStatus;

    public Order(String orderID, String proID, String proPhoto, String proName, String cusID, String cusName, String cusPhone, String cusEmail, String cusAddress, String proPrice, String proQuantity, String proMoney, String orderStatus) {
        this.orderID = orderID;
        this.proID = proID;
        this.proPhoto = proPhoto;
        this.proName = proName;
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
        this.cusEmail = cusEmail;
        this.cusAddress = cusAddress;
        this.proPrice = proPrice;
        this.proQuantity = proQuantity;
        this.proMoney = proMoney;
        this.orderStatus = orderStatus;
    }

    public Order(String orderID, String proID, String proPhoto, String proName, String cusID, String cusName, String cusPhone, String cusEmail, String cusAddress, String proPrice, String proQuantity, String đang_đặt_hàng) {
    }
}
