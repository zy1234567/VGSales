package com.ztstech.vgmate.weigets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ztstech.vgmate.R;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class TopBar extends FrameLayout {

    private ImageView imgRight;

    public TopBar(@NonNull Context context) {
        super(context);
        init();
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View topView = LayoutInflater.from(getContext()).inflate(R.layout.layout_top_bar,
                this, true);
        imgRight = topView.findViewById(R.id.btn_new_top_bar_right);

    }

    public ImageView getRightImage() {
        return imgRight;
    }
}
