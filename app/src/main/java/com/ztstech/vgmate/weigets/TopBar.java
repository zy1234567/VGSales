package com.ztstech.vgmate.weigets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
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

    private View topView, searchView;
    private ImageView imgRight, imgRightTwo,imgLeft;
    private TextView tvTitle, tvLeft, tvRight, tvSearch;
    private TextView tvRedNum;

    private String title, leftText, rightText, searchText;

    private int backgroundColor = -1;
    private int colorLeft;
    private int colorRight;
    private int colorTitle;


    private boolean showBackNav = false;
    private boolean showSearch = false;

    public TopBar(@NonNull Context context) {
        this(context, null, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        topView = LayoutInflater.from(getContext()).inflate(R.layout.layout_top_bar,
                this, false);
        imgRight = topView.findViewById(R.id.iv_right);
        imgRightTwo = topView.findViewById(R.id.iv_right_two);
        tvTitle = topView.findViewById(R.id.tv_title);
        imgLeft = topView.findViewById(R.id.iv_left);
        tvLeft = topView.findViewById(R.id.tv_left);
        tvRight = topView.findViewById(R.id.tv_right);
        searchView = topView.findViewById(R.id.rl_total_search);
        tvSearch = topView.findViewById(R.id.tv_search);
        tvRedNum = topView.findViewById(R.id.tv_red_num);



        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TopBar);

        int leftDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcLeft, -1);
        int rightDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcRight, -1);
        int rightTwoDrawableId = typedArray.getResourceId(R.styleable.TopBar_srcRightTwo, -1);
        backgroundColor = typedArray.getColor(R.styleable.TopBar_backgroundColor, 0);
        colorLeft = typedArray.getColor(R.styleable.TopBar_colorLeft, 0);
        colorRight = typedArray.getColor(R.styleable.TopBar_colorRight, 0);
        colorTitle = typedArray.getColor(R.styleable.TopBar_colorTitle, 0);
        showBackNav = typedArray.getBoolean(R.styleable.TopBar_showBackNav, false);
        showSearch = typedArray.getBoolean(R.styleable.TopBar_showSearch, false);

        leftText = typedArray.getString(R.styleable.TopBar_textLeft);
        rightText = typedArray.getString(R.styleable.TopBar_textRight);
        searchText = typedArray.getString(R.styleable.TopBar_textSearch);

        title = typedArray.getString(R.styleable.TopBar_barTitle);

        typedArray.recycle();

        if (backgroundColor != -1) {
            topView.setBackgroundColor(backgroundColor);
        }

        if (title != null) {
            tvTitle.setText(title);
        }

        if (leftText != null) {
            tvLeft.setText(leftText);
        }

        if (rightText != null) {
            tvRight.setText(rightText);
        }

        if (searchText != null) {
            tvSearch.setText(searchText);
        }

        if (leftDrawableId != -1) {
            imgLeft.setImageResource(leftDrawableId);
        }
        if (rightDrawableId != -1) {
            imgRight.setImageResource(rightDrawableId);
        }

        if (rightTwoDrawableId != -1) {
            imgRightTwo.setImageResource(rightTwoDrawableId);
        }

        //颜色为纯白色暂时无法显示
        if (colorLeft != 0) {
            imgLeft.setColorFilter(colorLeft);
            tvLeft.setTextColor(colorLeft);
        }
        if (colorRight != 0) {
            imgRight.setColorFilter(colorRight);
            imgRightTwo.setColorFilter(colorRight);
            tvRight.setTextColor(colorRight);
        }

        if (colorTitle != 0) {
            tvTitle.setTextColor(colorTitle);
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

        if (showSearch) {
            searchView.setVisibility(VISIBLE);
            tvTitle.setVisibility(INVISIBLE);
        }


        addView(topView);


    }


    public ImageView getRightImage() {
        return imgRight;
    }

    public ImageView getRightTwoImage() {
        return imgRightTwo;
    }

    public ImageView getLeftImage() {
        return imgLeft;
    }

    public TextView getRightTextView() {
        return tvRight;
    }

    public TextView getRightRedNum() {
        return tvRedNum;
    }

    public TextView getLeftTextView() {
        return tvLeft;
    }


    public TextView getCenterTextView() {
        return tvTitle;
    }

    public View getSearchView() {
        return searchView;
    }

    public void setTitle(String title) {
        this.title = title;
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }
    public void setTitleColor(int i){
        this.title = title;
        if (tvTitle != null) {
            tvTitle.setTextColor(getContext().getResources().getColor(i));
        }
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
        if (tvSearch != null) {
            tvSearch.setText(searchText);
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
