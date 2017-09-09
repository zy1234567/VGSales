package com.ztstech.vgmate.activitys.article_detail;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 文章详情界面
 */
public class ArticleDetailActivity extends MVPActivity<ArticleDetailContract.Presenter> implements
        ArticleDetailContract.View {

    /**是否显示编辑*/
    public static final String ARG_SHOW_EDIT = "arg_show_edit";

    /**传入数据{@link com.ztstech.vgmate.data.beans.MainListBean.ListBean}，转换为json string传入*/
    public static final String ARG_LIST_DATA = "arg_list_data";

    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.top_bar)
    TopBar topBar;

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

        if (!TextUtils.isEmpty(data.url)) {
            webView.loadUrl(data.url);
        }

    }

    private void initIntentData() {
        Intent it = getIntent();
        this.data = new Gson().fromJson(it.getStringExtra(ARG_LIST_DATA),
                MainListBean.ListBean.class);
        this.showEdit = it.getBooleanExtra(ARG_SHOW_EDIT, false);
    }
}
