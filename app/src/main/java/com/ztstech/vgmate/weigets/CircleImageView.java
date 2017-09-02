package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.ztstech.vgmate.R;

/**
 * Created by zhiyuan on 2017/8/25.
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

    private Path path = new Path();
    private Paint paint = new Paint();

    private int mBorderColor;
    private float mBorderWidth;
    private boolean showBroder = true;


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

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);


        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.CircleImageView);
            mBorderColor = typedArray.getColor(R.styleable.CircleImageView_borderColor,
                    ContextCompat.getColor(context, R.color.color_50_109));
            showBroder = typedArray.getBoolean(R.styleable.CircleImageView_drawBorder, true);
            mBorderWidth = typedArray.getDimension(R.styleable.CircleImageView_borderWidth, 2);
            typedArray.recycle();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float width = getWidth();
        float height = getHeight();

        float small = width < height ? width : height;
        path.reset();
        path.addCircle(width / 2, height / 2, small / 2, Path.Direction.CCW);
        canvas.clipPath(path);

        super.onDraw(canvas);
        if (showBroder) {
            paint.setColor(mBorderColor);
            paint.setStrokeWidth(mBorderWidth);
            canvas.drawCircle(width / 2, height / 2, small / 2, paint);

        }
    }
}
