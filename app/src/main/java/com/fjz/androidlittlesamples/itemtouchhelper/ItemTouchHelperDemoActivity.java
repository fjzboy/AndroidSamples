package com.fjz.androidlittlesamples.itemtouchhelper;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.ActivityItemTouchHelperDemoBinding;

public class ItemTouchHelperDemoActivity extends AppCompatActivity {

    ActivityItemTouchHelperDemoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_touch_helper_demo);

        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyAdapter adapter = new MyAdapter(this);

        binding.rv.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        helper.attachToRecyclerView(binding.rv);
    }


}
