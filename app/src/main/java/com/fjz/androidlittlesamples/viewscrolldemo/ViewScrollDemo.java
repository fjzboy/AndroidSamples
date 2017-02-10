package com.fjz.androidlittlesamples.viewscrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fjz.androidlittlesamples.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewScrollDemo extends AppCompatActivity {

    @BindView(R.id.ll_container)
    LinearLayout container;
    @BindView(R.id.tv_cursor)
    TextView tvCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll_demo);
        ButterKnife.bind(this);

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                boolean result = false;

                Logger.i("event action=%s, x=%f, y=%f", event.getAction(), event.getX(), event.getY());
                int x = (int) event.getX();
                int y = (int) event.getY();
                int w = tvCursor.getWidth();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (isPointInViews(tvCursor, event)) {
                            result = true;

                        }
                        break;

                    case MotionEvent.ACTION_MOVE:

                        result = true;

                        if (x <= w/2) {
                            x = w/2;
                            result = false;
                        }

                        if (x >= container.getWidth()-w/2) {
                            x = container.getWidth()-w/2;
                            result = false;
                        }

                        container.scrollTo(-(x - w / 2), 0);

                        break;

                    case MotionEvent.ACTION_UP:
                        Logger.i("scroll x = %d", container.getScrollX());
                        int scrollX = container.getScrollX();
                        if (scrollX-w/2>-container.getWidth()/2) {
                            container.scrollTo(0, 0);
                        } else {
                            container.scrollTo(-(container.getWidth() - w), 0);
                        }

                        break;
                }


                Logger.i("result = %b", result);
                return result;
            }
        });
    }

    public static boolean isPointInViews(View v, MotionEvent event) {
        if (v != null) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();

            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {

                return true;
            } else {
                return false;
            }
        }
        return false;
    }


}
