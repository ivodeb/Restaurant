package com.example.ivode.restaurant;

import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/** Request menu items from server. */
public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private String category_name;
    private ArrayList<MenuItem> menu_items = new ArrayList<>();

    // callback from another activity
    Callback activity;
    public interface Callback {
        void gotMenus(ArrayList<MenuItem> menu_items);
        void gotMenusError(String message);
    }

    // constructor
    MenuItemsRequest(Context context, String category) {
        this.context = context;
        this.category_name = category;
    }

    void getMenus(Callback activity) {
        this.activity = activity;
        String url = "https://resto.mprog.nl/menu?category=" + category_name;

        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception error) {
            Log.e("req_error", error.getMessage());
        }
    }

    // retrieve parameters from JSONObject, process into menu items and pass back to activity
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
                String price = "€" + menuObject.getString("price") + "0";

                // create menu_item and add to array list
                MenuItem menu_item = new MenuItem(name, description, imageURL, category, price);
                menu_items.add(menu_item);
            }
        }
        catch(JSONException error) {
            Log.e("req_error", error.getMessage());
        }

        activity.gotMenus(menu_items);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenusError(error.getMessage());
    }
}
