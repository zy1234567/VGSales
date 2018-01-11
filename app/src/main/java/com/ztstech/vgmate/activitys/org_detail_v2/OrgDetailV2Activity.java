package com.ztstech.vgmate.activitys.org_detail_v2;


import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.CommmunicateListActivity;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrgDetailV2Activity extends MVPActivity {

    public static final String ARG_BEAN = "bean";


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_com)
    TextView tvCom;
    @BindView(R.id.ll_next)
    LinearLayout llNext;

    OrgFollowlistBean.ListBean bean;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        bean = new Gson().fromJson(getIntent().getStringExtra(ARG_BEAN),OrgFollowlistBean.ListBean.class);
        if (bean == null){
            throw new NullPointerException("沒有传入正确的实体类");
        }
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
        intent.putExtra(CommmunicateListActivity.ARG_RBIID,bean.rbiid);
        startActivity(intent);
    }
}
