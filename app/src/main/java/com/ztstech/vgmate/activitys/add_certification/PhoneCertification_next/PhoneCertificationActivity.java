package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.data.dto.OrgPassData;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhoneCertificationActivity  extends MVPActivity<PhoneCertificationContract.Presenter>
        implements PhoneCertificationContract.View {
    //orgpassdata实体类
    public static final String ORG_PASS_DATA = "orgpassdata_key";
    OrgPassData orgPassData;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_weixin_check;
    }
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initData();
    }

    private void initData(){
        orgPassData = new Gson().fromJson(getIntent().getStringExtra(ORG_PASS_DATA), OrgPassData.class);
    }
    @Override
    protected PhoneCertificationContract.Presenter initPresenter() {
        return new PhoneCertificationPresenter(this);
    }


}
