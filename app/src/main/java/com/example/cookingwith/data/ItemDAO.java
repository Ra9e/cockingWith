package com.example.cookingwith.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cookingwith.model.Item;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Query("SELECT * FROM Item")
    LiveData<List<Item>> getAllLiveData();

    @Query("SELECT * FROM Item WHERE uid IN (:itemIds)")
    List<Item> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM Item WHERE uid = :uid LIMIT 1")
    Item findById(int uid);

    @Query("SELECT * FROM Item WHERE text = :text LIMIT 1")
    Item findByName(String text);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);
}
