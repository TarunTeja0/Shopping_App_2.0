package com.example.shoppingapp_20.modelclass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class ItemModelClass {
    @PrimaryKey(autoGenerate = true)
    int index;
    String name;
    int cost;
    int stock;
    String unitType;
////    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    String imageUrl;
    String description;
    String category;
//    int quantity;

//    String deliveryDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

    //    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

//    public int getQuantity() {
//        return quantity;
//    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

//    public String getDeliveryDate() {
//        return deliveryDate;
//    }

//    public void setDeliveryDate(String deliveryDate) {
//        this.deliveryDate = deliveryDate;
//    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ItemModelClass(String name,String unitType, String description, String category, int stock, int cost) {
        this.name = name;
//        this.imageUrl = imageUrl;
        this.description = description;
        this.category = category;
        this.unitType = unitType;
//        this.quantity = quantity;
        this.stock = stock;
        this.cost = cost;
//        this.deliveryDate = deliveryDate;
//        this.index = index;
    }
}