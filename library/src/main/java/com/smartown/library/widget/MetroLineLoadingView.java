package com.smartown.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiger on 2016-01-11.
 */
public class MetroLineLoadingView extends View {

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        }
    };

    /**
     * 半圈时间
     */
    private static final int time = 80;
    /**
     * 等待时间
     */
    private static final int waitTime = 10;
    /**
     * 点总数
     */
    private static final int pointCount = 6;
    private double acceleration = 0;
    private List<ModelPoint> points = new ArrayList<>();

    private double pointWidth = 0;

    public MetroLineLoadingView(Context context) {
        super(context);
        init();
    }

    public MetroLineLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MetroLineLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measure();
    }

    private void init() {
        for (int i = 0; i < pointCount; i++) {
            points.add(new ModelPoint((i * waitTime)));
        }
    }

    private void measure() {
        int viewHeight = getMeasuredHeight();
        int viewWidth = getMeasuredWidth();

        pointWidth = (double) viewHeight;
        acceleration = ((double) viewWidth + pointWidth) / Math.pow(time, 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < points.size(); i++) {
            double position = points.get(i).getPosition();
            RectF rectF = new RectF((float) (position - pointWidth), 0, (float) (position), (float) (pointWidth));
            canvas.drawArc(rectF, 0.0f, 360.0f, true, paint);
            handler.sendEmptyMessageDelayed(1, 1);
        }
    }

    public class ModelPoint {

        private int delayTime = 0;
        private int activeTime = 0;

        public ModelPoint(int delayTime) {
            this.delayTime = delayTime;
        }

        public double getPosition() {
            if (delayTime > 0) {
                delayTime--;
                return 0;
            }
            activeTime++;
            double position = 0;
            if (activeTime > time) {
                double speed = acceleration * time;
                position = acceleration * Math.pow(time, 2) / 2.0 + speed * (activeTime - time) - acceleration * Math.pow(activeTime - time, 2) / 2.0;
            } else {
                position = acceleration * Math.pow(activeTime, 2) / 2.0;
            }
            if (activeTime == 2 * time) {
                activeTime = 0;
                delayTime = waitTime * (pointCount - 1);
            }
            return position;
        }

    }

}
