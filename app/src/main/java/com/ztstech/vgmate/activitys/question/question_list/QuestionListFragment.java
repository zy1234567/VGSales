package com.ztstech.vgmate.activitys.question.question_list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.question.QuestDetailActivity;
import com.ztstech.vgmate.activitys.question.adapter.QuestionListAdapter;
import com.ztstech.vgmate.activitys.question.adapter.QuestionViewHolder;
import com.ztstech.vgmate.activitys.question.create_question.CreateQuestionActivity;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author smm
 * @date 2017/11/16
 * 全部问答列表
 */

public class QuestionListFragment extends MVPFragment<QuestionListContact.Presenter> implements QuestionListContact.View,QuestionViewHolder.ClickCallBack{

    /** 请求发布问题 */
    public static final int REQUEST_CREATE = 0x001;

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    QuestionListAdapter adapter;

    /** 是否是我的问题列表 */
    boolean myflg;

    public static QuestionListFragment newInstance() {
        Bundle args = new Bundle();
        QuestionListFragment fragment = new QuestionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setShowType(boolean myflg){
        this.myflg = myflg;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        adapter = new QuestionListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
        requestNewData();
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.appendData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
    }

    @Override
    protected QuestionListContact.Presenter initPresenter() {
        return new QuestionListPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_question_list;
    }

    @Override
    public void setData(List<QuestionListBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        if (listData.size() == 0){
            llNoData.setVisibility(View.VISIBLE);
            smartRefreshLayout.setVisibility(View.GONE);
        }else {
            llNoData.setVisibility(View.GONE);
            smartRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtil.toastCenter(getContext(),"查询问题列表出错:".concat(errorMessage));
    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {
        smartRefreshLayout.setEnableAutoLoadmore(!noreMoreData);
    }

    @Override
    public void onDeleteSucceed() {
        ToastUtil.toastCenter(getContext(),"删除成功");
        requestNewData();
    }

    @Override
    public void onItemClick(String quid) {
        Intent intent = new Intent(getActivity(),QuestDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(final String quid) {
        new IOSStyleDialog(getContext(), "确认删除此条问答？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteQuestion(quid);
            }
        }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CREATE && resultCode == CreateQuestionActivity.RESULT_CREATE){
            requestNewData();
        }
    }

    private void requestNewData(){
        mPresenter.loadData("",myflg);
    }

}
