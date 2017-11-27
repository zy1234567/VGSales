package com.ztstech.vgmate.activitys.share.create;

import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by smm on 2017/11/27.
 */

public class CreateMyShareActivity extends MVPActivity<CreateShareContact.Presenter> implements CreateShareContact.View {


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_link)
    Button btnLink;
    @BindView(R.id.tv_none_link)
    TextView tvNoneLink;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ll_webview)
    LinearLayout llWebview;
    @BindView(R.id.cgv)
    CustomGridView cgv;
    @BindView(R.id.ll_grid)
    LinearLayout llGrid;

    @Override
    public String getShareTitle() {
        return etTitle.getText().toString();
    }

    @Override
    public String getShareContent() {
        return etContent.getText().toString();
    }

    @Override
    public String getLinkUrl() {
        if (TextUtils.equals(tvNoneLink.getText().toString(),"剪切板中没有有效链接")){
            return "";
        }
        return tvNoneLink.getText().toString();
    }

    @Override
    public File[] getImgaeFiles() {
        return new File[0];
    }

    @Override
    protected CreateShareContact.Presenter initPresenter() {
        return new CreateSharePresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_share;
    }


    @OnClick({R.id.tv_next, R.id.btn_link})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                break;
            case R.id.btn_link:
                break;
        }
    }
}
