package com.example.cookingwith.screens.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingwith.R;
import com.example.cookingwith.databinding.ActivityMainBinding;
import com.example.cookingwith.dishes.DetailFragment;
import com.example.cookingwith.dishes.DishActivity;
import com.example.cookingwith.model.DishModel;
import com.example.cookingwith.model.Item;
import com.example.cookingwith.screens.details.ItemDetailsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        recyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDetailsActivity.start(MainActivity.this, null);
            }
        });

        Button btnToSecondAct = (Button) findViewById(R.id.fab2);
// 441504000000
        btnToSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DishActivity.class);
                startActivity(intent);
            }
        });

        // ???????????????? ?????????????????? viewModel ?????? ?????????? ????????????????
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // ?????????????????????????? ???? ??????????????
        // ???????????? ???????????????? ?????????????? ?????????????????? ????????. ?????????? ?????????????????????????? ????????????????????, ?????????? ???????????????? ??????????????
        mainViewModel.getItemLiveData().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                adapter.setItems(items);
            }
        });

    }

}