package com.example.shoppingapp_20.modelclass;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "userCart")
public class CartModelClass {

    @PrimaryKey
    private int cartId;
    private String cartItemName;
    private int cartItemQuantity;
    private int cost;
    private int stock;
//    private String cart;

    @Ignore
    public CartModelClass() {
    }

    public CartModelClass(int cartId, String cartItemName, int cartItemQuantity,int stock, int cost) {
        this.cartId = cartId;
        this.cartItemName = cartItemName;
        this.cartItemQuantity = cartItemQuantity;
        this.stock = stock;
        this.cost = cost;
//        this.cart = cart;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public void setCartItemName(String cartItemName) {
        this.cartItemName = cartItemName;
    }

    public int getCartItemQuantity() {
        return cartItemQuantity;
    }

    public void setCartItemQuantity(int cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //    public String getCart() {
//        return cart;
//    }
//
//    public void setCart(String cart) {
//        this.cart = cart;
//    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
