package com.ztstech.vgmate.activitys.org_detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * 机构详情
 */
public class OrgDetailActivity extends MVPActivity<OrgDetailContract.Presenter> implements
        OrgDetailContract.View {


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_detail;
    }

    @Override
    protected OrgDetailContract.Presenter initPresenter() {
        return new OrgDetailPresenter(this);
    }
}
