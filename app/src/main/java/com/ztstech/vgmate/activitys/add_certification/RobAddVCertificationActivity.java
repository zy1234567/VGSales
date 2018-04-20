package com.ztstech.vgmate.activitys.add_certification;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * Created by Administrator on 2018/4/20.
 */

public class RobAddVCertificationActivity extends MVPActivity<AddVContract.Presenter>implements AddVContract.View {
    @Override
    public void onSubmitFinish(String msg) {

    }

    @Override
    protected AddVContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return  R.layout.activity_recoder_pass;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
    }
}
