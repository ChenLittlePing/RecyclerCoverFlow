package com.recycler.coverflow;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

public class MirrorView extends android.support.v7.widget.AppCompatImageView {

    public MirrorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MirrorView(Context context) {
        this(context, null, 0);
    }

    public MirrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (getDrawable() != null) {
            doReflection(((BitmapDrawable) getDrawable()).getBitmap());
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        doReflection(bm);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        if (drawable == null) return;
        if (drawable instanceof GlideBitmapDrawable) {
            Bitmap bitmap = ((GlideBitmapDrawable) drawable).getBitmap();
            if (bitmap != null) doReflection(bitmap);
        } else {
            super.setImageDrawable(drawable);
        }
    }

    @Override
    public void setImageResource(int resId) {
        doReflection(BitmapFactory.decodeResource(getResources(), resId));
    }

    private void doReflection(Bitmap originalImage) {
        if (originalImage == null) return;
        final int reflectionGap = 4;
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);

        canvas.drawBitmap(originalImage, 0, 0, null);

        Paint defaultPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,
                originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
                + reflectionGap, 0x70ffffff, 0x00000000,
                Shader.TileMode.MIRROR);

        paint.setShader(shader);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);
        super.setImageDrawable(new BitmapDrawable(getResources(), bitmapWithReflection));
    }

}