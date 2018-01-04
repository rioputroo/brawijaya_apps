package com.example.nb_rioputro.brawijaya_apps;

import java.io.Serializable;

/**
 * Created by nb-rioputro on 12/19/2017.
 */

public class Item implements Serializable {
    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(Products products, int quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public Item() {
    }

    private Products products;
    private int quantity;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String note;
}
