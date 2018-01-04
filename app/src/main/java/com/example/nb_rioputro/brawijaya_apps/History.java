package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 12/27/2017.
 */

public class History {
    private String id_purchase;
    private String id_users;
    private String id_purchase_details;
    private String total_purchase;
    private String status_purchase;

    public String getId_purchase() {
        return id_purchase;
    }

    public void setId_purchase(String id_purchase) {
        this.id_purchase = id_purchase;
    }

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getId_purchase_details() {
        return id_purchase_details;
    }

    public void setId_purchase_details(String id_purchase_details) {
        this.id_purchase_details = id_purchase_details;
    }

    public String getTotal_purchase() {
        return total_purchase;
    }

    public void setTotal_purchase(String total_purchase) {
        this.total_purchase = total_purchase;
    }

    public String getStatus_purchase() {
        return status_purchase;
    }

    public void setStatus_purchase(String status_purchase) {
        this.status_purchase = status_purchase;
    }

    public String getWaktu_purchase() {
        return waktu_purchase;
    }

    public void setWaktu_purchase(String waktu_purchase) {
        this.waktu_purchase = waktu_purchase;
    }

    private String waktu_purchase;


}
