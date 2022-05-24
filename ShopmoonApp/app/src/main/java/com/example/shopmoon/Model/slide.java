package com.example.shopmoon.Model;

import java.io.Serializable;

public class slide implements Serializable {
    public String hinhslide;

    public slide(String hinhslide) {
        this.hinhslide = hinhslide;
    }

    public String getHinhslide() {
        return hinhslide;
    }

    public void setHinhslide(String hinhslide) {
        this.hinhslide = hinhslide;
    }
}
