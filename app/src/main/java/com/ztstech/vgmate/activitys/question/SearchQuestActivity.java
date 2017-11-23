package com.ztstech.vgmate.activitys.question;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.PresenterImpl;
import com.ztstech.vgmate.activitys.question.adapter.QuestionListAdapter;
import com.ztstech.vgmate.activitys.question.adapter.QuestionViewHolder;
import com.ztstech.vgmate.activitys.question.question_detail.QuestDetailActivity;
import com.ztstech.vgmate.activitys.question.question_list.QuestionListContact;
import com.ztstech.vgmate.activitys.question.question_list.QuestionListPresenter;
import com.ztstech.vgmate.data.beans.QuestionListBean;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author smm
 * @date 2017/11/16
 */

public class SearchQuestActivity extends MVPActivity<QuestionListContact.Presenter> implements QuestionListContact.View,QuestionViewHolder.ClickCallBack {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    QuestionListAdapter adapter;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;


    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        adapter = new QuestionListAdapter("",this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    String keyword = etSearch.getText().toString();
                    if (!TextUtils.isEmpty(keyword)) {
                        adapter.setSearchText(keyword);
                        mPresenter.loadData(etSearch.getText().toString(),false);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected QuestionListContact.Presenter initPresenter() {
        return new QuestionListPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        KeyboardUtils.hideKeyBoard(this,etSearch);
        finish();
    }

    @Override
    public void onItemClick(QuestionListBean.ListBean bean) {
        Intent intent = new Intent(this,QuestDetailActivity.class);
        intent.putExtra(QuestDetailActivity.KEY_BEAN,bean);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(final String qid) {
        new IOSStyleDialog(this, "确认删除此条问答？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteQuestion(qid);
            }
        }).show();
    }

    @Override
    public void setData(List<QuestionListBean.ListBean> listData) {
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();
        KeyboardUtils.hideKeyBoard(this,etSearch);
        if (listData.size() == 0){
            llNoData.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }else {
            llNoData.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage) {
        ToastUtil.toastCenter(this,errorMessage);
    }

    @Override
    public void setNoreMoreData(boolean noreMoreData) {

    }

    @Override
    public void onDeleteSucceed() {
        ToastUtil.toastCenter(this,"删除成功");
    }
}
