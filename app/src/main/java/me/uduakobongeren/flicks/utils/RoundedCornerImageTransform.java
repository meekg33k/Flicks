package me.uduakobongeren.flicks.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * @author Uduak Obong-Eren
 * @since 9/15/17.
 *
 * https://stackoverflow.com/questions/22363515/rounded-corners-with-picasso
 */

public class RoundedCornerImageTransform implements Transformation {

    private final int radius;
    private final int margin;

    public RoundedCornerImageTransform(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    @Override
    public Bitmap transform(Bitmap source){
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP));

        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),
                source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth()
                - margin, source.getHeight() - margin), radius, radius, paint);

        if (source != bitmap) {
            source.recycle();
        }

        return bitmap;
    }


    @Override
    public String key(){
        return "rounded_corner_image";
    }
}
