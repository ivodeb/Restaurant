package com.example.ivode.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private String category_name;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    Callback activity;

    public interface Callback {
        void gotMenus(ArrayList<MenuItem> menuItems);
        void gotMenusError(String message);
    }

    // constructor
    public MenuRequest(Context c, String cat_name) {
        this.context = c;
        this.category_name = cat_name;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenusError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray menuArray = response.getJSONArray("items");

            for (int i = 0; i < menuArray.length(); i++) {
                JSONObject menuObject = menuArray.getJSONObject(i);
                String name = menuObject.getString("name");
                String description = menuObject.getString("description");
                String imageURL = menuObject.getString("image_url");
                String category = menuObject.getString("category");
                float price = BigDecimal.valueOf(menuObject.getDouble("price")).floatValue();

                // create new MenuItem to store information from menuObject
                MenuItem menu_item = new MenuItem(name, description, imageURL, category, price);
                menuItems.add(menu_item);
            }
        }
        catch(JSONException error) {
            Log.e("requestError", error.getMessage());
        }

        activity.gotMenus(menuItems);
    }

    void getMenus(Callback act) {
        this.activity = act;
        String url = "https://resto.mprog.nl/menu?category=" + category_name;

        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception error) {
            Log.e("requestError", error.getMessage());
        }
    }
}
