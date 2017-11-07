package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */

public class Food {
    private String foodId, foodName, foodPrice, foodStock, foodContent, foodCategory, foodDescription, foodPict;

    public Food(String foodId, String foodName, String foodPrice, String foodStock, String foodContent, String foodCategory, String foodDescription, String foodPict) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodStock = foodStock;
        this.foodContent = foodContent;
        this.foodCategory = foodCategory;
        this.foodDescription = foodDescription;
        this.foodPict = foodPict;
    }

    public Food() {
    }

    public String getFoodPict() {
        return foodPict;
    }

    public void setFoodPict(String foodPict) {
        this.foodPict = foodPict;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(String foodStock) {
        this.foodStock = foodStock;
    }

    public String getFoodContent() {
        return foodContent;
    }

    public void setFoodContent(String foodContent) {
        this.foodContent = foodContent;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
