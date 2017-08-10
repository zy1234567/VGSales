package com.ztstech.mate.activitys.main.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.mate.R;

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
    @BindView(R.id.img_chance)
    ImageView ivTab1;
    @BindView(R.id.txt_4)
    TextView tvTab2;
    @BindView(R.id.txt_5)
    TextView tvTab3;

    @BindView(R.id.txt_1)
    TextView tvTab0;
    @BindView(R.id.rl_1)
    RelativeLayout rlTab0;

    @BindView(R.id.txt_2)
    TextView tvTab1;
    @BindView(R.id.rl_2)
    RelativeLayout rlTab1;

    @BindView(R.id.rl_4)
    RelativeLayout rlTab2;

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
        rlTab0.setOnClickListener(this);
        rlTab1.setOnClickListener(this);
        rlTab2.setOnClickListener(this);
        rlTab3.setOnClickListener(this);
        updateTabView(0);
    }


    @Override
    public void onClick(View view) {
        if (onTabItemClickListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.rl_1:
                onTabClick(0);
                break;
            case R.id.rl_2:
                onTabClick(1);
                break;
            case R.id.rl_4:
                onTabClick(2);
                break;
            case R.id.rl_5:
                onTabClick(3);
                break;
        }
    }


    private void onTabClick(int index) {
        onTabItemClickListener.onItemClick(index);
        updateTabView(index);
    }


    private void updateTabView(int index) {
        if (index == 0) {
            ivTab0.setSelected(true);
            ivTab1.setSelected(false);
            tvTab2.setSelected(false);
            tvTab3.setSelected(false);
        }else if (index == 1) {
            ivTab0.setSelected(false);
            ivTab1.setSelected(true);
            tvTab2.setSelected(false);
            tvTab3.setSelected(false);
        }else if (index == 2) {
            ivTab0.setSelected(false);
            ivTab1.setSelected(false);
            tvTab2.setSelected(true);
            tvTab3.setSelected(false);
        }else if (index == 3) {
            ivTab0.setSelected(false);
            ivTab1.setSelected(false);
            tvTab2.setSelected(false);
            tvTab3.setSelected(true);
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener onTabItemClickListener) {
        this.onTabItemClickListener = onTabItemClickListener;
    }


    public interface OnTabItemClickListener {

        void onItemClick(int index);
    }
}
