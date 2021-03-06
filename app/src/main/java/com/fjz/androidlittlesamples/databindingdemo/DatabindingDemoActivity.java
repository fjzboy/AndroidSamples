package com.fjz.androidlittlesamples.databindingdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.ActivityDatabindingBinding;

public class DatabindingDemoActivity extends AppCompatActivity {

    ActivityDatabindingBinding binding;
    private boolean state = false;

    String[] imgUrls = {
            "https://deow9bq0xqvbj.cloudfront.net/dir-logo/311963/311963_300x300.jpg",
            "https://deow9bq0xqvbj.cloudfront.net/dir-logo/280842/280842_300x300.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);

        binding.setState(state);
        binding.setName("test");
        binding.setImgUrl(imgUrls[0]);
        binding.setHandler(handler);
    }

    private View.OnClickListener handler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_state:
                    binding.setName("feaadf");
                    state = !state;
                    binding.setState(state);
                    if(binding.getImgUrl().equals(imgUrls[0])) {
                        binding.setImgUrl(imgUrls[1]);
                    } else {
                        binding.setImgUrl(imgUrls[0]);
                    }
                    break;
                case R.id.btn_enter_rv:
                    startActivity(new Intent(DatabindingDemoActivity.this, BindingRecyclerViewActivity.class));
                    break;
            }
        }
    };
}
