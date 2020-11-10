package com.example.clikentertainment.live;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private Paint paint = new Paint();
    private int arrow = 0;
    private int colorUp = 0;
    private int colorDown = 0;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (arrow == 1) {
            if (colorUp == Color.RED) {
                arrowUpRed(canvas);
            } else {
                arrowUpBlack(canvas);
            }
        } else if (arrow == 2) {
            if (colorDown == Color.RED) {
                arrowDownRed(canvas);
            } else {
                arrowDownBlack(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

    public void setUpColor(int color) {
        this.colorUp = color;
    }

    public void setDownColor(int color) {
        this.colorDown = color;
    }

    public void setArrowUp() {
        arrow = 1;
    }

    public void setArrowDown() {
        arrow = 2;
    }

    public void arrowUpRed(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        Path path = new Path();
        path.moveTo(20, 35);
        path.lineTo(60, 20);
        path.lineTo(100, 35);
        canvas.drawPath(path, paint);
    }

    public void arrowUpBlack(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        Path path = new Path();
        path.moveTo(20, 35);
        path.lineTo(60, 20);
        path.lineTo(100, 35);
        canvas.drawPath(path, paint);
    }

    public void arrowDownRed(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(20, 20);
        path.lineTo(60, 35);
        path.lineTo(100, 20);
        canvas.drawPath(path, paint);
    }

    public void arrowDownBlack(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        path.moveTo(20, 20);
        path.lineTo(60, 35);
        path.lineTo(100, 20);
        canvas.drawPath(path, paint);
    }
}

