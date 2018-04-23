package com.ztstech.vgmate.activitys.add_certification.add_phone_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPFragment;
import com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next.PhoneCertificationActivity;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhoneFragment extends MVPFragment<PhoneContract.Presenter> implements PhoneContract.View {

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
    Unbinder unbinder;
    @BindView(R.id.ll_next)
    LinearLayout llNext;
    public static PhoneFragment newInstance() {
        PhoneFragment fragment = new PhoneFragment();
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_way_home;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);
        llNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PhoneCertificationActivity. class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected PhoneContract.Presenter initPresenter() {
        return new PhonePresenter(this);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
