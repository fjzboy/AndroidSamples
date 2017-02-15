package com.fjz.androidlittlesamples.metadata;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fjz.androidlittlesamples.LauncherActivity;
import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.ActivityReadMetadataBinding;

public class ReadMetadataActivity extends AppCompatActivity {

    ActivityReadMetadataBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_read_metadata);
        ComponentName cn = new ComponentName(getPackageName(), LauncherActivity.class.getName());

        try {
            ActivityInfo info = getPackageManager().getActivityInfo(cn, PackageManager.GET_META_DATA);
            String actMeta = info.metaData.getString("activity_metadata");
            binding.setActivityMetadata(actMeta);

            cn = new ComponentName(getPackageName(), MetadataService.class.getName());
            ServiceInfo si = getPackageManager().getServiceInfo(cn, PackageManager.GET_META_DATA);
            String serviceMeta = si.metaData.getString("service_metadata");
            binding.setServiceMetadata(serviceMeta);

            cn = new ComponentName(getPackageName(), MetadataReceiver.class.getName());
            ActivityInfo ai = getPackageManager().getReceiverInfo(cn, PackageManager.GET_META_DATA);
            String receiverMeta = ai.metaData.getString("receiver_metadata");
            binding.setReceiverMetadata(receiverMeta);

            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String appMetadata = appInfo.metaData.getString("application_metadata");

            binding.setAppMetadata(appMetadata);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
