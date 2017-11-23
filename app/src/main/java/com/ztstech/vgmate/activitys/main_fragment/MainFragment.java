package com.ztstech.vgmate.activitys.main_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.adapter.MainFragmentPagerAdapter;
import com.ztstech.vgmate.activitys.mate_approve.UnApproveMateListActivity;
import com.ztstech.vgmate.activitys.org_follow.OrgFollowActivity;
import com.ztstech.vgmate.activitys.sell_mate_list.SellMateListActivity;
import com.ztstech.vgmate.activitys.setting.SettingActivity;
import com.ztstech.vgmate.data.beans.MainPageBean;
import com.ztstech.vgmate.data.beans.UserBean;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 资讯
 */
public class MainFragment extends MVPFragment<MainContract.Presenter> implements MainContract.View {

    /**
     * 请求机构列表，如果机构列表重新筛选，需要重新刷新数字
     */
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

    /**
     * 身份状态
     */
    @BindView(R.id.tv_id_status)
    ImageView imgIdStatus;

    /**
     * 编辑按钮
     */
    @BindView(R.id.img_setting)
    ImageView imgSetting;

    /**
     * 预计到账
     */
    @BindView(R.id.tv_money_ready)
    TextView tvMoneyReady;
    /**
     * 实际到账
     */
    @BindView(R.id.tv_money_already)
    TextView tvMoneyAlready;
    /**
     * 已经完成
     */
    @BindView(R.id.tv_money_finish)
    TextView tvMoneyFinish;
    /**
     * 介绍人
     */
    @BindView(R.id.tv_introducer)
    TextView tvIntroducer;
    /**
     * 待审批数
     */
    @BindView(R.id.tv_wait_num)
    TextView tvWaitNum;
    /**
     * 待审批
     */
    @BindView(R.id.ll_wait)
    LinearLayout llWait;
    /**
     * 销售伙伴数
     */
    @BindView(R.id.tv_mate_num)
    TextView tvMateNum;
    /**
     * 跟进客户数
     */
    @BindView(R.id.tv_custom_num)
    TextView tvCustomNum;
    Unbinder unbinder;


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
        mPresenter.loadUserInfo();
        mPresenter.loadData();

    }

    @Override
    public void onStart() {
        super.onStart();

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

    @OnClick({R.id.rl_info,R.id.ll_wait,R.id.rl_mate,R.id.rl_custom})
    public void onSettingClick(View v) {
        switch (v.getId()){
            case R.id.rl_info:
                //点击跳转到设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.ll_wait:
                //点击跳转到待审批
                startActivity(new Intent(getActivity(), UnApproveMateListActivity.class));
                break;
            case R.id.rl_mate:
                //销售伙伴
                Intent intent = new Intent(getActivity(), SellMateListActivity.class);
                intent.putExtra(SellMateListActivity.KEY_MATE_NUM,tvMateNum.getText().toString());
                startActivity(intent);
                break;
            case R.id.rl_custom:
                //客户跟进
                startActivity(new Intent(getActivity(), OrgFollowActivity.class));
                break;
            default:
                break;
        }
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
        tvMateNum.setText(String.valueOf(mainPageBean.info.firstcnt));
        tvCustomNum.setText(String.valueOf(mainPageBean.info.followOrgNum));
        int waitNum = mainPageBean.info.waitAuditNum;
        if (waitNum > 0){
            llWait.setVisibility(View.VISIBLE);
            tvWaitNum.setText(String.valueOf(waitNum));
            imgSetting.setVisibility(View.GONE);
        }else {
            llWait.setVisibility(View.GONE);
            imgSetting.setVisibility(View.VISIBLE);
        }
        tvName.setText(mainPageBean.info.uname);

        String status = UserRepository.getInstance().getUser().getUserBean()
                .info.status;
        if (Constants.USER_ID_CHECKING.equals(status) || Constants.USER_ID_WILL_CHECK.equals(status)) {
            //身份审核中
            imgIdStatus.setVisibility(View.VISIBLE);
        } else {

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
