package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhoneCertificationActivity  extends MVPActivity<PhoneCertificationContract.Presenter>
        implements PhoneCertificationContract.View {
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
    }
    @Override
    protected PhoneCertificationContract.Presenter initPresenter() {
        return new PhoneCertificationPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_weixin_check;
    }
}
