package com.fjz.androidlittlesamples.recyclerviewitemclicklistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjz.androidlittlesamples.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RvItemClickDemo extends AppCompatActivity {

    @BindView(R.id.rv_demo)
    RecyclerView rvDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_item_click_demo);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDemo.setHasFixedSize(true);
        rvDemo.setLayoutManager(llm);
        rvDemo.setAdapter(new MyAdapter());

        rvDemo.addOnItemTouchListener(new OnItemTouchListener(rvDemo) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Logger.i("position = %d", vh.getAdapterPosition());
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private String[] data= new String[]{
                "A","B","B","B","B","B","B","B",
                "A","B","B","B","B","B","B","B",
                "A","B","B","B","B","B","B","B"
        };

        private LayoutInflater inflater;

        public MyAdapter() {
            this.inflater = LayoutInflater.from(RvItemClickDemo.this);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rv_demo_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText(data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_title)
            public TextView tvTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

            }
        }
    }
}
