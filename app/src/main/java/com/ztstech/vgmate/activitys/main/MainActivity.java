package com.ztstech.vgmate.activitys.main;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_org.AddOrgActivity;
import com.ztstech.vgmate.activitys.add_sell_mate.AddSellMateActivity;
import com.ztstech.vgmate.activitys.create_share_info.CreateShareInfoActivity;
import com.ztstech.vgmate.activitys.info.EditInfoActivity;
import com.ztstech.vgmate.activitys.main.adapter.MainPagerAdapter;
import com.ztstech.vgmate.activitys.main.widget.BottomBar;
import com.ztstech.vgmate.data.api.CreateShareApi;
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
                startActivity(new Intent(MainActivity.this, EditInfoActivity.class));
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
        View tvAddMate = dialog.findViewById(R.id.tv_add_friend);
        View tvAddOrg = dialog.findViewById(R.id.tv_add_org);
        View tvShareInfo = dialog.findViewById(R.id.tv_share_info);
        View tvShareNotice = dialog.findViewById(R.id.tv_share_notice);


        tvAddMate.setOnClickListener(this);
        dialogContainer.setOnClickListener(this);
        tvShareInfo.setOnClickListener(this);
        tvAddOrg.setOnClickListener(this);
        tvShareNotice.setOnClickListener(this);


        addContentView(dialog, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        topBar.getRightImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转提供销售机会
                if (View.GONE == dialog.getVisibility()) {
                    dialog.setVisibility(View.VISIBLE);
                }

            }
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_container:
                dialog.setVisibility(View.GONE);
                break;
            case R.id.tv_add_friend:
                //添加销售伙伴
                startActivity(new Intent(this, AddSellMateActivity.class));
                dialog.setVisibility(View.GONE);
                break;
            case R.id.tv_add_org:
                //添加机构
                startActivity(new Intent(this, AddOrgActivity.class));
                dialog.setVisibility(View.GONE);
                break;
            case R.id.tv_share_info:
                //分享资讯
                Intent itInfo = new Intent(this, CreateShareInfoActivity.class);
                itInfo.putExtra(CreateShareInfoActivity.ARG_TYPE, CreateShareApi.SHARE_INFO);
                startActivity(itInfo);
                dialog.setVisibility(View.GONE);
                break;
            case R.id.tv_share_notice:
                //分享公告
                Intent itNotice = new Intent(this, CreateShareInfoActivity.class);
                itNotice.putExtra(CreateShareInfoActivity.ARG_TYPE, CreateShareApi.SHARE_NOTICE);
                startActivity(itNotice);
                dialog.setVisibility(View.GONE);
                break;
        }
    }
}
