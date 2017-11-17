package com.ztstech.vgmate.activitys.photo_browser;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.photo_browser.adapter.PhotoViewPagerAdapter;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.weigets.PhotoViewViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @author smm
 * @date 2017/11/17
 */

public class PhotoBrowserActivity extends BaseActivity {

    public static final String KEY_LIST = "list";
    public static final String KEY_POSITION = "position";

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.viewpager)
    PhotoViewViewPager viewpager;
    @BindView(R.id.tv_position)
    TextView tvPosition;

    PhotoViewPagerAdapter adapter;

    /** 传过来的图片地址集合 */
    private List<String> urlList;

    /** 当前选项的位置 */
    private int position;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        urlList = getIntent().getStringArrayListExtra(KEY_LIST);
        position = getIntent().getIntExtra(KEY_POSITION,0);
        List<View> mViews = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            final PhotoView photoView = new PhotoView(this);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setLayoutParams(layoutParams);
            photoView.setOnClickListener(listener); //点击事件
            Glide.with(this).load(urlList.get(i)).into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    //photoview重写了设置图片的方法 ，所以不能像普通的imageview去对待
                    photoView.setImageDrawable(resource);
                }
            });
            mViews.add(photoView);
        }
        adapter = new PhotoViewPagerAdapter(mViews);
        viewpager.setAdapter(adapter);
        tvPosition.setText((position + 1) + "/" + urlList.size());
        viewpager.setCurrentItem(position);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvPosition.setText(position + 1 + "/" + urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo_browser;
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
