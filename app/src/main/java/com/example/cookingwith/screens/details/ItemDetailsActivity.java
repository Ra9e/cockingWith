package com.example.cookingwith.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookingwith.App;
import com.example.cookingwith.R;
import com.example.cookingwith.model.Item;

public class ItemDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM = "ItemDetailsActivity.EXTRA_ITEM";
    private String[] spinList = {"Potato", "Eggs", "Meat"};

    private Item item;

    private EditText editText;
    private EditText editNumber;
    private ArrayAdapter<String> spinListAdapter;
    private Spinner spinFood;

    public static void start(Activity caller, Item item) {
        Intent intent = new Intent(caller, ItemDetailsActivity.class);
        if (item != null) {
            intent.putExtra(EXTRA_ITEM, item);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle("Ingredient");
        
        editText = findViewById(R.id.textEdit);
        editNumber = findViewById(R.id.numberEdit);

        spinListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinList);
        spinListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinFood = (Spinner) findViewById(R.id.spinner);
        spinFood.setAdapter(spinListAdapter);

        if (getIntent().hasExtra(EXTRA_ITEM)) {
            item = getIntent().getParcelableExtra(EXTRA_ITEM);
            editText.setText(String.valueOf(item.text));
            editNumber.setText(String.valueOf(item.quantity));
            spinFood.setSelection(0);
        } else {
            item = new Item();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (editText.getText().length() > 0 && editNumber.getText().length() > 0) {
                    item.text = editText.getText().toString();
                    item.quantity = Integer.parseInt(String.valueOf(editNumber.getText()));
                    item.dishType = spinFood.getSelectedItem().toString();
                    item.timestamp = System.currentTimeMillis();

                    if(getIntent().hasExtra(EXTRA_ITEM)) { // проверяем был ли у нас такой объект, если был, то обновляем его
                        App.getInstance().getItemDao().update(item);
                    } else { // если нет, создаем новый
                        App.getInstance().getItemDao().insert(item);
                    }
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
