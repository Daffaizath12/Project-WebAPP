package com.example.project_webapp.Config;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DBHelper {

    private static DBHelper instance;
    private RequestQueue requestQueue;
    private static Context context;

    private DBHelper(Context context) {
        DBHelper.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void executeQuery(String url, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error);
            }
        });
        requestQueue.add(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String result);
        void onFailure(VolleyError error);
    }
}
