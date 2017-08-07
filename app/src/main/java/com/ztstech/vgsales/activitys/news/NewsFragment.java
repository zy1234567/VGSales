package com.ztstech.vgsales.activitys.news;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ztstech.vgsales.R;
import com.ztstech.vgsales.activitys.MVPFragment;
import com.ztstech.vgsales.activitys.news.adapter.NewsRecyclerAdapter;
import com.ztstech.vgsales.model.news.NewsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 资讯
 */
public class NewsFragment extends MVPFragment<NewsContract.Presenter> implements NewsContract.View {

    @BindView(R.id.xrv_news)
    XRecyclerView recyclerView;

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

    @Override
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);
        NewsRecyclerAdapter adapter = new NewsRecyclerAdapter();
        List<NewsModel> newsModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newsModelList.add(new NewsModel());
        }
        adapter.setListData(newsModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }
}
