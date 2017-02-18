package com.fjz.androidlittlesamples.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/2/18.
 */

public class LoadingView extends View {

    private Paint mPaint;

    private int runningIndex = 0;

    private int arcCount = 20;
    private int angleStep = 360 / arcCount;

    private int borderWidth = 10;

    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(0xff00ff00);
        
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(0x88cccccc);
        RectF rect = new RectF(borderWidth/2, borderWidth/2, getWidth()-borderWidth/2, getHeight()-borderWidth/2);


        runningIndex %= arcCount;
        int index = 0;
        while (index < arcCount) {

            if (index == runningIndex) {
                mPaint.setColor(0xffff0000);
            } else {
                mPaint.setColor(0xff00ff00);
            }

            canvas.drawArc(rect, index * angleStep, angleStep, false, mPaint);



            index += 2;
        }

        runningIndex += 2;

        postInvalidateDelayed(100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));

    }

    public static int getDefaultSize(int size, int spec) {

        int result = size;

        int specSize = MeasureSpec.getSize(spec);
        int mode = MeasureSpec.getMode(spec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;

            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }

        return result;
    }

}
