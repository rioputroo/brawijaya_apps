package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 12/19/2017.
 */

public class Products {

    public Products(String foodId, String foodName, double foodPrice, String foodImage) {
        this.productsId = foodId;
        this.productsName = foodName;
        this.productsPrice = foodPrice;
        this.productsPict = foodImage;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getProductsCategory() {
        return productsCategory;
    }

    public void setProductsCategory(String productsCategory) {
        this.productsCategory = productsCategory;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsPict() {
        return productsPict;
    }

    public void setProductsPict(String productsPict) {
        this.productsPict = productsPict;
    }

    private String productsId;
    private String productsName;
    private double productsPrice;
    private String productsCategory;
    private String productsDescription;
    private String productsPict;

    public String getProductsNote() {
        return productsNote;
    }

    public void setProductsNote(String productsNote) {
        this.productsNote = productsNote;
    }

    private String productsNote;


}
