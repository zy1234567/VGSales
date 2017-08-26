package com.ztstech.vgmate.activitys.provide_chance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.weigets.CustomGridView;

import butterknife.BindView;

public class ProvideChanceActivity extends MVPActivity<ProvideChanceContract.Presenter> implements
        ProvideChanceContract.View, View.OnClickListener{

    private final int TAG_ADD = -1;

    @BindView(R.id.cgv)
    CustomGridView customGridView;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_provide_chance;
    }

    @Override
    protected ProvideChanceContract.Presenter initPresenter() {
        return new ProvideChancePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        addDefaultImage();

    }


    /**
     * 增加默认图片
     */
    private void addDefaultImage() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.add_img);
        imageView.setTag(TAG_ADD);
        customGridView.addView(imageView);
        customGridView.requestLayout();

    }

    @Override
    public void onClick(View view) {
        int tag = (int)view.getTag();
        if (TAG_ADD == tag) {

        }
    }
}
