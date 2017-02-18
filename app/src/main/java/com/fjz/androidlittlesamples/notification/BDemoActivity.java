package com.fjz.androidlittlesamples.notification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fjz.androidlittlesamples.R;

public class BDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdemo);


        ((TextView)findViewById(R.id.tv)).setText(getIntent().getStringExtra("data"));
    }
}
