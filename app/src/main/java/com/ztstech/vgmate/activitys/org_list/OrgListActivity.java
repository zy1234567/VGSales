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

    /**标题*/
    private String[] titles = new String[] {"待确认", "待认领", "已认领", "网站端"};


    private LocationSelectDialog.OnLocationSelectListener onLocationSelectListener =
            new LocationSelectDialog.OnLocationSelectListener() {
                @Override
                public void onLocationSelected(String locationName, String locP, String locC, String locA) {
                    tvTitle.setText(locationName);
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
}
