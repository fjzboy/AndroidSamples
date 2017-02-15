package com.fjz.androidlittlesamples.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.ActivityDatabindingBinding;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabindingDemoActivity extends AppCompatActivity {

    ActivityDatabindingBinding binding;
    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        binding.setState(state);
        binding.setName("test");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_state)
    public void onClick(View view) {
        binding.setName("feaadf");
        state = !state;
        binding.setState(state);
    }
}
