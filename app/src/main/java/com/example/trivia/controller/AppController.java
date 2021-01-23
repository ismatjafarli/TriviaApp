package com.example.trivia.controller;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.trivia.data.Repository;

public class AppController extends Application{
    private static AppController instance;
    private RequestQueue requestQueue;



    public static synchronized AppController getInstance() {
        return  instance;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
