package com.ztstech.vgmate.activitys.main_fragment;

import android.app.Activity;
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
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.adapter.MainFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.setting.SettingActivity;
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

    /**请求机构列表，如果机构列表重新筛选，需要重新刷新数字*/
    public static final int REQ_ORG_LIST = 1;

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

    /**身份状态*/
    @BindView(R.id.tv_id_status)
    ImageView imgIdStatus;

    /**预计到账*/
    @BindView(R.id.tv_money_ready)
    TextView tvMoneyReady;
    /**实际到账*/
    @BindView(R.id.tv_money_already)
    TextView tvMoneyAlready;
    /**已经完成*/
    @BindView(R.id.tv_money_finish)
    TextView tvMoneyFinish;
    /**介绍人*/
    @BindView(R.id.tv_introducer)
    TextView tvIntroducer;



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

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadUserInfo();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQ_ORG_LIST) {
            mPresenter.loadData();
        }
    }

    @OnClick(R.id.img_setting)
    public void onSettingClick(View v) {
        //点击跳转到设置
        startActivity(new Intent(getActivity(), SettingActivity.class));
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
        tvName.setText(mainPageBean.info.uname);

        if (Constants.USER_ID_CHECKING.equals(UserRepository.getInstance().getUser().getUserBean()
                .info.status)) {
            //身份审核中
            imgIdStatus.setVisibility(View.VISIBLE);
        }else {
            imgIdStatus.setVisibility(View.GONE);
        }

        StringBuilder locationStr = new StringBuilder();
        String p = LocationUtils.getPName(mainPageBean.info.district);
        if (!TextUtils.isEmpty(p)) {
            locationStr.append(p);
        }
        String c = LocationUtils.getCName(mainPageBean.info.district);
        if (!TextUtils.isEmpty(c)) {
            if (locationStr.length() != 0) {
                locationStr.append(" ");
            }
            locationStr.append(c);
        }
        String a = LocationUtils.getAName(mainPageBean.info.district);
        if (!TextUtils.isEmpty(a)) {
            if (locationStr.length() != 0) {
                locationStr.append(" ");
            }
            locationStr.append(a);
        }

        tvLocation.setText(locationStr.toString());
        tvIntroducer.setText("介绍人 " + mainPageBean.info.fname);

    }

    @Override
    public void setUserInfo(UserBean userBean) {
        Glide.with(getActivity())
                .load(userBean.info.picurl)
                .into(imgHeader);
        tvName.setText(userBean.info.uname);
    }
}
