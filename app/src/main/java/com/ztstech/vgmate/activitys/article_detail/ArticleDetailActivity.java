package com.ztstech.vgmate.activitys.article_detail;

import android.content.Intent;
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
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.comment.CommentActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_info.CreateShareInfoActivity;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.constants.NetConstants;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 文章详情界面
 */
public class ArticleDetailActivity extends MVPActivity<ArticleDetailContract.Presenter> implements
        ArticleDetailContract.View, View.OnClickListener {

    /**请求编辑资讯*/
    public static final int REQ_EDIT = 1;

    /**是否显示编辑*/
    public static final String ARG_SHOW_EDIT = "arg_show_edit";

    /**传入数据{@link com.ztstech.vgmate.data.beans.MainListBean.ListBean}，转换为json string传入*/
    public static final String ARG_LIST_DATA = "arg_list_data";

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

    /**传入的数据*/
    private MainListBean.ListBean data;
    /**显示编辑*/
    private boolean showEdit;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected ArticleDetailContract.Presenter initPresenter() {
        return new ArticleDetailPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        initIntentData();

        if (showEdit) {
            topBar.getRightImage().setVisibility(View.VISIBLE);
        }else {
            topBar.getRightImage().setVisibility(View.INVISIBLE);
        }

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(NetConstants.BASE_URL
                .concat("jsp/webh5/inforDetails.jsp?type=01"
                        .concat("&nid=").concat(data.nid)));


        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (etComment.isFocused()) {
                    etComment.clearFocus();
                }
                return false;
            }
        });


        topBar.getRightImage().setOnClickListener(this);

        //隐藏右侧编辑按钮
        if (UserRepository.getInstance().getUser().getUserBean().info.uid.equals(data.createuid)
                || UserRepository.getInstance().getUser().canEditArticle()) {
            topBar.getRightImage().setVisibility(View.VISIBLE);
        }else {
            topBar.getRightImage().setVisibility(View.GONE);
        }

        etComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    KeyboardUtils.showKeyBoard(ArticleDetailActivity.this, etComment);
                }else {
                    KeyboardUtils.hideKeyBoard(ArticleDetailActivity.this, etComment);
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

        imgComment.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        //初始化状态
        setEditTextHint(false);
        showCommentIconAndCount();
    }

    private void showCommentIconAndCount() {
        if (etComment.isFocused()) {
            tvCommentCount.setVisibility(View.INVISIBLE);
            imgComment.setVisibility(View.INVISIBLE);
            tvSubmit.setVisibility(View.VISIBLE);
        }else {
            if (data.evacnt > 0) {
                tvCommentCount.setText(String.valueOf(data.evacnt));
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
            etComment.setHintTextColor(ContextCompat.getColor(ArticleDetailActivity.this,
                    R.color.color_103));
            etComment.setHint("写评论...");
        }else {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   写评论...");
            ImageSpan imageSpan = new ImageSpan(ArticleDetailActivity.this, R.mipmap.ico_com,
                    ImageSpan.ALIGN_BASELINE);
            spannableStringBuilder.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            etComment.setHintTextColor(ContextCompat.getColor(ArticleDetailActivity.this,
                    R.color.color_100));
            etComment.setHint(spannableStringBuilder);
        }
    }

    /**
     * 初始化数据
     */
    private void initIntentData() {
        Intent it = getIntent();
        this.data = new Gson().fromJson(it.getStringExtra(ARG_LIST_DATA),
                MainListBean.ListBean.class);
        this.showEdit = it.getBooleanExtra(ARG_SHOW_EDIT, false);

    }

    @Override
    public void onClick(View view) {
        if (view == imgComment) {
            //跳转评论界面
            Intent it = new Intent(this, CommentActivity.class);
            it.putExtra(CommentActivity.ARG_NEWSID, data.nid);
            startActivity(it);
        }else if (view == tvSubmit) {
            //提交评论
            if (etComment.getText().length() == 0) {
                ToastUtil.toastCenter(this, "评论不能为空");
                return;
            }
            mPresenter.comment(etComment.getText().toString(), data);
        }else if (view == topBar.getRightImage()) {
            //编辑分享
            Intent it = new Intent(this, CreateShareInfoActivity.class);
            CreateShareData createShareData = new CreateShareData();
            createShareData.contentpicurl = data.contentpicsurl;
            createShareData.contentpicsurl = data.contentpicurl;
            createShareData.picurl = data.picurl;
            createShareData.picsurl = data.picsurl;
            createShareData.title = data.title;
            createShareData.url = data.url;
            createShareData.nid = data.nid;
            createShareData.picdescribe = data.picdescribe;
            createShareData.ntype = data.ntype;
            createShareData.type = data.type;
            createShareData.summary = data.summary;

            String jsonStr = new Gson().toJson(createShareData);
            it.putExtra(CreateShareInfoActivity.ARG_EDIT_DATA, jsonStr);
            startActivityForResult(it, REQ_EDIT);

        }
    }

    @Override
    public void onCommentFinish(@Nullable String errmsg) {
        hideLoading(null);
        if (errmsg == null) {
            ToastUtil.toastCenter(this, "评论成功");
        }else {
            ToastUtil.toastCenter(this, "评论失败：" + errmsg);
        }
    }
}
