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

import java.util.ArrayList;

/** Request categories from API. */
public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<String> categories = new ArrayList<>();
    Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }
    CategoriesRequest(Context context) {
        this.context = context;
    }

    void getCategories(Callback activity) {
        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest (url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch(Exception error) {
            Log.e("req_error", error.getMessage());
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray categories_array = response.getJSONArray("categories");

            for (int i = 0; i < categories_array.length(); i++) {
                categories.add(categories_array.getString(i));
            }
        }
        catch(JSONException error) {
            Log.e("req_error", error.getMessage());
        }
        activity.gotCategories(categories);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }
}
