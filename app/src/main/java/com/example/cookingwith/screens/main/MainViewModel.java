package com.example.cookingwith.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.cookingwith.App;
import com.example.cookingwith.model.Item;

import java.util.List;

public class MainViewModel extends ViewModel {

    private LiveData<List<Item>> itemLiveData = App.getInstance().getItemDao().getAllLiveData();

    public LiveData<List<Item>> getItemLiveData() {
        return itemLiveData;
    }
}
