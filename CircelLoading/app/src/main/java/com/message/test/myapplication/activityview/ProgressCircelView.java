package com.message.test.myapplication.activityview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shengjunhao on 16/11/14.
 * <p/>
 * 仿网易邮箱，实现加载动画效果
 */
public class ProgressCircelView extends View {

    private Paint paint;
    private RectF rectF;

    /**
     * 宽
     */
    private int width;

    /**
     * 高
     */
    private int height;

    /**
     * 中心点
     */
    private Point cenPoint;

    /**
     * 画笔宽度
     */
    private int paintWith = 10;

    /**
     * 正方形边长
     */
    private int length;

    /**
     * 枫叶的默认数目
     */
    private int count = 3;

    /**
     * 枫叶的默认角度
     */
    private int degree = 90;

    /**
     * 旋转的角度
     */
    private int routeDegree = 0;

    /**
     * 冲程比例
     */
    private double distances = 0.3;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
            handler.sendEmptyMessageDelayed(0, 10);
        }
    };

    public ProgressCircelView(Context context) {
        super(context);
    }

    public ProgressCircelView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ProgressCircelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public void beginRoute() {
        handler.sendEmptyMessageDelayed(0, 100);
    }


    /**
     * 设置枫叶数目
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 设置每个枫叶的度数
     *
     * @param degree
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * 设置前进比例
     *
     * @param disTances
     */
    public void setDistance(double disTances) {

        this.distances = disTances;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        inItPaint();
        canvas.drawColor(Color.WHITE);
        drawLine(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 初始化画笔
     */
    private void inItPaint() {

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float) (1.0 + Math.sin(Math.PI * routeDegree / 180 / 1.5)*15+30));
    }


    /**
     * 绘制弧线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {

        for (int i = 0; i < count; i++) {
            canvas.drawArc(getReact(), getRouteDegree() + i * (360 / count), getDrawDegree(), false, paint);
        }

    }

    /**
     * 获取间隔度数
     *
     * @return
     */
    private int getDrawDegree() {

        if (degree * count > 270) {
            return 360 / count;
        } else {
            return degree;
        }
    }

    private RectF getReact() {
        rectF = new RectF();
        length = width > height ? height : width;
        rectF.set(Math.abs(length - width) / 2 + circelWith(),
                Math.abs(length - height) / 2 + circelWith(),
                Math.abs(length - width) / 2 + length - circelWith(),
                Math.abs(length - height) / 2 + length - circelWith());
        return rectF;
    }

    private int getRouteDegree() {
        return routeDegree++;
    }

    /**
     * 获取圆的半径
     *
     * @return
     */
    private float circelWith() {
        return (float) ((1.0 + Math.sin(Math.PI * routeDegree / 180 / 1.5)) / 2 * length / 2 * distances + paintWith);
    }



}
