package com.jbstore.o2o.lovelibrary;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by gwy on 2017/7/4.
 */

public class LoveView extends View implements View.OnClickListener {
    private static final float C = 0.551915024494f;
    private long duaration;
    private int selectColor;
    private int normalColor;
    private int strokeColor;
    private float strokeWidth;
    private Paint paint;
    private Path path;
    boolean isSeclect;
    private float width, height, radius=0;
    static Context mContext;
    private float tempR;

    public LoveView(Context context) {
        this(context, null);
    }

    public LoveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        setClickable(true);
        setOnClickListener(this);
        TintTypedArray typedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.LoveView);
        duaration = typedArray.getInteger(R.styleable.LoveView_duration, 1500);
        selectColor = typedArray.getColor(R.styleable.LoveView_selectedColor, Color.RED);
        normalColor = typedArray.getColor(R.styleable.LoveView_normalColor, Color.CYAN);
        strokeWidth = typedArray.getDimension(R.styleable.LoveView_strokeWidth, 2);
        strokeColor = typedArray.getColor(R.styleable.LoveView_strokeColor, Color.BLACK);
        typedArray.recycle();
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("gao", "onMeasure: " + getWidth());

        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSize);
        }else if(heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,200);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        Log.e("gao", "onDraw: "+getWidth());
        height = getHeight();
        radius = Math.min(width, height) / 3;
        tempR = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isSeclect) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(selectColor);
        } else {
            paint.setColor(normalColor);
            paint.setStrokeWidth(dip2px(strokeWidth));
        }
        path.reset();
        canvas.translate(width/2, height/2);
        path.moveTo(0, -radius + radius / 3 * 2);
        path.cubicTo(-radius * C, -radius, -radius, -radius * C, -radius, 0);
        path.cubicTo(-radius, radius * C, -radius * C + radius / 3 * 2, radius, 0, radius);
        path.cubicTo(radius * C - radius / 3 * 2, radius, radius, radius * C, radius, 0);
        path.cubicTo(radius, -radius * C, radius * C, -radius, 0, -radius + radius / 3 * 2);
        path.close();
        canvas.drawPath(path, paint);

    }

    /**
     * dp转成为 px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        isSeclect = !isSeclect;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1.5f, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                radius = tempR * (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(duaration);
        valueAnimator.start();
    }
}
