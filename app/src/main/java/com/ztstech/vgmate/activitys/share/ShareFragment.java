package com.ztstech.vgmate.activitys.share;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.share.adapter.BaseShareViewHolder;
import com.ztstech.vgmate.activitys.share.adapter.ShareListAdapter;
import com.ztstech.vgmate.activitys.share.create.CreateMyShareActivity;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 分享
 *
 * @author smm
 */
public class ShareFragment extends MVPFragment<ShareContact.Presenter> implements ShareContact.View,BaseShareViewHolder.ClickCallback{

    /** 请求跳转至分享 */
    public static final int REQ_SHARE = 66;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    private ShareListAdapter adapter;

    public static ShareFragment newInstance() {
        Bundle args = new Bundle();
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        adapter = new ShareListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        mPresenter.loadNetData();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadNetData();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), CreateMyShareActivity.class),REQ_SHARE);
            }
        });
    }

    @Override
    protected ShareContact.Presenter initPresenter() {
        return new SharePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_share;
    }

    @Override
    public void showError(String msg) {
        ToastUtil.toastCenter(getContext(),msg);
    }

    @Override
    public void onDeleteSuccess() {
        ToastUtil.toastCenter(getContext(),"删除成功");
        mPresenter.loadNetData();
    }

    @Override
    public void onCommentSuccess() {
        ToastUtil.toastCenter(getContext(),"评论成功");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_SHARE && resultCode == CreateMyShareActivity.RES_SHARE){
            mPresenter.loadNetData();
        }
    }

    @Override
    public void setListData(List<ShareListBean.ListBean> listData) {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();
        if (listData.size() == 0){
            llNoData.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }else {
            llNoData.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
        }
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickPrise(ShareListBean.ListBean data) {
        mPresenter.priseShare(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClickDelete(final ShareListBean.ListBean data) {
        new IOSStyleDialog(getContext(), "确认删除此条分享?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteShare(data.sid);
            }
        }).show();
    }

    @Override
    public void comment(ShareListBean.ListBean data, String content) {
        mPresenter.comment(data,content);
    }
}
