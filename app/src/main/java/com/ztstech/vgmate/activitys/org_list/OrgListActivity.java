package com.ztstech.vgmate.activitys.org_list;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectDialog;
import com.ztstech.vgmate.activitys.org_list.adapter.OrgListPageAdapter;
import com.ztstech.vgmate.activitys.self_organization_detail.adapter.SelfOrganizationDetailPagerAdapter;
import com.ztstech.vgmate.data.beans.GetOrgListCountBean;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 组织名录
 */
public class OrgListActivity extends MVPActivity<OrgListContract.Presenter> implements
        OrgListContract.View, View.OnClickListener {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tableLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_left)
    ImageView ivLeft;

    private OrgListPageAdapter pagerAdapter;

    private LocationSelectDialog locationSelectDialog;

    /**筛选地址*/
    private String mDefaultLocation = "110101";

    /**标题*/
    private String[] titles = new String[] {"待确认", "待认领", "已认领", "网站端"};


    private LocationSelectDialog.OnLocationSelectListener onLocationSelectListener =
            new LocationSelectDialog.OnLocationSelectListener() {
                @Override
                public void onLocationSelected(String locationName, String locP, String locC, String locA) {
                    tvTitle.setText(locationName);
                    if (locA != null) {
                        mDefaultLocation = locA;
                    }else if (locC != null) {
                        mDefaultLocation = locC;
                    }else if (locP != null) {
                        mDefaultLocation = locP;
                    }
                    mPresenter.loadCount(mDefaultLocation);
                }
            };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_org_list;
    }

    @Override
    protected OrgListContract.Presenter initPresenter() {
        return new OrglistPresenter(this);
    }


    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tableLayout.setupWithViewPager(viewPager);

        pagerAdapter = new OrgListPageAdapter(getSupportFragmentManager());
        pagerAdapter.setTitles(titles);

        viewPager.setAdapter(pagerAdapter);

        ivLeft.setOnClickListener(this);

        mPresenter.loadCount(mDefaultLocation);
    }

    @OnClick(R.id.tv_title)
    public void onTitleClick(View v) {
        locationSelectDialog = new LocationSelectDialog(this);
        locationSelectDialog.setOnLocationSelectedListener(onLocationSelectListener);
        locationSelectDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view == ivLeft) {
            finish();
        }
    }

    @Override
    public void onLoadCountFinish(GetOrgListCountBean bean, String errmsg) {
        if (errmsg != null) {
//            ToastUtil.toastCenter(this, "查询失败：" + errmsg);
        }else if (bean != null && bean.info != null) {

            titles[0] = "待确认 " + bean.info.waitConfirmcount;
            titles[1] = "待认领 " + bean.info.waitClaimcount;
            titles[2] = "已认领 " + bean.info.alreadayClaimcount;
            titles[3] = "网页端 " + bean.info.webcount;

            pagerAdapter.setTitles(titles);
            tableLayout.setupWithViewPager(viewPager);

        }
    }
}
