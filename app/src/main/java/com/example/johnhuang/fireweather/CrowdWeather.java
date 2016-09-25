package com.example.johnhuang.fireweather;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by JohnHuang on 2016/9/25.
 */
public class CrowdWeather extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
