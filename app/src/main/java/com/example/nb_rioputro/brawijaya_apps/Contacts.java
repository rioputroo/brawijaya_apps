package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/14/2017.
 */

public class Contacts {
    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    private String nama_user;


    public Contacts(String namakontak) {
        this.nama_user = namakontak;
    }
}
