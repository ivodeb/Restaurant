package com.example.ivode.restaurant;

import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/** Show menu for a given category with pictures and prices. Retrieve menu items from server. */
public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // get category name and make a menu request
        Intent intent = getIntent();
        String category_name = intent.getStringExtra("category_name");
        MenuItemsRequest category_request = new MenuItemsRequest(this, category_name);
        category_request.getMenus(this);
    }

    // show details for a selected menu item
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem menu_item = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menu_item", menu_item);
            startActivity(intent);
        }
    }

    @Override
    public void gotMenus(ArrayList<MenuItem> menu_items) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, R.layout.menu_item, menu_items);
        ListView menu_list = findViewById(R.id.menu_list);
        menu_list.setAdapter(adapter);
        menu_list.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    public void gotMenusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}