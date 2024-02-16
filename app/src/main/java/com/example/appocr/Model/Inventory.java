package com.example.appocr.Model;

import java.io.Serializable;

public class Inventory implements Serializable {
    private int id;
    private String ten;
    private int quantity;
    private String gia;

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", ten='" + ten + '\'' +
                ", quantity=" + quantity +
                ", gia='" + gia + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public Inventory(int id, String ten, int quantity, String gia) {
        this.id = id;
        this.ten = ten;
        this.quantity = quantity;
        this.gia = gia;
    }

    public Inventory(String ten, int quantity, String gia) {
        this.ten = ten;
        this.quantity = quantity;
        this.gia = gia;
    }
}

