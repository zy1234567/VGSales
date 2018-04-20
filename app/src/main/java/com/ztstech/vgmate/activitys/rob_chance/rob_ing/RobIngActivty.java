package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.ORG_CHECK_IN_OR_CALIM;
import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.PASSER_CHECK_IN;

/**
 * Created by dongdong on 2018/4/19.
 */

public class RobIngActivty extends BaseActivity {
    //传入得实体类key
    public static final String ORG_BEAN_ROB = "ORG_BEAN_ROB";
    //传入得身份key
    public static final String ORG_IDENTITY = "ORG_IDENTITY_KEY";
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_add_type)
    TextView tvAddType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_org_name)
    TextView tvOrgName;
    @BindView(R.id.tv_class_types)
    TextView tvClassTypes;
    @BindView(R.id.tv_otype)
    TextView tvOtype;
    @BindView(R.id.tv_locations)
    TextView tvLocations;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_detial_locations)
    TextView tvDetialLocations;
    @BindView(R.id.tv_detial_location)
    TextView tvDetialLocation;
    @BindView(R.id.tv_phones)
    TextView tvPhones;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_gpss)
    TextView tvGpss;
    @BindView(R.id.tv_gps)
    TextView tvGps;
    @BindView(R.id.img_ico)
    ImageView imgIco;
    @BindView(R.id.tv_ico_gps)
    TextView tvIcoGps;
    @BindView(R.id.rl_ico_gps)
    RelativeLayout rlIcoGps;
    @BindView(R.id.tv_names)
    TextView tvNames;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_org_count)
    TextView tvOrgCount;
    @BindView(R.id.tv_postions)
    TextView tvPostions;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.tv_tels)
    TextView tvTels;
    @BindView(R.id.tv_tel)
    TextView tvTel;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;
    @BindView(R.id.ll_layout_center)
    LinearLayout llLayoutCenter;

    RobChanceBean.ListBean bean;
    //传入得身份 路人/机构
    int identityFlg;
    /**其他补充最大长度*/
    static int maxLenght=100;
    /**拒绝原因01重复，02已关闭/暂停营业，03尚未营业，04机构不存在*/
    static  String []strReason=new String []{"01","02","03","04"};
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rob_ing;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initData();
        initView();
    }

    private void initView() {
        CommonUtil.orgfFromType(this, tvAddType, bean.cstatus, bean.nowchancetype, bean.chancetype);
        tvOtype.setText(CategoryUtil.findCategoryByOtype(bean.rbiotype));
        tvOrgName.setText(bean.rbioname);
        tvLocation.setText(LocationUtils.getPName(bean.rbiprovince)
                .concat(LocationUtils.getCName(bean.rbicity)).concat(LocationUtils.getAName(bean.rbidistrict)));
        tvDetialLocation.setText(bean.rbiaddress);
        tvPhone.setText(bean.rbicontphone);
        tvName.setText(bean.contractname);
        tvTel.setText(bean.contractphone);
        if (bean.orgcount >= 0) {
            tvOrgCount.setText("(名下机构数：".concat(String.valueOf(bean.orgcount)).concat(")"));
        }
        if (!TextUtils.isEmpty(bean.createrid)) {
            if (TextUtils.equals(bean.createrid, Constants.ORG_ADMIN)) {
                tvPostions.setText("机构一般管理人员");
            } else if (TextUtils.equals(bean.createrid, Constants.ORG_BOSS)) {
                tvPostions.setText("机构法人/老板/店长");
            }

        }
        if (identityFlg == PASSER_CHECK_IN) {
            llLayoutCenter.setVisibility(View.GONE);
        } else if (identityFlg == ORG_CHECK_IN_OR_CALIM) {
            llLayoutCenter.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        bean = new Gson().fromJson(getIntent().getStringExtra(ORG_BEAN_ROB), RobChanceBean.ListBean.class);
        identityFlg = getIntent().getIntExtra(ORG_IDENTITY, 0);
    }

    @OnClick({R.id.rl_ico_gps, R.id.tv_refuse, R.id.tv_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_ico_gps:
                break;
            case R.id.tv_refuse:
                DialogUtils.showRefuseReasonDialog(RobIngActivty.this, maxLenght, new DialogUtils.ShowRefuseReasonCallBack() {
                    @Override
                    public void confirm(TextView tvConfirm, EditText reason, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4) {

                    }

                    @Override
                    public void textChanged(TextView tvConfirm, EditText etReason, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, TextView tvNum) {
                        tvNum.setText(etReason.getText().toString().length()+"/"+maxLenght);
                        isCommit(etReason,rb1,rb2,rb3,rb4,tvConfirm);
                    }

                    @Override
                    public void radioButtonCheck(TextView tvConfirm, EditText etReason, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, TextView tvNum) {
                        isCommit(etReason,rb1,rb2,rb3,rb4,tvConfirm);
                    }
                });
                break;
            case R.id.tv_pass:
                break;
            default:
                break;
        }
    }
    /**
     * 判断是原因否为空以及按钮是否选择
     */
    private  void isCommit( EditText etReason ,RadioButton rb1,RadioButton rb2,RadioButton rb3,RadioButton rb4,TextView tvCommit){
        if(!TextUtils.equals(etReason.getText().toString(),"")&&!TextUtils.isEmpty(CommonUtil.isCheck(rb1,rb2,rb3,rb4,strReason))){
            tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_001);
            tvCommit.setClickable(true);
        }else {
            tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_104);
            tvCommit.setClickable(false);
        }
    }
}
