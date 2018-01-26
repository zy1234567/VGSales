package com.ztstech.vgmate.activitys.org_detail_v2;


import android.content.Intent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.CommmunicateListActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.OnClick;

public class OrgDetailV2Activity extends MVPActivity {

    public static final String ARG_RBIID = "rbiid";


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_com)
    TextView tvCom;
    @BindView(R.id.ll_next)
    LinearLayout llNext;


    int rbiid;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        rbiid = getIntent().getIntExtra(ARG_RBIID,0);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_detail_v2;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @OnClick(R.id.ll_next)
    public void onViewClicked() {
        Intent intent = new Intent(this, CommmunicateListActivity.class);
        intent.putExtra(CommmunicateListActivity.ARG_RBIID,String.valueOf(rbiid));
        startActivity(intent);
    }
}
