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

/** Puts a menu item with its details into a view. */
public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menu_items;

    MenuItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menu_items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent,
                    false);
        }

        MenuItem menu_item = menu_items.get(position);

        ImageView image = view.findViewById(R.id.image);
        Picasso.with(getContext()).load(menu_item.getImageUrl()).into(image);

        TextView title_view = view.findViewById(R.id.title);
        TextView price_view = view.findViewById(R.id.price);

        title_view.setText(menu_item.getName());
        price_view.setText(menu_item.getPrice());

        return view;
    }
}