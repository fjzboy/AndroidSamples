package com.fjz.androidlittlesamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fjz.androidlittlesamples.tts.TTSDemo;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LauncherActivity extends AppCompatActivity {

    @BindView(R.id.lv_samples)
    ListView lv;

    class Sample {
        private String name;
        private Class target;

        public Sample(String name, Class T) {
            this.name = name;
            this.target = T;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Class getTarget() {
            return target;
        }

        public void setTarget(Class target) {
            this.target = target;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private Sample[] samplesData = new Sample[]{
            //new Sample("动态改动App图标", ActivityDynamicLauncherIcon.class),
            new Sample("TTS Demo", TTSDemo.class),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViews();
    }



    private void initViews() {
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.samples_listitem_layout, R.id.tv_sample_name, samplesData);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.i("position = %d", position);
                startActivity(new Intent(LauncherActivity.this, samplesData[position].getTarget()));
            }
        });
    }
}
