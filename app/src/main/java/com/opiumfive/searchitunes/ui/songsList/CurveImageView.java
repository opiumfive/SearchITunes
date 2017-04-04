package com.opiumfive.searchitunes.ui.songsList;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class CurveImageView extends AppCompatImageView {

    private final static float OVAL_WIDTH_SCALE_FACTOR = 1.25f;
    private final static float OVAL_HEIGHT_SCALE_FACTOR = 0.5f;
    private final static float RECT_HEIGHT_SCALE_FACTOR = 0.8f;

    private Paint mBackgroundPaint;
    private Bitmap mMaskBitmap;

    public CurveImageView(Context context) {
        super(context);
        init();
    }

    public CurveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CurveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (w > 0 && h > 0 && mMaskBitmap == null) {
                prepareMask(w, h);
            }
        }
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        setLayerType(LAYER_TYPE_HARDWARE, mBackgroundPaint);
    }

    private void prepareMask(int width, int height) {
        mMaskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mMaskBitmap);
        canvas.drawARGB(0, 255, 255, 255);
        Rect rect = new Rect(0, 0, width, Math.round(height * RECT_HEIGHT_SCALE_FACTOR));
        RectF oval = new RectF(width * (1 - OVAL_WIDTH_SCALE_FACTOR), height * OVAL_HEIGHT_SCALE_FACTOR,
                width * OVAL_WIDTH_SCALE_FACTOR, height * 1.0f);
        canvas.drawOval(oval, mBackgroundPaint);
        canvas.drawRect(rect, mBackgroundPaint);
        mBackgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (mMaskBitmap != null && mBackgroundPaint != null) {
                canvas.drawBitmap(mMaskBitmap, 0, 0, mBackgroundPaint);
            }
        }
    }
}
