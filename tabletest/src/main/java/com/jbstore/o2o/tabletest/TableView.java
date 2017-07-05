package com.jbstore.o2o.tabletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gwy on 2017/7/5.
 */

public class TableView extends View {
    Paint paint;
    Path path;
    int width = 800;
    int height = 1000;
    Rect rect = new Rect();
    int anIntX = 5;
    int anIntY = 50;
    Paint circlePaint;
    Context mContext;
    List<Point> data = new ArrayList<>();
    private int offInt = 10;//坐标指示数字的偏移值
    private int heightX;//X轴坐标指示数字的高度
    private int widthY;//Y轴坐标指示数字的宽度
    private int XCount = 7;
    private int YCount = 7;
    private int XLength;
    private int YLength;
    private float radius = 3;

    public TableView(Context context) {
        this(context, null);
    }

    public TableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TableView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(400), dip2px(400));
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(400), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, dip2px(400));
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(50);
        path = new Path();
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Color.BLUE);
        paint.getTextBounds("350", 0, 2, rect);
        widthY = rect.width();
        heightX = rect.height();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mWidth = width - widthY * 2 + dip2px(offInt);
        int mHeight = height - heightX * 2 - dip2px(offInt);
        XLength = mWidth / XCount;
        YLength = mHeight / YCount;
        canvas.translate(widthY + dip2px(offInt) * 2, height - heightX - dip2px(offInt));
        path.lineTo(mWidth, 0);
        path.moveTo(0, 0);
        path.lineTo(0, -mHeight);
        canvas.drawPath(path, paint);
        for (int i = 0; i < XCount; i++) {
            String str = i * anIntX + "";
            paint.getTextBounds(str, 0, str.length(), rect);
            if (i == XCount - 1) {
                canvas.drawText(str + "(天)", i * XLength - rect.width() / 2, rect.height() + dip2px(offInt) / 2, paint);
            } else
                canvas.drawText(str, i * XLength - rect.width() / 2, rect.height() + dip2px(offInt) / 2, paint);
        }
        for (int i = 1; i < YCount; i++) {
            String str = i * anIntY + "";
            paint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str, -rect.width() - dip2px(offInt) / 2, -YLength * i + rect.height() / 2, paint);
        }
        String str = "(天)";
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(str, -rect.width() - dip2px(offInt) / 2, -mHeight + rect.height(), paint);
        path.moveTo(0, 0);
        drawMsg(canvas);
        canvas.drawPath(path, paint);
    }

    private void drawMsg(Canvas canvas) {
        for (int i = 0; i < data.size(); i++) {
            Point point = data.get(i);
            int x = point.x / anIntX;
            int y = point.y / anIntY;
            path.lineTo(x * XLength, -y * YLength);
            canvas.drawCircle(x * XLength, -y * YLength, dip2px(radius), circlePaint);
            String str = point.y + "";
            paint.getTextBounds(str, 0, str.length(), rect);
            canvas.drawText(str, x * XLength - rect.width() / 2, -y * YLength, paint);

        }
    }


    public void setData(List<Point> list) {
        data.addAll(list);
        invalidate();
    }

    public void setCoorCount(int xc, int yc) {
        XCount = xc;
        YCount = yc;
    }

    public void setStepCount(int stepX, int stepY) {
        anIntX = stepX;
        anIntY = stepY;
    }


    /**
     * dp转成为 px
     *
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
