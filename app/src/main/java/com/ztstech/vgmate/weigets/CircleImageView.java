package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private Path path = new Path();

    public CircleImageView(Context context) {
        this(context, null, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float width = getWidth();
        float height = getHeight();

        float small = width < height ? width : height;
        path.reset();
        path.addCircle(width / 2, height / 2, small / 2, Path.Direction.CCW);
//        canvas.clipPath(path, Region.Op.INTERSECT);
        canvas.clipPath(path);



        super.onDraw(canvas);
    }
}
