package com.ztstech.vgmate.activitys.org_list.fragments.comfirmed;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.org_list.fragments.comfirmed.adapter.OrglistConfirmedPagerAdapter;

import butterknife.BindView;

/**
 * 已认领
 */
public class OrglistConfirmedFragment extends MVPFragment<OrglistContirmedContract.Presenter>
        implements OrglistContirmedContract.View, View.OnClickListener {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tv_left)
    TextView tvLeft;

    @BindView(R.id.tv_right)
    TextView tvRight;

    private OrglistConfirmedPagerAdapter pagerAdapter;

    public OrglistConfirmedFragment() {
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_orglist_confirmed;
    }

    @Override
    protected OrglistContirmedContract.Presenter initPresenter() {
        return new OrglistConfirmedPresenter(this);
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        tvLeft.setSelected(true);
        tvRight.setSelected(false);

        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        pagerAdapter = new OrglistConfirmedPagerAdapter(getChildFragmentManager());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvLeft.setSelected(position == 0);
                tvRight.setSelected(position == 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view == tvLeft) {
            tvLeft.setSelected(true);
            tvRight.setSelected(false);
            viewPager.setCurrentItem(0);
        }else if (view == tvRight) {
            tvRight.setSelected(true);
            tvLeft.setSelected(false);
            viewPager.setCurrentItem(1);
        }
    }
}
