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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cookingwith.R;
import com.example.cookingwith.model.DishModel;
import com.example.cookingwith.screens.main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.*;


public class MainFragment extends Fragment implements DishRvAdapter.ItemClickListener {
    private ArrayList<DishModel> list = new ArrayList<>();
    private CheckBox showCheckbox;
    private FloatingActionButton btnToBackAct;
    private String[] spinList = {"Potato", "Eggs", "Meat"};
    private static final HashMap<String,DishModel> foodHash = new HashMap<String,DishModel>() {{
        put("Potato1", new DishModel("Суп", "description4", R.drawable.sup));
        put("Eggs1",   new DishModel("Оладьи", "Очень вкусные и нежные оладьи на кефире.", R.drawable.oladii));
        put("Eggs2",   new DishModel("Гренки", "description2", R.drawable.grenki));
        put("Eggs3",   new DishModel("Яичница", "description5", R.drawable.yaichnia));
        put("Meat1",   new DishModel("Котлеты", "description3", R.drawable.kotleti));
        put("Meat2",   new DishModel("Тефтели", "description6", R.drawable.tefteli));
    }};


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
                    buildAllListData();
                    adapter.notifyDataSetChanged();
                }
                else {
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });


        spinFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setCheckboxUnchecked();
                buildSortedListData(parent.getSelectedItem().toString());
                Toast.makeText(getContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        buildAllListData();
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

    private void buildAllListData() {
        for (Map.Entry<String, DishModel> entry: foodHash.entrySet()) {
            list.add(entry.getValue());
        }
    }
    private void buildSortedListData(String food) {
        for (Map.Entry<String, DishModel> entry: foodHash.entrySet()) {
            String key = entry.getKey().replaceAll("\\d", "");
            System.out.println(key + " / " + (food) + " / " + key.equals(food));
            if (key.equals(food)) {
                list.add(entry.getValue());
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setCheckboxUnchecked() {
        showCheckbox.setChecked(false);
        list.clear();
        adapter.notifyDataSetChanged();
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
