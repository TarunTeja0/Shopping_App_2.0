package com.example.shoppingapp_20.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppingapp_20.modelclass.CartModelClass;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void insertCartItem(CartModelClass cartModelClass);

    @Delete
    void deleteCartItem(CartModelClass cartModelClass);

    @Update
    void updateCartItem(CartModelClass cartModelClass);

    @Query("SELECT * FROM userCart")
    List<CartModelClass> getAllCartItems();



}
