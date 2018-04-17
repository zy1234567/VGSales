package com.ztstech.vgmate.activitys.org_detail_v2;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.communicate_record.com_list.CommmunicateListActivity;
import com.ztstech.vgmate.activitys.upload_protocol.UploadActivity;
import com.ztstech.vgmate.activitys.upload_protocol.upload_cood_poster.UploadCoodPosterActivity;
import com.ztstech.vgmate.data.dto.AddComRecordData;
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
    //协议实体类
    public static final String ARG_BEAN_PROTOCOL = "protocol_bean";
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.tv_com)
    TextView tvCom;
    @BindView(R.id.tv_upload)
    TextView tvIpload;
    @BindView(R.id.ll_next)
    LinearLayout llNext;

    UploadProtocolData data;
    int rbiid;
    String rbioname,orgid;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        rbiid = getIntent().getIntExtra(ARG_RBIID, 0);
        orgid = getIntent().getStringExtra(ARG_ORGID);
        rbioname = getIntent().getStringExtra(ARG_RBIONAMW);
        data = new UploadProtocolData();
        if (TextUtils.isEmpty(orgid)){
            tvIpload.setVisibility(View.GONE);
        }else{
            mPresenter.loadData(orgid);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_detail_v2;
    }


    @OnClick({R.id.tv_com, R.id.tv_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_com:
                Intent intent = new Intent(this, CommmunicateListActivity.class);
                intent.putExtra(CommmunicateListActivity.ARG_RBIID, String.valueOf(rbiid));
                startActivity(intent);
                break;
            case R.id.tv_upload:
                showuploadprotocol();
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
    protected OrgDetailV2Contract.Presenter initPresenter() {
        return new OrgDetailV2Presenter(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UploadProtocolEvent event) {
        mPresenter.loadData(orgid);
    };
}
