package com.ztstech.vgsales.activitys.main.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ztstech.vgsales.R;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/8/1.
 */

public class TopBar extends FrameLayout {


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


    }
}
