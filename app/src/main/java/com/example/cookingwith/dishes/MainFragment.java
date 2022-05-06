package com.example.cookingwith.dishes;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.cookingwith.R;
import com.example.cookingwith.model.DishModel;
import com.example.cookingwith.screens.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainFragment extends Fragment implements DishRvAdapter.ItemClickListener {
    private ArrayList<DishModel> list = new ArrayList<>();
    private CheckBox showCheckbox;
    private FloatingActionButton btnToBackAct;
    private String[] spinList = {"potato","apple","meat"};

    DishRvAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        ArrayAdapter<String> spinListAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, spinList);
        spinListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinFood = (Spinner) view.findViewById(R.id.spinner);
        spinFood.setAdapter(spinListAdapter);
        
        showCheckbox = view.findViewById(R.id.show_checkbox);
        btnToBackAct = view.findViewById(R.id.fabBtn);

        btnToBackAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        showCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCheckbox.isChecked()) {
                    list.clear();
                    buildListData();
                    adapter.notifyDataSetChanged();
                }
                else {
                    list.clear();

                    adapter.notifyDataSetChanged();
                }
            }
        });

        buildListData();
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.food_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        adapter = new DishRvAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    private void buildListData() {
        list.add(new DishModel("Оладьи", "Очень вкусные и нежные оладьи на кефире.", R.drawable.oladii));
        list.add(new DishModel("Гренки", "description2", R.drawable.grenki));
        list.add(new DishModel("Котлеты", "description3", R.drawable.kotleti));
        list.add(new DishModel("Суп", "description4", R.drawable.sup));
        list.add(new DishModel("Яичница", "description5", R.drawable.yaichnia));
        list.add(new DishModel("Тефтели", "description6", R.drawable.tefteli));
    }

    @Override
    public void onItemClick(DishModel dataModel) {
        Fragment fragment = DetailFragment.newInstance(dataModel.getTitle(), dataModel.getDescription());


        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        // transaction.replace(R.id.frame_container, fragment, "detail_fragment");

        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("main_fragment"));
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
