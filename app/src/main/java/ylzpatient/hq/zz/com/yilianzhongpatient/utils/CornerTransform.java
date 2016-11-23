package ylzpatient.hq.zz.com.yilianzhongpatient.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2016-10-11.
 *
 * 图形圆角处理,用于picasso处理图片加圆角
 *     Picasso.with(this).load(url).transform(new CornerTransform()).into(ivAvatar);
 */
public class CornerTransform implements Transformation {
    private final int radius;
    private final int margin;  // dp

    // radius is corner radii in dp
    // margin is the board in dp
    public CornerTransform(final int radius, final int margin) {
        this.radius = radius;
        this.margin = margin;
    }

    public CornerTransform() {
        this.radius = 20;
        this.margin = 15;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawRoundRect(new RectF(margin, margin, source.getWidth() - margin, source.getHeight() - margin), radius, radius, paint);

        if (source != output) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return "roundcorner";
    }
}
