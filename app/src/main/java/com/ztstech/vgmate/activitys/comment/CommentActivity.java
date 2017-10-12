package com.ztstech.vgmate.activitys.comment;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.comment.adapter.CommentRecyclerAdapter;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 评论界面
 */
public class CommentActivity extends MVPActivity<CommentContract.Presenter> implements CommentContract.View {

    /**
     * 传入参数，资讯id
     */
    public static final String ARG_NEWSID = "arg_newsid";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private CommentRecyclerAdapter recyclerAdapter;

    private String newsId;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_comment;
    }

    @Override
    protected CommentContract.Presenter initPresenter() {
        return new CommentPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        this.newsId = getIntent().getStringExtra(ARG_NEWSID);

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                mPresenter.getCommentList(newsId);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendCommentList(newsId);
            }
        });

        recyclerAdapter = new CommentRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        mPresenter.getCommentList(newsId);

    }


    @Override
    public void onLoadFinish(List<CommentBean.ListBean> listBeanList, @Nullable String error) {
        smartRefreshLayout.finishRefresh();
        if (error != null) {
            ToastUtil.toastCenter(this, error);
        }else {
            recyclerAdapter.setListData(listBeanList);
            recyclerAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onAppendFinish(List<CommentBean.ListBean> listBeanList, @Nullable String error) {
        smartRefreshLayout.finishLoadmore();
        if (error != null) {
            ToastUtil.toastCenter(this, error);
        }else {
            recyclerAdapter.setListData(listBeanList);
            recyclerAdapter.notifyDataSetChanged();
        }
    }
}
