package com.fjz.androidlittlesamples.databindingdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fjz.androidlittlesamples.R;
import com.fjz.androidlittlesamples.databinding.RvBinding;
import com.fjz.androidlittlesamples.databinding.RvItemBinding;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Random;

public class BindingRecyclerViewActivity extends AppCompatActivity {

    private ArrayList<Person> persons = new ArrayList<>();

    private RvBinding rvBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding_recycler_view);

        Random r = new Random(10);
        for (int i = 0; i < 26; i++) {
            persons.add(new Person("Name:"+i, r.nextInt(23)));
        }

        rvBinding.activityBindingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rvBinding.activityBindingRecyclerView.setAdapter(new MyAdapter());

        rvBinding.setHandler(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = new Random().nextInt(persons.size() - 1);
                persons.get(pos).setAge(new Random().nextInt(19));
                rvBinding.activityBindingRecyclerView.getAdapter().notifyItemChanged(pos);
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        LayoutInflater inflater;
        public MyAdapter() {
            inflater = LayoutInflater.from(BindingRecyclerViewActivity.this);
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            RvItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.rv_item_layout, parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.binding(persons.get(position));
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            RvItemBinding itemBinding;

            public ViewHolder(RvItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
                this.itemBinding.setHandler(changeHandler);
            }

            public void binding(Person person) {
                itemBinding.setPerson(person);
            }

            private View.OnClickListener changeHandler = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logger.i("Clicked item:" + getAdapterPosition());
                }
            };
        }
    }


}
