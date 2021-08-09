package ru.avem.cookmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;

import static androidx.core.view.ViewCompat.setNestedScrollingEnabled;
import static ru.avem.cookmate.MainActivity.SELECTED_INGREDIENTS;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        ArrayList<String> selectedIngredients = intent.getStringArrayListExtra(SELECTED_INGREDIENTS);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<String> arrayList = new ArrayList<String>();

        ListView listView = findViewById(R.id.list_recepts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, selectedIngredients);

        AdapterView.OnItemClickListener itemClickListener = (parent, view, position, id) -> {
            //TODO
        };
        listView.setOnItemClickListener(itemClickListener);

        listView.setAdapter(adapter);

//        for (int i = 0; i < selectedIngredients.size(); i++) {
//            adapter.add(selectedIngredients.get(i));
//            adapter.notifyDataSetChanged();
//        }
    }
}