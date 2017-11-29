package com.ztstech.vgmate.activitys.share.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailActivity;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailContract;
import com.ztstech.vgmate.activitys.article_detail.ArticleDetailPresenter;
import com.ztstech.vgmate.activitys.comment.CommentActivity;
import com.ztstech.vgmate.data.beans.ShareListBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 *
 * @author smm
 * @date 2017/11/29
 * 显示分享网页详情的界面
 */

public class ShareDetailActivity extends MVPActivity<ArticleDetailContract.Presenter>
        implements ArticleDetailContract.View, View.OnClickListener{

    public static final String KEY_BEAN = "bean";

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.rl_comment)
    RelativeLayout rlComment;
    @BindView(R.id.pb)
    ProgressBar pb;

    /** 该条分享的实体类 */
    private ShareListBean.ListBean bean;

    @Override
    protected ArticleDetailContract.Presenter initPresenter() {
        return new ArticleDetailPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        Intent intent = getIntent();
        bean = (ShareListBean.ListBean) intent.getSerializableExtra(KEY_BEAN);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(bean.url);

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (etComment.isFocused()) {
                    etComment.clearFocus();
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pb.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        etComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    KeyboardUtils.showKeyBoard(ShareDetailActivity.this, etComment);
                }else {
                    KeyboardUtils.hideKeyBoard(ShareDetailActivity.this, etComment);
                }
                setEditTextHint(b);
                showCommentIconAndCount();
            }
        });

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

        rlComment.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        topBar.getRightImage().setVisibility(View.GONE);
        //初始化状态
        setEditTextHint(false);
        showCommentIconAndCount();
    }

    @Override
    public void onCommentFinish(@Nullable String errmsg) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_article_detail;
    }

    private void showCommentIconAndCount() {
        if (etComment.isFocused()) {
            tvCommentCount.setVisibility(View.INVISIBLE);
            imgComment.setVisibility(View.INVISIBLE);
            tvSubmit.setVisibility(View.VISIBLE);
        }else {
            if (bean.commentList.size() > 0) {
                tvCommentCount.setText(String.valueOf(bean.commentList.size()));
                tvCommentCount.setVisibility(View.VISIBLE);
            }else {
                tvCommentCount.setVisibility(View.INVISIBLE);
            }
            imgComment.setVisibility(View.VISIBLE);
            tvSubmit.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * 设置edittext提示文字
     * @param isEdit 是否处于编辑状态
     */
    private void setEditTextHint(boolean isEdit) {
        if (isEdit) {
            //如果获取到焦点
            etComment.setHintTextColor(ContextCompat.getColor(ShareDetailActivity.this,
                    R.color.color_103));
            etComment.setHint("写评论...");
        }else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   写评论...");
            ImageSpan imageSpan = new ImageSpan(ShareDetailActivity.this, R.mipmap.ico_com,
                    ImageSpan.ALIGN_BASELINE);
            spannableStringBuilder.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            etComment.setHintTextColor(ContextCompat.getColor(ShareDetailActivity.this,
                    R.color.color_100));
            etComment.setHint(spannableStringBuilder);
        }
    }



    @Override
    public void onClick(View view) {
        if (view == rlComment) {
            //跳转评论界面
            Intent it = new Intent(this, CommentActivity.class);
            it.putExtra(CommentActivity.ARG_NEWSID, bean.sid);
            startActivity(it);
        }else if (view == tvSubmit) {
            //提交评论
            if (etComment.getText().length() == 0) {
                ToastUtil.toastCenter(this, "评论不能为空");
                return;
            }
//            mPresenter.comment(etComment.getText().toString(), data);
        }
    }
}
