package com.fjz.androidlittlesamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopWinAnimActivity extends AppCompatActivity {

    @BindView(R.id.tv_sample)
    TextView tvSample;

    PopupWindow popWin;

    @BindView(R.id.rl_pop_container)
    RelativeLayout rlContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_win_anim);
        ButterKnife.bind(this);
    }

    private void showPopWin() {

        View contentView = LayoutInflater.from(this).inflate(R.layout.sample_pop_win_layout, null);
        popWin = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, 100, false);

        popWin.setTouchable(true);
        popWin.setOutsideTouchable(true);
        popWin.setAnimationStyle(R.style.popupAnimation);
        //popWin.showAtLocation(rlContainer, Gravity.BOTTOM, 0, 0);
        popWin.showAsDropDown(rlContainer, 0, -400);
    }

    private void dismissPopwin() {
        popWin.dismiss();
        popWin = null;
    }

    public void sampleClick(View view) {

        if (popWin == null) {
            showPopWin();
        } else {
            dismissPopwin();
        }
    }
}
