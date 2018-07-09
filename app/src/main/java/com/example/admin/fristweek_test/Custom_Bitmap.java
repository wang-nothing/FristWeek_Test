package com.example.admin.fristweek_test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class Custom_Bitmap extends TextView{

    private Paint mPaint;
    private Path mPath;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private boolean isInited = false;
    private  float TOUCH_TOLERANCE;
    private float mX,mY;

    public Custom_Bitmap(Context context) {
        super(context);
    }

    public Custom_Bitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Custom_Bitmap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInited){
            mCanvas.drawPath(mPath, mPaint);
            canvas.drawBitmap(mBitmap,0,0,null);
        }
    }
    public void initCustom(final  int bgColor, int paintstroreWidth, float touchTolerance){
        TOUCH_TOLERANCE = touchTolerance;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setDither(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(240);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(paintstroreWidth);

        mPath = new Path();
        mBitmap = Bitmap.createBitmap(getLayoutParams().width, getLayoutParams().height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        Paint paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.parseColor("#ff0717"));
        mCanvas.drawColor(bgColor);
        Bitmap photo = BitmapFactory.decodeResource(this.getResources(), R.drawable.guaguale);
        mCanvas.drawBitmap(photo,1,1,paint);
        mCanvas.drawText("刮刮看咯",getLayoutParams().width/4,getLayoutParams().height/2+15,paint);
        isInited = true;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isInited){
            return true;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                mX = event.getX();
                mY = event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(event.getX() - mX);
                float dy = Math.abs(event.getY() - mY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
                    mPath.quadTo(dx,dy,(event.getX() + mX)/2,(event.getY() + mY)/2);
                    mX = event.getX();
                    mY = event.getY();
                    invalidate();
                    break;
                }

        }
        return true;
    }
}
