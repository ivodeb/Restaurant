package com.example.ivode.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/** Retrieve menu item clicked on and show its details. */
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem menu_item = (MenuItem) intent.getSerializableExtra("menu_item");

        TextView title_view = findViewById(R.id.item_title);
        TextView price_view = findViewById(R.id.item_price);
        TextView description_view = findViewById(R.id.item_description);
        ImageView img = findViewById(R.id.item_image);

        title_view.setText(menu_item.getName());
        price_view.setText(menu_item.getPrice());
        description_view.setText(menu_item.getDescription());
        Picasso.with(this).load(menu_item.getImageUrl()).into(img);
    }
}