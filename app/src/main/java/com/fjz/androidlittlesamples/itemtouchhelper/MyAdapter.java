package com.fjz.androidlittlesamples.itemtouchhelper;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.ItemTouchHelperRvItemLayoutBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fjz on 02/06/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    private List<String> data = new ArrayList<>();

    Context context;
    LayoutInflater inflater;

    public MyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < 30; i++) {
            data.add("String data:"+i);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTouchHelperRvItemLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_touch_helper_rv_item_layout, parent, false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemTouchHelperRvItemLayoutBinding binding;

        public ViewHolder(ItemTouchHelperRvItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(String data){
            binding.setTitle(data);
        }

    }

    public void onDismiss(int pos) {
        data.remove(pos);
        notifyItemRemoved(pos);
    }
    public void onMoved(int pos, int targetPos) {
        Collections.swap(data, pos, targetPos);
        notifyItemMoved(pos, targetPos);
    }
}
