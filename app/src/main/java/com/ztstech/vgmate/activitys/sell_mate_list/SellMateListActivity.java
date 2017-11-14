package com.ztstech.vgmate.activitys.sell_mate_list;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.constants.NetConstants;

import butterknife.BindView;

/**
 * 销售伙伴列表
 */
public class SellMateListActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.pb)
    ProgressBar pb;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sell_mate_list;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initWebView();
        webView.loadUrl(NetConstants.BASE_URL + Constants.MATE_LIST_URL);
        setCookie();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setSaveFormData(false);
        settings.setBuiltInZoomControls(true);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setDefaultTextEncodingName("utf-8");
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        settings.setBlockNetworkImage(false);
        webView.setInitialScale(99); //这句话是解决s8上左右晃动的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

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
    }

    /**
     * 设置访问h5的加密cookie
     */
    private void setCookie() {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(NetConstants.BASE_URL, NetConstants.PARAM_AUTHID + UserRepository.getInstance().getAuthId());
        cookieManager.setCookie(NetConstants.BASE_URL, "type=02");
        CookieSyncManager.getInstance().sync();
    }

}
