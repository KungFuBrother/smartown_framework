package com.smartown.framework.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MailLineView extends View {

    private int colorWidth = 4;
    private int emptyWidth = 2;

    public MailLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MailLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MailLineView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int drawLength = 0;

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        int count = 0;

        while (drawLength < getWidth()) {
            drawLength += (emptyWidth - 1) * getHeight();
            count++;
            if (count % 2 == 1) {
                paint.setColor(Color.rgb(255, 134, 134));
            } else {
                paint.setColor(Color.rgb(134, 194, 255));
            }
            Path path = new Path();
            path.moveTo(drawLength, getHeight());// 此点为多边形的起点
            path.lineTo(drawLength + colorWidth * getHeight() - getHeight(), getHeight());
            path.lineTo(drawLength + colorWidth * getHeight(), 0);
            path.lineTo(drawLength + getHeight(), 0);
            path.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(path, paint);
            drawLength += colorWidth * getHeight();
        }
    }

}
