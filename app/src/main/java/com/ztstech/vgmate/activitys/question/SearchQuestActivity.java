package com.ztstech.vgmate.activitys.question;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.question.adapter.QuestionListAdapter;
import com.ztstech.vgmate.activitys.question.adapter.QuestionViewHolder;
import com.ztstech.vgmate.activitys.question.question_detail.QuestDetailActivity;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2017/11/16
 */

public class SearchQuestActivity extends MVPActivity implements QuestionViewHolder.ClickCallBack {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    QuestionListAdapter adapter;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;


    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        adapter = new QuestionListAdapter("机构",this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(QuestionListBean.ListBean bean) {
        Intent intent = new Intent(this,QuestDetailActivity.class);
        intent.putExtra(QuestDetailActivity.KEY_BEAN,bean);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(String qid) {
        new IOSStyleDialog(this, "确认删除此条问答？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.toastCenter(SearchQuestActivity.this,"删除成功!");
            }
        }).show();
    }
}
