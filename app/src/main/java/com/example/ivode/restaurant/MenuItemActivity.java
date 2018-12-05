package com.example.ivode.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    private MenuItem menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // receive intent
        Intent intent = getIntent();
        menu = (MenuItem) intent.getSerializableExtra("menu_item");

        // draw image
        ImageView image = findViewById(R.id.item_image);
        Picasso.with(this).load(menu.getImageUrl()).into(image);

        TextView titleView = findViewById(R.id.item_title);
        TextView priceView = findViewById(R.id.item_price);
        TextView descriptionView = findViewById(R.id.item_description);

        titleView.setText(menu.getName());
        priceView.setText("€" + String.valueOf(menu.getPrice()) + "0");
        descriptionView.setText(menu.getDescription());
    }
}