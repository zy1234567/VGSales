package com.ztstech.vgsales.activitys.news;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ztstech.vgsales.R;
import com.ztstech.vgsales.activitys.MVPFragment;

/**
 * 资讯
 */
public class NewsFragment extends MVPFragment<NewsContract.Presenter> implements NewsContract.View {

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected NewsContract.Presenter initPresenter() {
        return new NewsPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }
}
