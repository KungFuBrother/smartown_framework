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
public class MetroCircleLoadingView1 extends View {

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        }
    };

    /**
     * 半圈时间
     */
    private static final int[] colorList = {Color.RED, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.MAGENTA, Color.YELLOW};
    /**
     * 半圈时间
     */
    private static final int halfTime = 80;
    /**
     * 等待时间
     */
    private static final int waitTime = 10;
    /**
     * 点总数
     */
    private static final int pointCount = 7;
    /**
     * 一圈的长度（角度）
     */
    private static final double roundLength = 450.0;
    /**
     * 加速度（角度）
     */
    private double acceleration = roundLength / Math.pow(halfTime, 2);
    private List<ModelPoint> points = new ArrayList<>();

    private double pointRadius = 0;
    private double radius = 0;
    private double centerX = 0;
    private double centerY = 0;
    private int runTime = 0;

    public MetroCircleLoadingView1(Context context) {
        super(context);
        init();
    }

    public MetroCircleLoadingView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MetroCircleLoadingView1(Context context, AttributeSet attrs, int defStyleAttr) {
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
            points.add(new ModelPoint(i * waitTime, colorList[i]));
        }
    }

    private void measure() {
        int viewHeight = getMeasuredHeight();
        int viewWidth = getMeasuredWidth();
        centerX = (double) viewWidth / 2.0;
        centerY = (double) viewHeight / 2.0;

        if (viewHeight < viewWidth) {
            pointRadius = (double) viewHeight / 16.0;
            radius = ((double) viewHeight - pointRadius * 2.0) / 2.0;
        } else {
            pointRadius = (double) viewWidth / 16.0;
            radius = ((double) viewWidth - pointRadius * 2.0) / 2.0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        runTime++;

        for (int i = 0; i < points.size(); i++) {
            paint.setColor(points.get(i).getColor());
            double position = getPosition(points.get(i).getDelayTime());
            if (position > 0) {
                double pointX = centerX + Math.cos(Math.toRadians(position - 90)) * radius;
                double pointY = centerY + Math.sin(Math.toRadians(position - 90)) * radius;
                RectF rectF = new RectF((float) (pointX - pointRadius), (float) (pointY - pointRadius), (float) (pointX + pointRadius), (float) (pointY + pointRadius));
                canvas.drawArc(rectF, 0.0f, 360.0f, true, paint);
            }
            handler.sendEmptyMessageDelayed(1, 10);
        }
    }

    private double getPosition(int delayTime) {
        int time = runTime - delayTime;
        if (time <= 0) {
            return 0;
        }
        int round = time / (2 * halfTime);
        double position = roundLength * round;
        int remainTime = time % (2 * halfTime);
        if (remainTime > halfTime) {
            double speed = acceleration * halfTime;
            position += acceleration * Math.pow(halfTime, 2) / 2.0 + speed * (remainTime - halfTime) - acceleration * Math.pow(remainTime - halfTime, 2) / 2.0;
        } else {
            position += acceleration * Math.pow(remainTime, 2) / 2.0;
        }
        return position;
    }

    public class ModelPoint {

        private int delayTime = 0;
        private int color = 0;

        public ModelPoint(int delayTime, int color) {
            this.color = color;
            this.delayTime = delayTime;
        }

        public int getColor() {
            return color;
        }

        public int getDelayTime() {
            return delayTime;
        }
    }

}
