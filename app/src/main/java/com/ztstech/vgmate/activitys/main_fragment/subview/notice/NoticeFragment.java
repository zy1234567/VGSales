package com.ztstech.vgmate.activitys.main_fragment.subview.notice;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.notice.adapter.NoticeRecyclerAdapter;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.events.CreateShareEvent;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.Go2EditShareUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * 公告tab
 */
public class NoticeFragment extends MVPFragment<NoticeContract.Presenter> implements
        NoticeContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;


    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    private NoticeRecyclerAdapter recyclerAdapter;

    public NoticeFragment() {
    }

    public static NoticeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_notice;
    }

    @Override
    protected NoticeContract.Presenter initPresenter() {
        return new NoticePresenter(this);
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        EventBus.getDefault().register(this);
        recyclerAdapter = new NoticeRecyclerAdapter(editInfoCallBack);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        refreshLayout.setEnableRefresh(false);  //禁止下拉刷新

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }
        });


        mPresenter.loadData();
    }



    /**
     * 长按回调
     */
    DialogUtils.EditInfoCallBack editInfoCallBack = new DialogUtils.EditInfoCallBack() {
        @Override
        public void onClickDelete(final String nid) {
            //删除
            new IOSStyleDialog(getContext(),"确认删除此条资讯?",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mPresenter.deleteNotice(nid);
                }
            }).show();
        }

        @Override
        public void onClickResend(MainListBean.ListBean bean) {
            //重新发送
            mPresenter.resendArticle(bean);
        }

        @Override
        public void onClickEdit(MainListBean.ListBean bean) {
            //编辑并重新发送
            Go2EditShareUtils.editShareInfo(getContext(),bean);
        }
    };


    @Override
    public void setData(List<MainListBean.ListBean> listData) {
        if (refreshLayout.isLoading()) {
            refreshLayout.finishLoadmore();
        }
        recyclerAdapter.setListData(listData);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }

    @Override
    public void deleteArticleFinish(@Nullable String errmsg) {
        if (errmsg == null) {
            ToastUtil.toastCenter(getActivity(), "删除成功");
            mPresenter.loadData();
        }else {
            ToastUtil.toastCenter(getActivity(), "删除失败：" + errmsg);
        }
    }

    @Override
    public void resendFinish(String errmsg) {
        if (errmsg == null) {
            ToastUtil.toastCenter(getActivity(), "发送成功");
            mPresenter.loadData();
        }else {
            ToastUtil.toastCenter(getActivity(), "发送失败：" + errmsg);
        }
    }

    @Subscribe
    public void refresh(Object object){
        if (object instanceof CreateShareEvent){
            if (((CreateShareEvent) object).type.equals(CreateShareApi.SHARE_NOTICE)){
                mPresenter.loadData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
