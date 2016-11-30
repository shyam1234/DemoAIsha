package com.malviya.demoaisha;

import android.app.Application;

/**
 * Created by 23508 on 11/30/2016.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static MyApplication getInstance() {
        return  mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
