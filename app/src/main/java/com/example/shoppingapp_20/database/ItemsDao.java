package com.example.shoppingapp_20.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppingapp_20.modelclass.ItemModelClass;

import java.util.List;

@Dao
public interface ItemsDao {
    @Insert
    void insertItem(ItemModelClass itemModelClass);

    @Delete
    void removeItem(ItemModelClass itemModelClass);

    @Update
    void updateItem(ItemModelClass itemModelClass);

    @Query("SELECT * FROM items")
    List<ItemModelClass> getAllItems();

}
