package com.ztstech.vgmate.activitys.main;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_org.AddOrgActivity;
import com.ztstech.vgmate.activitys.add_sell_mate.AddSellMateActivity;
import com.ztstech.vgmate.activitys.create_share.create_share_info.CreateShareInfoActivity;
import com.ztstech.vgmate.activitys.main.adapter.MainPagerAdapter;
import com.ztstech.vgmate.activitys.main.widget.BottomBar;
import com.ztstech.vgmate.activitys.qr_code.scan.QRCodeScanActivity;
import com.ztstech.vgmate.activitys.setting.SettingActivity;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 主界面
 */
public class MainActivity extends MVPActivity<MainContract.Presenter> implements MainContract.View,
        BottomBar.OnTabItemClickListener, View.OnClickListener{

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private View dialog;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }


    @Override
    protected void onViewBindFinish() {
        bottomBar.setOnTabItemClickListener(this);

        vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        initDialog();

        topBar.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QRCodeScanActivity.class));
            }
        });

        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (View.GONE == dialog.getVisibility()) {
                    dialog.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    public void onItemClick(int index) {
        vpMain.setCurrentItem(index);
    }


    private void initDialog() {

        dialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_main_more,
                null);
        dialog.setVisibility(View.GONE);

        View dialogContainer = dialog.findViewById(R.id.rl_container);
        View rlAddMate = dialog.findViewById(R.id.rl_add_friend);
        View rlAddOrg = dialog.findViewById(R.id.rl_add_org);
        View rlShareInfo = dialog.findViewById(R.id.rl_share_info);
        View rlShareNotice = dialog.findViewById(R.id.rl_share_notice);

        View line1 = dialog.findViewById(R.id.line1);
        View line2 = dialog.findViewById(R.id.line2);
        View line3 = dialog.findViewById(R.id.line3);


        rlAddMate.setOnClickListener(this);
        dialogContainer.setOnClickListener(this);
        rlShareInfo.setOnClickListener(this);
        rlAddOrg.setOnClickListener(this);
        rlShareNotice.setOnClickListener(this);

        /**
         * 显示规则
         * 是否能添加伙伴：看身份是否审核过
         是否能添加机构：都可以添加机构
         是否能发布资讯和公告：是否是王总或一级销售
         */
        if (!TextUtils.equals(Constants.USER_ID_PASS,
                UserRepository.getInstance().getUser().getUserBean().info.status)) {
            //未审核通过
            rlAddMate.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
        }

        if (!UserRepository.getInstance().getUser().enableShare()) {
            rlShareInfo.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            rlShareNotice.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
        }


        addContentView(dialog, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));


    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_container:
                dialog.setVisibility(View.GONE);
                break;
            case R.id.rl_add_friend:
                //添加销售伙伴
                startActivity(new Intent(this, AddSellMateActivity.class));
                dialog.setVisibility(View.GONE);
                break;
            case R.id.rl_add_org:
                //添加机构
                startActivity(new Intent(this, AddOrgActivity.class));
                dialog.setVisibility(View.GONE);
                break;
            case R.id.rl_share_info:
                //分享资讯
                Intent itInfo = new Intent(this, CreateShareInfoActivity.class);
                itInfo.putExtra(CreateShareInfoActivity.ARG_TYPE, CreateShareApi.SHARE_INFO);
                startActivity(itInfo);
                dialog.setVisibility(View.GONE);
                break;
            case R.id.rl_share_notice:
                //分享公告
                Intent itNotice = new Intent(this, CreateShareInfoActivity.class);
                itNotice.putExtra(CreateShareInfoActivity.ARG_TYPE, CreateShareApi.SHARE_NOTICE);
                startActivity(itNotice);
                dialog.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
