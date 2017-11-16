package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/16/2017.
 */

public class Makanan {
    public String foodname;
    public String foodid;
    public String fooddesc;
    public String foodimage;
    public String foodcat;
    public String custprice;
    public String doctprice;

    public Makanan() {
    }

    public Makanan(String foodname) {
        this.foodname = foodname;
    }

    public Makanan(String foodName, String foodPrice, String foodImage) {
        this.foodname = foodName;
        this.custprice = foodPrice;
        this.foodimage = foodImage;
    }

    public Makanan(String foodId, String foodName, String foodPrice, String foodImage) {
        this.foodid = foodId;
        this.foodname = foodName;
        this.custprice = foodPrice;
        this.foodimage = foodImage;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodid() {
        return foodid;
    }

    public void setFoodid(String foodid) {
        this.foodid = foodid;
    }

    public String getFooddesc() {
        return fooddesc;
    }

    public void setFooddesc(String fooddesc) {
        this.fooddesc = fooddesc;
    }

    public String getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(String foodimage) {
        this.foodimage = foodimage;
    }

    public String getFoodcat() {
        return foodcat;
    }

    public void setFoodcat(String foodcat) {
        this.foodcat = foodcat;
    }

    public String getCustprice() {
        return custprice;
    }

    public void setCustprice(String custprice) {
        this.custprice = custprice;
    }

    public String getDoctprice() {
        return doctprice;
    }

    public void setDoctprice(String doctprice) {
        this.doctprice = doctprice;
    }
}
