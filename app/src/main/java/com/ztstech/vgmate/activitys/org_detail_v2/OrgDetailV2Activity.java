package com.ztstech.vgmate.activitys.org_detail_v2;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.CommmunicateListActivity;
import com.ztstech.vgmate.activitys.coop_progress.CoopProgressActivity;
import com.ztstech.vgmate.activitys.upload_protocol.UploadActivity;
import com.ztstech.vgmate.activitys.upload_protocol.upload_cood_poster.UploadCoodPosterActivity;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.data.dto.CoopProgressData;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.data.events.UploadProtocolEvent;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrgDetailV2Activity extends MVPActivity<OrgDetailV2Contract.Presenter> implements OrgDetailV2Contract.View {

    public static final String ARG_RBIID = "rbiid";
    public static final String ARG_RBIONAMW = "rbioname";
    public static final String ARG_ORGID = "orgid";
    //合作精度红点
    public static final String STATUES_FLG = "status_flg";
    //协议实体类
    public static final String ARG_BEAN_PROTOCOL = "protocol_bean";
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_upload)
    TextView tvIpload;
    @BindView(R.id.ll_next)
    LinearLayout llNext;
    @BindView(R.id.tv_sched)
    TextView tvSched;
    @BindView(R.id.view_flg)
    View viewFlg;
    UploadProtocolData data;
    CoopProgressData coopProgressData;
    int rbiid;
    String rbioname,orgid;

    //红点标志位
    private String status;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        rbiid = getIntent().getIntExtra(ARG_RBIID, 0);
        orgid = getIntent().getStringExtra(ARG_ORGID);
        rbioname = getIntent().getStringExtra(ARG_RBIONAMW);
        status = getIntent().getStringExtra(STATUES_FLG);
        if(TextUtils.isEmpty(status)||TextUtils.equals("01",status)){
            viewFlg.setVisibility(View.GONE);
        }else {
            viewFlg.setVisibility(View.VISIBLE);
        }
        data = new UploadProtocolData();
        topBar.setTitle("机构主页");
        topBar.setTitleColor(R.color.color_109);
        mPresenter.loadData(orgid);
        webview.loadUrl("http://www.008box.com/jsp/webh5/app_mapOrgDetails.jsp?rbiid="
                .concat(String.valueOf(rbiid)).concat("&").concat("type=01&").concat("openWhere=01"));
        WebSettings settings = webview.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(false);
        settings.setSaveFormData(false);
        settings.setBuiltInZoomControls(true);
        settings.setSavePassword(false);
        settings.setSupportZoom(false);
        settings.setDefaultTextEncodingName("utf-8");
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        settings.setBlockNetworkImage(false);
        webview.setInitialScale(99); //这句话是解决s8上左右晃动的问题

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_detail_v2;
    }


    @OnClick({ R.id.tv_upload,R.id.tv_sched})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_upload:
                showuploadprotocol();
                break;
            case R.id.tv_sched:
                mPresenter.loadcoopData(String.valueOf(rbiid),orgid);

                break;
            default:
                break;
        }
    }
    /**
     * 上传信息dialog
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void showuploadprotocol(){
        DialogUtils.showUploadMsg(this, data,new DialogUtils.showUploadMsgCallBack() {
            @Override
            public void onUploadprotocolClick() {
                Intent intent = new Intent(OrgDetailV2Activity.this, UploadCoodPosterActivity.class);
                intent.putExtra(UploadCoodPosterActivity.UPLOAD_KEY,UploadCoodPosterActivity.UPLOAD_COOP_VALUSE);
                intent.putExtra(ARG_RBIONAMW,rbioname);
                intent.putExtra(ARG_ORGID,orgid);
                intent.putExtra(ARG_BEAN_PROTOCOL,new Gson().toJson(data));
                startActivity(intent);
            }

            @Override
            public void onUploadposterClick() {
                Intent intent = new Intent(OrgDetailV2Activity.this, UploadCoodPosterActivity.class);
                intent.putExtra(UploadCoodPosterActivity.UPLOAD_KEY,UploadCoodPosterActivity.UPLOAD_POSTER_VALUSE);
                intent.putExtra(ARG_RBIONAMW,rbioname);
                intent.putExtra(ARG_ORGID,orgid);
                intent.putExtra(ARG_BEAN_PROTOCOL,new Gson().toJson(data));
                startActivity(intent);
            }

            @Override
            public void onUploadprivaryClick() {
                Intent intent = new Intent(OrgDetailV2Activity.this, UploadActivity.class);
                intent.putExtra(ARG_RBIONAMW,rbioname);
                intent.putExtra(ARG_ORGID,orgid);
                intent.putExtra(ARG_BEAN_PROTOCOL,new Gson().toJson(data));
                startActivity(intent);
            }
        });
    }

    @Override
    public void setData(UploadProtocolData uploadProtocolData) {
        this.data = uploadProtocolData;
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void setCoopData(CoopProgressData coopProgressData) {
        this.coopProgressData = coopProgressData;
        Intent intent = new Intent(this,CoopProgressActivity.class);
        intent.putExtra(CoopProgressActivity.ORG_COOP_BEAN,new Gson().toJson(coopProgressData));
        startActivity(intent);
    }

    @Override
    protected OrgDetailV2Contract.Presenter initPresenter() {
        return new OrgDetailV2Presenter(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadProtocolEvent event) {
        mPresenter.loadData(orgid);
    };
}
