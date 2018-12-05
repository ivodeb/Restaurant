package com.example.ivode.restaurant;

import java.util.ArrayList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menuItems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menuItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent,
                    false);
        }

        MenuItem menu_item = menuItems.get(position);

        ImageView image = convertView.findViewById(R.id.image);
        Picasso.with(getContext()).load(menu_item.getImageUrl()).into(image);

        TextView titleView = convertView.findViewById(R.id.title);
        TextView priceView = convertView.findViewById(R.id.price);

        titleView.setText(menu_item.getName());
        priceView.setText("€" + String.valueOf(menu_item.getPrice()) + "0");

        return convertView;
    }
}