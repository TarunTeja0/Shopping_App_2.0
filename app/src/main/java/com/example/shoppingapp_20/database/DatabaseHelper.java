package com.example.shoppingapp_20.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppingapp_20.modelclass.CartModelClass;
import com.example.shoppingapp_20.modelclass.ItemModelClass;

@Database(entities = {ItemModelClass.class, CartModelClass.class}, exportSchema = false, version = 4)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract CartDao cartDao();
    public abstract ItemsDao itemsDao();

    public static final String DATABASE_NAME ="Shopping.db";
    public static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, DatabaseHelper.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
