package com.fjz.androidlittlesamples.backgroundjobscheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fjz.androidlittlesamples.R;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * job schedulers library in github:
 * 1. GcmNetworkManager
 * 2. https://github.com/firebase/firebase-jobdispatcher-android
 * 3. Android-Job
 */

public class JobSchedulerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        ButterKnife.bind(this);
    }


    /*@OnClick(R.id.btn_start_job)
    public void startJob(View view) {

        // Create a new dispatcher using the Google Play driver.
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        Bundle myExtrasBundle = new Bundle();
        myExtrasBundle.putString("some_key", "some_value");

        Job myJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(FirebaseJobService.class)
                // uniquely identifies the job
                .setTag("my-unique-tag")
                // one-off job
                .setRecurring(true)
                // don't persist past a device reboot
                .setLifetime(Lifetime.FOREVER)
                // start between 0 and 60 seconds from now
                .setTrigger(Trigger.executionWindow(5, 20))
                // don't overwrite an existing job with the same tag
                .setReplaceCurrent(false)
                // retry with exponential backoff
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // constraints that need to be satisfied for the job to run
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_UNMETERED_NETWORK,
                        // only run when the device is charging
                        Constraint.DEVICE_CHARGING
                )
                .setExtras(myExtrasBundle)
                .build();

        dispatcher.mustSchedule(myJob);

    }

    @OnClick(R.id.btn_stop_job)
    public void stopJob(View view) {
        // Create a new dispatcher using the Google Play driver.
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        dispatcher.cancel("my-unique-tag");

    }*/

    @OnClick(R.id.btn_start_gcmjob)
    public void startGmcNetworkManagerTask(View view) {
        GcmNetworkManager manager = GcmNetworkManager.getInstance(this);
        PeriodicTask task = new PeriodicTask.Builder()
                .setService(GcmNetworkTaskService.class)
                .setTag("my_gcmnetworkmanager_task")
                .setUpdateCurrent(true)
                .setPeriod(30)
                .setFlex(10)
                .setRequiredNetwork(Task.NETWORK_STATE_UNMETERED)
                .setPersisted(true)
                .setRequiresCharging(false)
                .build();

        manager.schedule(task);

    }

    @OnClick(R.id.btn_stop_gcmjob)
    public void stopGmcNetworkManagerTask(View view) {
        GcmNetworkManager manager = GcmNetworkManager.getInstance(this);
        manager.cancelTask("my_gcmnetworkmanager_task", GcmNetworkTaskService.class);
    }
}
