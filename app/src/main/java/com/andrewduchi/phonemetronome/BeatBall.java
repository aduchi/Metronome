package com.andrewduchi.phonemetronome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by aduchi on 2/8/15.
 */
public class BeatBall extends View {
    private int mTextPos;
    private float mXCenter;
    private float mYCenter;
    private float mRadius;
    private int mBpm;
    int MAX_ALPHA=255;

    public BeatBall(Context context, AttributeSet attrs){
        super(context, attrs);
        mBpm = 0;
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BeatBall,
                0, 0);


        try {
            mTextPos = a.getInteger(R.styleable.BeatBall_labelPosition, 0);
        } finally {
            a.recycle();
        }
    }

    public void setBPM(int bpm){
        mBpm = bpm;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());


        float ww = (float)w - xpad;
        float hh = (float)h - ypad;

        // Figure out how big we can make the pie.
        float diameter = Math.min(ww, hh);
        mXCenter = (diameter+xpad)/2.0f;
        mYCenter =  (diameter+ypad)/2.0f;
        mRadius = diameter/2.0f;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint myPaint = new Paint();
        myPaint.setColor(Color.argb(Math.min(mBpm,MAX_ALPHA),0,0,0));
        canvas.drawCircle(mXCenter, mYCenter,mRadius,myPaint);

    }
}
