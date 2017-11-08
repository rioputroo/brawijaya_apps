package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/8/2017.
 */

public class Cart {
    private int id_order;
    private int id_detail_order;
    private int id_food;
    private int jumlah_order;
    private int total_order;
    private String status_order;
    private String name_food;
    private String pict_food;

    public Cart() {
    }

    public Cart(String foodId, String jumlah_order, int total, String status) {
        this.id_food = Integer.parseInt(foodId);
        this.jumlah_order = Integer.parseInt(jumlah_order);
        this.total_order = total;
        this.status_order = status;
    }

    public Cart(String foodId, String foodName, String jumlah_order, int total, String status) {
        this.id_food = Integer.parseInt(foodId);
        this.jumlah_order = Integer.parseInt(jumlah_order);
        this.total_order = total;
        this.status_order = status;
        this.name_food = foodName;
    }

    public Cart(String foodId, String foodName, String foodPict, String jumlah_order, int total, String status) {
        this.id_food = Integer.parseInt(foodId);
        this.jumlah_order = Integer.parseInt(jumlah_order);
        this.total_order = total;
        this.status_order = status;
        this.name_food = foodName;
        this.pict_food = foodPict;
    }

    public int getId_order() {
        return id_order;
    }

    public Cart(int id_order, int id_detail_order, int id_food, int jumlah_order, int total_order, String status_order) {
        this.id_order = id_order;
        this.id_detail_order = id_detail_order;
        this.id_food = id_food;
        this.jumlah_order = jumlah_order;
        this.total_order = total_order;
        this.status_order = status_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_detail_order() {
        return id_detail_order;
    }

    public void setId_detail_order(int id_detail_order) {
        this.id_detail_order = id_detail_order;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public int getJumlah_order() {
        return jumlah_order;
    }

    public void setJumlah_order(int jumlah_order) {
        this.jumlah_order = jumlah_order;
    }

    public int getTotal_order() {
        return total_order;
    }

    public void setTotal_order(int total_order) {
        this.total_order = total_order;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }
}
