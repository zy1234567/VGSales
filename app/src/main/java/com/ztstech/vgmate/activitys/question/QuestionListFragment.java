package com.ztstech.vgmate.activitys.question;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.question.adapter.QuestionListAdapter;
import com.ztstech.vgmate.activitys.question.adapter.QuestionViewHolder;
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

    @BindView(R.id.recycler)
    RecyclerView recycler;

    QuestionListAdapter adapter;

    public static QuestionListFragment newInstance() {
        Bundle args = new Bundle();
        QuestionListFragment fragment = new QuestionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        adapter = new QuestionListAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<QuestionListBean.ListBean> listData = new ArrayList();
        QuestionListBean.ListBean bean = new QuestionListBean.ListBean();
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        adapter.setListData(listData);
        recycler.setAdapter(adapter);
    }

    @Override
    protected QuestionListContact.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_question_list;
    }

    @Override
    public void setData(List<QuestionListBean.ListBean> listData) {
        QuestionListBean.ListBean bean = new QuestionListBean.ListBean();
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        listData.add(bean);
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }

    @Override
    public void onItemClick() {
        Intent intent = new Intent(getActivity(),QuestDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick() {
        new IOSStyleDialog(getContext(), "确认删除此条问答？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.toastCenter(getContext(),"删除成功!");
            }
        }).show();
    }
}
