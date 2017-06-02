package com.fjz.androidlittlesamples.unittest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by fjz on 11/04/2017.
 */

public class UnitTestService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            String s = intent.getStringExtra("service_action");
            System.out.println("s = " + s);
        }


        return super.onStartCommand(intent, flags, startId);
    }
}
