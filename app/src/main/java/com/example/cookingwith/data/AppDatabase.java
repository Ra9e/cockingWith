package com.example.cookingwith.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cookingwith.model.Item;

@Database(entities = {Item.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDAO itemDAO();
}
