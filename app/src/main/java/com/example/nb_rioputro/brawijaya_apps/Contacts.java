package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/14/2017.
 */

public class Contacts {
    public Contacts(String id_user, String nama_user, String username_user, String role_user) {
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.username_user = username_user;
        this.role_user = role_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    private String nama_user;
    private String username_user;
    private String id_user;

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    private String role_user;


    public Contacts(String namakontak) {
        this.nama_user = namakontak;
    }
}
