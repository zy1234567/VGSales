package com.ztstech.vgmate.activitys.comment;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.comment.adapter.CommentRecyclerAdapter;
import com.ztstech.vgmate.data.beans.CommentBean;
import com.ztstech.vgmate.manager.SoftKeyboardStateHelper;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;

import java.util.List;

import butterknife.BindView;

/**
 * 评论界面
 */
public class CommentActivity extends MVPActivity<CommentContract.Presenter> implements
        CommentContract.View, CommentRecyclerAdapter.CommentRecyclerCallback, View.OnClickListener,
        SoftKeyboardStateHelper.SoftKeyboardStateListener{

    public static final String FLG_COMMENT_TYPE = "flg";

    public static final String FLG_INFO = "01";

    public static final String FLG_SHARE = "00";

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

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    private CommentRecyclerAdapter recyclerAdapter;
    private SoftKeyboardStateHelper keyboardStateHelper;

    private String newsId;

    /** 区分是分享评论还是资讯通知评论的标志位 */
    private String flg;

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
        flg = getIntent().getStringExtra(FLG_COMMENT_TYPE);
        keyboardStateHelper = new SoftKeyboardStateHelper(llRoot);

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

        etComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    etComment.setHint("写评论...");
                    //清除评论条目信息
                    etComment.setTag(null);
                    etComment.setTag(R.id.tag_0, null);
                    etComment.setTag(R.id.tag_1, null);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        keyboardStateHelper.addSoftKeyboardStateListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        keyboardStateHelper.removeSoftKeyboardStateListener(this);
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

    public void onCommentFinish(@Nullable String onCommentFinish) {
        if (onCommentFinish != null) {
            ToastUtil.toastCenter(this, "评论失败：" + onCommentFinish);
        }else {
            ToastUtil.toastCenter(this, "评论成功");
            smartRefreshLayout.autoRefresh();
            etComment.clearFocus();
            etComment.setText("");
        }
    }

    @Override
    public void deleteCommentFinish(@Nullable String errmsg) {
        if (errmsg == null) {
            ToastUtil.toastCenter(this, "删除成功");
            smartRefreshLayout.autoRefresh();
        }else {
            ToastUtil.toastCenter(this, "删除失败：" + errmsg);
        }
    }

    @Override
    public void onReplay(CommentBean.ListBean bean, boolean isReplay) {
        etComment.requestFocus();
        etComment.setText("");
        etComment.setSelection(etComment.getText().length());
        KeyboardUtils.showKeyBoard(this, etComment);
        etComment.setTag(R.id.tag_0, isReplay);
        etComment.setTag(R.id.tag_1, bean);
        etComment.setHint("@" + bean.name + "：");

    }

    @Override
    public void onDelete(final CommentBean.ListBean bean) {
        new IOSStyleDialog(this, "确认删除这条评论吗?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mPresenter.deleteComment(String.valueOf(bean.lid),flg);
            }
        }).show();

    }

    @Override
    public void onClick(View v) {
        if (v == tvSubmit) {
            String comment = etComment.getText().toString();
            if (TextUtils.isEmpty(comment.trim())) {
                ToastUtil.toastCenter(this, "请输入评论内容！");
                return;
            }
            Object tag = etComment.getTag(R.id.tag_1);
            Boolean isReplay = (Boolean) etComment.getTag(R.id.tag_0);
            // TODO: clearFocus会清除掉tag 把clear操作写到获取tag后边
            etComment.clearFocus();
            etComment.clearComposingText();
            if (tag != null && tag instanceof CommentBean.ListBean) {
                //回复某人
                CommentBean.ListBean listBean = (CommentBean.ListBean) tag;
                if (isReplay) {
                    mPresenter.comment(String.valueOf(listBean.lid), newsId,
                            listBean.uid, comment,flg);
                }else {
                    mPresenter.comment(null, newsId,
                            null, comment,flg);
                }
            }else {
                //直接回复新闻
                mPresenter.comment(null, newsId, null, comment,flg);
            }
        }
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
    }

    @Override
    public void onSoftKeyboardClosed() {
        etComment.clearFocus();
    }

}
