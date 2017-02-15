package com.fjz.androidlittlesamples.metadata;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MetadataService extends Service {
    public MetadataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
