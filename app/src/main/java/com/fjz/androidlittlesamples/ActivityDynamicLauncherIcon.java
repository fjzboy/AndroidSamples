package com.fjz.androidlittlesamples;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityDynamicLauncherIcon extends AppCompatActivity {

    PackageManager mPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_launcher_icon);
        ButterKnife.bind(this);
        mPm = getApplicationContext().getPackageManager();
    }

    @OnClick(R.id.btn_change_icon)
    public void changeIcon(View view) {
        ComponentName originalCN = getComponentName();
        ComponentName newCN = new ComponentName(getBaseContext(), "com.fjz.androidlittlesamples.ActivityAppAlias");
        setComponentEnable(newCN);
        setComponentDisenable(originalCN);
    }

    /**
     * 开启
     */
    private void setComponentEnable(ComponentName componentEnable) {
        mPm.setComponentEnabledSetting(componentEnable,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 关闭
     */
    private void setComponentDisenable(ComponentName componentEnable) {
        mPm.setComponentEnabledSetting(componentEnable,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
