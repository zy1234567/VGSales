package com.ztstech.vgmate.activitys.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.add_sell_mate.AddSellMateActivity;
import com.ztstech.vgmate.activitys.main_fragment.adapter.MainFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.org_list.OrgListActivity;
import com.ztstech.vgmate.activitys.sell_mate_list.SellMateListActivity;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 资讯
 */
public class MainFragment extends MVPFragment<MainContract.Presenter> implements MainContract.View {


    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.img_header)
    ImageView imgHeader;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_location)
    TextView tvLocation;

    /**预计到账*/
    @BindView(R.id.tv_money_ready)
    TextView tvMoneyReady;
    /**实际到账*/
    @BindView(R.id.tv_money_already)
    TextView tvMoneyAlready;
    /**已经完成*/
    @BindView(R.id.tv_money_finish)
    TextView tvMoneyFinish;
    /**新机会+ */
    @BindView(R.id.tv_new_chance)
    TextView tvNewChance;

    /**销售伙伴数*/
    @BindView(R.id.tv_mate)
    TextView tvMate;

    /**介绍人*/
    @BindView(R.id.tv_introducer)
    TextView tvIntroducer;
    /**机构名录*/
    @BindView(R.id.tv_org)
    TextView tvOrg;


    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager()));

        mPresenter.loadData();

    }


    @OnClick(R.id.tv_mate)
    public void onAddSellMateClick(View v) {
        //点击增加销售伙伴
        startActivity(new Intent(getActivity(), SellMateListActivity.class));
    }

    @OnClick(R.id.tv_org)
    public void onSelfOrganizationClick(View v) {
        //点击自拓机构
        startActivity(new Intent(getActivity(), OrgListActivity.class));
    }

    @Override
    public void loadError(String errorMessage) {
        ToastUtil.toastCenter(getActivity(), errorMessage);
    }

    @Override
    public void setData(MainPageBean mainPageBean) {
        if (mainPageBean == null || mainPageBean.info == null) {
            return;
        }

        tvMoneyReady.setText("¥" + String.valueOf(mainPageBean.info.maxmoney));
        tvMoneyFinish.setText("¥" + String.valueOf(mainPageBean.info.finalmoney));
        tvMoneyAlready.setText("¥" + String.valueOf(mainPageBean.info.realmoney));
        tvNewChance.setText("销售机会：+" + mainPageBean.info.comnum);
        tvName.setText(mainPageBean.info.uname);

        StringBuilder locationStr = new StringBuilder();
        String p = LocationUtils.getProvinceNameByAreaCode(mainPageBean.info.district);
        if (!TextUtils.isEmpty(p)) {
            locationStr.append(p);
        }
        String c = LocationUtils.getCityNameByAreaCode(mainPageBean.info.district);
        if (!TextUtils.isEmpty(c)) {
            if (locationStr.length() != 0) {
                locationStr.append(" ");
            }
            locationStr.append(c);
        }
        String a = LocationUtils.getLocationNameByCode(mainPageBean.info.district);
        if (!TextUtils.isEmpty(a)) {
            if (locationStr.length() != 0) {
                locationStr.append(" ");
            }
            locationStr.append(a);
        }

        tvLocation.setText(locationStr.toString());
        tvMate.setText("销售伙伴\n" + mainPageBean.info.firstcnt);
        tvIntroducer.setText("介绍人 " + mainPageBean.info.fname);
        tvOrg.setText("区县机构\n" + String.valueOf(mainPageBean.info.rbinum));

    }

    @Override
    public void setUserInfo(UserBean userBean) {
        Glide.with(getActivity())
                .load(userBean.info.picurl)
                .into(imgHeader);
        tvName.setText(userBean.info.uname);
    }
}
