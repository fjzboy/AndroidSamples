package com.fjz.androidlittlesamples;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by fjz on 10/02/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("ALS");
    }
}
