package com.smartown.framework.widget;

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
public class MetroLoadingView extends View {

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            invalidate();
        }
    };

    private double acceleration = 360.0 / Math.pow(2000, 2);
    private List<ModelPoint> points = new ArrayList<>();

    public MetroLoadingView(Context context) {
        super(context);
        init();
    }

    public MetroLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MetroLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        for (int i = 0; i < 6; i++) {
            points.add(new ModelPoint((i * 20)));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        double pointRadius = 0;
        double radius = 0;
        double centerX = 0;
        double centerY = 0;

        int viewHeight = getHeight();
        int viewWidth = getWidth();

        centerX = (double) viewWidth / 2.0;
        centerY = (double) viewHeight / 2.0;

        if (viewHeight < viewWidth) {
            pointRadius = (double) viewHeight / 16.0;
            radius = ((double) viewHeight - pointRadius * 2.0) / 2.0;
        } else {
            pointRadius = (double) viewWidth / 16.0;
            radius = ((double) viewWidth - pointRadius * 2.0) / 2.0;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < points.size(); i++) {
            double pointX = centerX + Math.cos(points.get(i).getPosition()) * radius;
            double pointY = centerY + Math.sin(points.get(i).getPosition()) * radius;
            RectF rectF = new RectF((float) (pointX - pointRadius), (float) (pointY - pointRadius), (float) (pointX + pointRadius), (float) (pointY + pointRadius));
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
            double position = 0;
            int time = activeTime % 2000;
            if (time > 1000) {
                double speed = acceleration * 1000;
                position = acceleration * Math.pow(1000, 2) / 2.0 + speed * (time - 1000) - acceleration * Math.pow(time - 1000, 2) / 2.0;
            } else {
                position = acceleration * Math.pow(time, 2) / 2.0;
            }
            if (position == 0) {
                delayTime = 100;
            }
            return position;
        }

    }

}
