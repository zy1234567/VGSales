package com.ztstech.vgmate.activitys.add_certification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next.PhoneCertificationActivity;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/20.
 */

public class RobAddVCertificationActivity extends MVPActivity<AddVContract.Presenter> implements AddVContract.View {

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rb_phone)
    RadioButton rbPhone;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rg_button)
    RadioGroup rgButton;
    @BindView(R.id.view_phone)
    View viewPhone;
    @BindView(R.id.view_home)
    View viewHome;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_need_phone)
    TextView tvNeedPhone;
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
    @BindView(R.id.rl_home)
    LinearLayout rlHome;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.rl_buttom)
    RelativeLayout rlButtom;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recoder_pass;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initView();
    }
    private void initView(){
        rbPhone.setChecked(true);
        rlHome.setVisibility(View.GONE);
        tvPass.setText("下一步");
        viewHome.setVisibility(View.INVISIBLE);
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_phone) {
                    tvNeedPhone.setVisibility(View.VISIBLE);
                    viewHome.setVisibility(View.INVISIBLE);
                    rlHome.setVisibility(View.GONE);
                    tvPass.setText("下一步");
                    viewPhone.setVisibility(View.VISIBLE);
                } else {
                    viewPhone.setVisibility(View.INVISIBLE);
                    rlHome.setVisibility(View.VISIBLE);
                    tvNeedPhone.setVisibility(View.GONE);
                    tvPass.setText("通过(加V认证)");
                    viewHome.setVisibility(View.VISIBLE);
                }
            }
        });
        rlButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbPhone.isChecked()){
                    Intent intent=new Intent(RobAddVCertificationActivity.this,
                            PhoneCertificationActivity.class);
                    startActivity(intent);
                }else {

                }
            }
        });
    }

    @Override
    public void onSubmitFinish(String msg) {

    }

    @Override
    protected AddVContract.Presenter initPresenter() {
        return new AddVPresenter(this);
    }

}
