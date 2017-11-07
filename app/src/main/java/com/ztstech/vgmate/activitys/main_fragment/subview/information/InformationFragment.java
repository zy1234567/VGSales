package com.ztstech.vgmate.activitys.main_fragment.subview.information;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.subview.information.adapter.InformationRecyclerAdapter;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.Go2EditShareUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 资讯tab
 */
public class InformationFragment extends MVPFragment<InformationContract.Presenter> implements
        InformationContract.View {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    RefreshLayout refreshLayout;

    private InformationRecyclerAdapter recyclerAdapter;


    public static InformationFragment newInstance() {

        InformationFragment fragment = new InformationFragment();
        return fragment;
    }

    @Override
    protected InformationContract.Presenter initPresenter() {
        return new InformationPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_information;
    }


    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        recyclerAdapter = new InformationRecyclerAdapter(editInfoCallBack);
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

        mPresenter.loadListData();


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
                    mPresenter.deleteArticle(nid);
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
    public void setListData(List<MainListBean.ListBean> listData) {
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
    public void showNomoreData(boolean nomore) {

    }

    @Override
    public void resendFinish(String errmsg) {
        if (errmsg == null) {
            ToastUtil.toastCenter(getActivity(), "发送成功");
            mPresenter.loadListData();
        }else {
            ToastUtil.toastCenter(getActivity(), "发送失败：" + errmsg);
        }
    }


    @Override
    public void deleteArticleFinish(@Nullable String errmsg) {
        if (errmsg == null) {
            ToastUtil.toastCenter(getActivity(), "删除成功");
            mPresenter.loadListData();
        }else {
            ToastUtil.toastCenter(getActivity(), "删除失败：" + errmsg);
        }
    }

}
