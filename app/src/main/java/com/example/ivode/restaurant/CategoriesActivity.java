package com.example.ivode.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;

/** Show the available categories and send the user to a list of available dishes in that category. */
public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    private String categories[] = {"appetizers", "entrees"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        ListView menu_list = findViewById(R.id.category_list);
        menu_list.setOnItemClickListener(new OnItemClickListener());

        CategoriesRequest category_request = new CategoriesRequest(this);
        category_request.getCategories(this);
    }

    // make array adapter for categories
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> category_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                categories);
        ListView menu_list = findViewById(R.id.category_list);
        menu_list.setAdapter(category_adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category_name", categories[position]);
            startActivity(intent);
        }
    }
}