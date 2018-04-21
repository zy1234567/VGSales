package com.ztstech.vgmate.activitys.add_certification.add_home_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.main_fragment.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/20.
 */

public class HomeFragment extends MVPFragment<HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.et_the_op_phone)
    EditText etTheOpPhone;
    @BindView(R.id.tv_phone_mail_the_op)
    TextView tvPhoneMailTheOp;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_content)
    EditText tvContent;
    @BindView(R.id.tv_content_count)
    TextView tvContentCount;
    @BindView(R.id.tv_more)
    EditText tvMore;
    @BindView(R.id.tv_more_count)
    TextView tvMoreCount;
    @BindView(R.id.rl_dismiss)
    RelativeLayout rlDismiss;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    Unbinder unbinder;
    @BindView(R.id.tv_need_phone)
    TextView tvNeedPhone;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_way_home;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        tvNeedPhone.setVisibility(View.GONE);
    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
