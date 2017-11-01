package com.ztstech.vgmate.activitys.complete_org_info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

import butterknife.BindView;

/**
 * 完善机构资料,日，这个界面改了，卧槽
 * <p>
 *     subview包中的界面也废掉了。。。<br></br>
 *     第二版{@link com.ztstech.vgmate.activitys.complete_org_info_v2.CompleteOrgInfoV2Activity}
 * </p>
 *
 */
@Deprecated
public class CompleteOrgInfoActivity extends MVPActivity<CompleteOrgInfoContract.Presenter>
        implements CompleteOrgInfoContract.View, View.OnClickListener {


    @BindView(R.id.ll_basic_info)
    View vBasicInfo;
    @BindView(R.id.ll_pic_video)
    View vPicVideo;
    @BindView(R.id.ll_org_info)
    View vOrgInfo;
    @BindView(R.id.ll_class_info)
    View vClassInfo;
    @BindView(R.id.ll_charge_info)
    View vChargeInfo;
    @BindView(R.id.ll_tag)
    View vEnroll;               //招生简章
    @BindView(R.id.ll_teacher)
    View vTeacher;
    @BindView(R.id.ll_phone)
    View vPhone;
    @BindView(R.id.ll_contact)
    View vContact;

    @BindView(R.id.tv_basic_info)
    TextView tvBasicInfo;
    @BindView(R.id.tv_pic_video)
    TextView tvPicVideo;
    @BindView(R.id.tv_org_info)
    TextView tvOrgInfo;
    @BindView(R.id.tv_class_info)
    TextView tvClassInfo;
    @BindView(R.id.tv_charge_info)
    TextView tvChargeInfo;
    @BindView(R.id.tv_tag)
    TextView tvEnroll;               //招生简章
    @BindView(R.id.tv_teacher)
    TextView tvTeacher;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_contact)
    TextView tvContact;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_complete_org_info;
    }

    @Override
    protected CompleteOrgInfoContract.Presenter initPresenter() {
        return new CompleteOrgInfoPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        vBasicInfo.setOnClickListener(this);
        vChargeInfo.setOnClickListener(this);
        vClassInfo.setOnClickListener(this);
        vEnroll.setOnClickListener(this);
        vOrgInfo.setOnClickListener(this);
        vPhone.setOnClickListener(this);
        vPicVideo.setOnClickListener(this);
        vTeacher.setOnClickListener(this);
        vContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
