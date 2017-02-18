package com.fjz.androidlittlesamples;

import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.Logger;

/**
 * Created by fjz on 10/02/2017.
 */

public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("ALS");
    }
}
