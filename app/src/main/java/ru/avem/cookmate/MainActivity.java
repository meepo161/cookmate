package ru.avem.cookmate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    private SettingsListAdapter settingsListAdapter;
    private ExpandableListView categoriesListView;
    private ArrayList<Category> categoriesList;

    static String SELECTED_INGREDIENTS = "SELECTED_INGREDIENTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesListView = findViewById(R.id.categories);
        categoriesList = Category.getCategories();
        settingsListAdapter = new SettingsListAdapter(this,
                categoriesList, categoriesListView);
        categoriesListView.setAdapter(settingsListAdapter);

        categoriesListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
            parent.smoothScrollToPosition(groupPosition);
            if (parent.isGroupExpanded(groupPosition)) {
                parent.collapseGroup(groupPosition);
            } else {
                parent.expandGroup(groupPosition);
            }
            return true;
        });

        categoriesListView.setOnChildClickListener((parent, view, groupPosition, childPosition, id) -> {
            onChildClick(view, groupPosition);
            return true;
        });
    }

    private void onChildClick(View view, int groupPosition) {
        CheckedTextView checkbox = view.findViewById(R.id.list_item_text_child);
        checkbox.toggle();

        View parentView = categoriesListView.findViewWithTag(categoriesList.get(groupPosition).name);
        if (parentView != null) {
            TextView sub = parentView.findViewById(R.id.list_item_text_subscriptions);

            if (sub != null) {
                Category category = categoriesList.get(groupPosition);
                if (checkbox.isChecked()) {
                    category.selection.add(checkbox.getText().toString());
                    category.selection.sort(new CustomComparator());
                } else {
                    category.selection.remove(checkbox.getText().toString());
                }
                sub.setText(category.selection.toString());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);

                ArrayList<String> selectedIngredientsList = new ArrayList<>();

                for (int i = 0; i < categoriesList.size(); i++) {
                    for (int j = 0; j < categoriesList.get(i).selection.size(); j++) {
                        selectedIngredientsList.add(categoriesList.get(i).selection.get(j));
                    }
                }
                intent.putExtra(SELECTED_INGREDIENTS, selectedIngredientsList);
                startActivity(intent);
                return true;
            case R.id.action_share:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "want to pizza?");
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
}