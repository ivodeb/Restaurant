package com.example.ivode.restaurant;

import java.util.ArrayList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // receive intent
        Intent intent = getIntent();
        String retrievedCategory = intent.getStringExtra("category_name");

        MenuRequest x = new MenuRequest(this, retrievedCategory);
        x.getMenus(this);
        Toast.makeText(this, retrievedCategory, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotMenus(ArrayList<MenuItem> menuItems) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);
        ListView listView = findViewById(R.id.menu_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener());
    }

    @Override
    public void gotMenusError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem menu_item = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("menu_item", menu_item);
            startActivity(intent);
        }
    }
}