package com.fjz.androidlittlesamples.backgroundjobscheduler;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by fjz on 07/04/2017.
 */

public class MyService extends IntentService {


    public MyService() {
        super("name");
    }

    public MyService(String name) {
        super(name);
    }

    @Override
    public void onHandleIntent(@Nullable Intent intent) {
        System.out.println(">>>>>>>");
    }
}
