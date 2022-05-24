package com.example.shopmoon.Model;

import java.io.Serializable;

public class Notification_class implements Serializable {
    public int hinh;
    public String ten,chitiet;

    public Notification_class(int hinh, String ten, String chitiet) {
        this.hinh = hinh;
        this.ten = ten;
        this.chitiet = chitiet;
    }
}
