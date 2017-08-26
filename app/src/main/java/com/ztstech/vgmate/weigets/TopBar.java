package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class TopBar extends FrameLayout {

    private ImageView imgRight, imgLeft;
    private TextView tvTitle;
    private String title;

    public TopBar(@NonNull Context context) {
        this(context, null, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View topView = LayoutInflater.from(getContext()).inflate(R.layout.layout_top_bar,
                this, false);
        imgRight = topView.findViewById(R.id.iv_right);
        tvTitle = topView.findViewById(R.id.tv_title);
        imgLeft = topView.findViewById(R.id.iv_left);


        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TopBar);

        int leftDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcLeft, -1);
        int rightDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcRight, -1);

        title = typedArray.getString(R.styleable.TopBar_barTitle);

        typedArray.recycle();

        if (title != null) {
            tvTitle.setText(title);
        }

        if (leftDrawableId != -1) {
            imgLeft.setImageResource(leftDrawableId);
        }
        if (rightDrawableId != -1) {
            imgRight.setImageResource(rightDrawableId);
        }

        addView(topView);

    }




    public ImageView getRightImage() {
        return imgRight;
    }

    public void setTitle(String title) {
        this.title = title;
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }
}
