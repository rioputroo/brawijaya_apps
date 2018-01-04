package com.example.nb_rioputro.brawijaya_apps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nb-rioputro on 12/19/2017.
 */

public class NewCart {
    private static List<Item> items = new ArrayList<Item>();

    public static void insert(Item item) {
        items.add(item);
    }

    public static void remove(Products products) {
        int index = getIndex(products);
        items.remove(index);
    }

    public static void update(Products products, int newValue) {
        int index = getIndex(products);
        int quantity = newValue;
        items.get(index).setQuantity(quantity);
    }

//    public static void remove(int id) {
//        items.remove(id);
//    }

    public static List<Item> contents() {
        return items;
    }

    public static double total() {
        double s = 0;
        for (Item item : items) {
            s += item.getProducts().getProductsPrice() * item.getQuantity();
        }

        return s;
    }

    public static boolean isExists(Products products) {
        return getIndex(products) != -1;
    }

    private static int getIndex(Products products) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProducts().getProductsId() == products.getProductsId()) {
                return i;
            }
        }

        return -1;
    }

}
