package com.marcouberti.sunsetswatchface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.wearable.view.CircledImageView;
import android.util.AttributeSet;

/**
 * Created by Marco on 05/09/15.
 */
public class CustomCircledImageView extends CircledImageView {

    Paint paint = new Paint();
    Paint circlePaint = new Paint();
    public int bitmapResource;
    Bitmap bitmap, front;

    public void setBitmapResource(int bitmapResource) {
        this.bitmapResource = bitmapResource;
        bitmap = BitmapFactory.decodeResource(getResources(), bitmapResource);
    }

    public CustomCircledImageView(Context context) {
        super(context);
        initPaint(context);
    }

    public CustomCircledImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public CustomCircledImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint(context);
    }

    private void initPaint(Context ctx){
        //setBitmapResource(R.drawable.preview_africa);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        circlePaint.setAntiAlias(true);
        circlePaint.setFilterBitmap(true);
        circlePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.config_activity_background));

        float R = this.getCircleRadius()-4;

        if(front != null && !front.isRecycled()) {
            front.recycle();
            front = null;
        }
        front = Bitmap.createBitmap(this.getMeasuredWidth(), this.getMeasuredWidth(), Bitmap.Config.ARGB_8888);

        Canvas frontCanvas = new Canvas(front);

        if(R > front.getWidth()/2) R = front.getWidth()/2;
        frontCanvas.drawCircle(front.getWidth() / 2, front.getHeight() / 2, R, paint);
        frontCanvas.drawBitmap(bitmap, new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()), new Rect(0,0,this.getMeasuredWidth(),this.getMeasuredWidth()), circlePaint);

        if(front!= null) {
            canvas.drawBitmap(front, new Rect(0, 0, front.getWidth(), front.getHeight()), new Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredWidth()), paint);
        }
/*
        float R = this.getCircleRadius();
        if(R > this.getMeasuredWidth()/2) R = this.getMeasuredWidth()/2;
        canvas.drawCircle(this.getMeasuredWidth()/2, this.getMeasuredHeight()/2, R, circlePaint);
        */
    }


}
