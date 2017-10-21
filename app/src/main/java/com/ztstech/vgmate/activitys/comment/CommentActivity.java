package com.ztstech.vgmate.activitys.comment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;
import com.ztstech.vgmate.activitys.comment.adapter.CommentRecyclerAdapter;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 评论界面
 */
public class CommentActivity extends MVPActivity<CommentContract.Presenter> implements
        CommentContract.View, CommentRecyclerAdapter.CommentRecyclerCallback, View.OnClickListener {

    /**
     * 传入参数，资讯id
     */
    public static final String ARG_NEWSID = "arg_newsid";

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.srl)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindView(R.id.img_comment)
    ImageView imgComment;

    @BindView(R.id.et_comment)
    EditText etComment;

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

        imgComment.setVisibility(View.GONE);
        tvSubmit.setVisibility(View.VISIBLE);
        etComment.setFocusableInTouchMode(true);
        etComment.setFocusable(true);

        recyclerAdapter.setCallback(this);

        tvSubmit.setOnClickListener(this);

        mPresenter.getCommentList(newsId);

        etComment.setHintTextColor(ContextCompat.getColor(this, R.color.color_103));
        etComment.setHint("写评论...");
        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvSubmit.setEnabled(editable.length() > 0);
            }
        });

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

    @Override
    public void onReplay(CommentBean.ListBean bean) {
        etComment.requestFocus();
        etComment.setSelection(etComment.getText().length());
        InputMethodManager inputManager =
                (InputMethodManager) etComment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(etComment, 0);
    }

    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            etComment.clearFocus();
            etComment.clearComposingText();
        }
    }

}
