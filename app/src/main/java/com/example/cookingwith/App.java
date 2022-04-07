package com.example.cookingwith;

import android.app.Application;

import androidx.room.Room;

import com.example.cookingwith.data.AppDatabase;
import com.example.cookingwith.data.ItemDAO;

public class App extends Application {

    private AppDatabase database;
    private ItemDAO itemDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-food")
                .allowMainThreadQueries()
                .build();

        itemDao = database.itemDAO();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public ItemDAO getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }
}
