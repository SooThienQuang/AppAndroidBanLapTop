package com.example.shopmoon.Model;

import java.io.Serializable;

public class ProductDetail implements Serializable {
   public String proID,proName,proPrice,proDetail,proDetailPhoto1 ,proDetailPhoto2,proDetailPhoto3 ,proDetailPhoto4;

    public ProductDetail(String proID, String proName, String proPrice, String proDetail, String proDetailPhoto1, String proDetailPhoto2, String proDetailPhoto3, String proDetailPhoto4) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proDetail = proDetail;
        this.proDetailPhoto1 = proDetailPhoto1;
        this.proDetailPhoto2 = proDetailPhoto2;
        this.proDetailPhoto3 = proDetailPhoto3;
        this.proDetailPhoto4 = proDetailPhoto4;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public String getProDetail() {
        return proDetail;
    }

    public void setProDetail(String proDetail) {
        this.proDetail = proDetail;
    }

    public String getProDetailPhoto1() {
        return proDetailPhoto1;
    }

    public void setProDetailPhoto1(String proDetailPhoto1) {
        this.proDetailPhoto1 = proDetailPhoto1;
    }

    public String getProDetailPhoto2() {
        return proDetailPhoto2;
    }

    public void setProDetailPhoto2(String proDetailPhoto2) {
        this.proDetailPhoto2 = proDetailPhoto2;
    }

    public String getProDetailPhoto3() {
        return proDetailPhoto3;
    }

    public void setProDetailPhoto3(String proDetailPhoto3) {
        this.proDetailPhoto3 = proDetailPhoto3;
    }

    public String getProDetailPhoto4() {
        return proDetailPhoto4;
    }

    public void setProDetailPhoto4(String proDetailPhoto4) {
        this.proDetailPhoto4 = proDetailPhoto4;
    }
}
