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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgsales.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhiyuan on 2017/8/1.
 * 底部bar
 */

public class BottomBar extends FrameLayout implements View.OnClickListener{


    @BindView(R.id.img_share)
    ImageView ivTab0;
    @BindView(R.id.txt_1)
    TextView tvTab0;
    @BindView(R.id.lt_1)
    LinearLayout llTab0;

    @BindView(R.id.img_chance)
    ImageView ivTab1;
    @BindView(R.id.txt_2)
    TextView tvTab1;
    @BindView(R.id.lt_2)
    LinearLayout llTab1;


    @BindView(R.id.txt_4)
    TextView tvTab2;
    @BindView(R.id.rl_4)
    RelativeLayout rlTab2;


    @BindView(R.id.txt_5)
    TextView tvTab3;
    @BindView(R.id.rl_5)
    RelativeLayout rlTab3;

    private Unbinder unbinder;

    private OnTabItemClickListener onTabItemClickListener;

    public BottomBar(@NonNull Context context) {
        super(context);
        init();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_tab, this, true);
        unbinder = ButterKnife.bind(this, v);
        llTab0.setOnClickListener(this);
        llTab1.setOnClickListener(this);
        rlTab2.setOnClickListener(this);
        rlTab3.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (onTabItemClickListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.lt_1:
                onTabItemClickListener.onItemClick(0);
                break;
            case R.id.lt_2:
                onTabItemClickListener.onItemClick(1);
                break;
            case R.id.rl_4:
                onTabItemClickListener.onItemClick(3);
                break;
            case R.id.rl_5:
                onTabItemClickListener.onItemClick(4);
                break;
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener onTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener;
    }


    public interface OnTabItemClickListener {

        void onItemClick(int index);
    }
}
