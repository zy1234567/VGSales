package com.ztstech.vgmate.weigets;

import android.app.Activity;
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
    private TextView tvTitle, tvLeft, tvRight;

    private String title, leftText, rightText;

    private boolean showBackNav = false;

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
        tvLeft = topView.findViewById(R.id.tv_left);
        tvRight = topView.findViewById(R.id.tv_right);


        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TopBar);

        int leftDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcLeft, -1);
        int rightDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcRight, -1);
        showBackNav = typedArray.getBoolean(R.styleable.TopBar_showBackNav, false);

        leftText = typedArray.getString(R.styleable.TopBar_textLeft);
        rightText = typedArray.getString(R.styleable.TopBar_textRight);

        title = typedArray.getString(R.styleable.TopBar_barTitle);

        typedArray.recycle();

        if (title != null) {
            tvTitle.setText(title);
        }

        if (leftText != null) {
            tvLeft.setText(leftText);
        }

        if (rightText != null) {
            tvRight.setText(rightText);
        }

        if (leftDrawableId != -1) {
            imgLeft.setImageResource(leftDrawableId);
        }
        if (rightDrawableId != -1) {
            imgRight.setImageResource(rightDrawableId);
        }

        if (showBackNav) {
            imgLeft.setImageResource(R.mipmap.return_nav);
            imgLeft.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity) getContext()).finish();
                }
            });
        }

        addView(topView);

    }




    public ImageView getRightImage() {
        return imgRight;
    }

    public ImageView getLeftImage() {
        return imgLeft;
    }

    public TextView getRightTextView() {
        return tvRight;
    }

    public TextView getLeftTextView() {
        return tvLeft;
    }

    public void setTitle(String title) {
        this.title = title;
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        if (tvLeft != null) {
            tvLeft.setText(leftText);
        }
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        if (tvRight != null) {
            tvRight.setText(rightText);
        }
    }
}
