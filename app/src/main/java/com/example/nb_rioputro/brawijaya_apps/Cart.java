package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/8/2017.
 */

public class Cart {
    private int id;
    private int id_order;
    private int id_detail_order;
    private int id_food;
    private int jumlah_order;
    private int total_order;
    private int price_food;
    private String status_order;
    private String name_food;
    private String pict_food;

    private String username;
    private String foodid;
    private String note;
    private int quantity;
    private int total;
    private String status;

    public Cart(String foodName, int jumlahOrder, int totalOrder, String foodPict) {
        this.name_food = foodName;
        this.jumlah_order = jumlahOrder;
        this.total_order = totalOrder;
        this.pict_food = foodPict;
    }


    public Cart(String username, String foodid, String note, int quantity, int total, String status) {
        this.foodid = foodid;
        this.username = username;
        this.note = note;
        this.quantity = quantity;
        this.total = total;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice_food() {
        return price_food;
    }

    public void setPrice_food(int price_food) {
        this.price_food = price_food;
    }

    public Cart(String foodName, int jumlahOrder, int totalOrder, String foodPict, int id, int price_food) {
        this.name_food = foodName;
        this.jumlah_order = jumlahOrder;
        this.total_order = totalOrder;
        this.pict_food = foodPict;
        this.id = id;
        this.price_food = price_food;

    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getPict_food() {
        return pict_food;
    }

    public void setPict_food(String pict_food) {
        this.pict_food = pict_food;
    }

    public Cart() {
    }

    public Cart(int foodId, int jumlah_order, int total, String status) {
        this.id_food = foodId;
        this.jumlah_order = jumlah_order;
        this.total_order = total;
        this.status_order = status;
    }

    public Cart(int foodId, String foodName, String jumlah_order, int total, String status) {
        this.id_food = foodId;
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

    public Cart(String foodName, String jumlahOrder, String totalOrder, String foodPict) {
        this.name_food = foodName;
        this.jumlah_order = Integer.parseInt(jumlahOrder);
        this.total_order = Integer.parseInt(totalOrder);
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
