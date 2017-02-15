package com.fjz.androidlittlesamples.backgroundjobscheduler;

import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.orhanobut.logger.Logger;

/**
 * Created by fjz on 15/02/2017.
 */

public class GcmNetworkTaskService extends GcmTaskService {

    @Override
    public int onRunTask(TaskParams taskParams) {
        Logger.i("on run task======");
        return 0;
    }
}
