package com.example.shopmoon.Model;

import java.io.Serializable;

public class Product implements Serializable {
    public String proID, proName,proPrice, proPhoto,proProducer, proQuantity;

    public Product(String proID, String proName, String proPrice, String proPhoto, String proProducer, String proQuantity) {
        this.proID = proID;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proPhoto = proPhoto;
        this.proProducer = proProducer;
        this.proQuantity = proQuantity;
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

    public String getProPhoto() {
        return proPhoto;
    }

    public void setProPhoto(String proPhoto) {
        this.proPhoto = proPhoto;
    }

    public String getProProducer() {
        return proProducer;
    }

    public void setProProducer(String proProducer) {
        this.proProducer = proProducer;
    }

    public String getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(String proQuantity) {
        this.proQuantity = proQuantity;
    }
}
