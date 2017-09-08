package com.ztstech.vgmate.activitys.main_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.add_sell_mate.AddSellMateActivity;
import com.ztstech.vgmate.activitys.main_fragment.adapter.MainFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.org_list.OrgListActivity;
import com.ztstech.vgmate.activitys.self_organization.SelfOrganizationActivity;
import com.ztstech.vgmate.activitys.sell_chance.SellChanceActivity;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
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
    protected void onCreateViewFinish(@Nullable Bundle savedInstanceState) {
        super.onCreateViewFinish(savedInstanceState);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getChildFragmentManager()));

        mPresenter.loadData();

    }

//    @OnClick(R.id.fl_sell_chance)
//    public void onSellChanceClick(View v) {
//        startActivity(new Intent(getActivity(), SellChanceActivity.class));
//    }

    @OnClick(R.id.tv_mate)
    public void onAddSellMateClick(View v) {
        //点击增加销售伙伴
        startActivity(new Intent(getActivity(), AddSellMateActivity.class));
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

    }

    @Override
    public void setUserInfo(UserBean userBean) {
        Glide.with(getActivity()).load(userBean.info.picurl).into(imgHeader);
        tvName.setText(userBean.info.uname);
    }
}
