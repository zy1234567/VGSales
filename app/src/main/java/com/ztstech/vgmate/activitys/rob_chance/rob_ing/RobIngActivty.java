package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.util.Timer;
import java.util.TimerTask;

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
    //传入剩余时间
    public static final String LAST_TIME = "LAST_TIME_KEY";
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
    //纯如的剩余时间
    double lasttime;
    int minute;
    int second;
    private Timer timer;
    private TimerTask timerTask;
    /**当前机构来源是 路人登记，机构登记，机构认领*/
    static  boolean  isNormalRegister;
    static  boolean isOrgRegister;
    static  boolean isOrgCalim;
    int type;
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
        tvTime.setText(minute + ":" + second);

        timerTask = new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 0, 1000);
    }
    //初始化数据
    private void initData() {
        bean = new Gson().fromJson(getIntent().getStringExtra(ORG_BEAN_ROB), RobChanceBean.ListBean.class);
        identityFlg = getIntent().getIntExtra(ORG_IDENTITY, 0);
        lasttime = getIntent().getDoubleExtra(LAST_TIME,0);
        String minteSecend[] = null;
        minteSecend = CommonUtil.secondToMinute(lasttime).split(":");
        minute = Integer.parseInt(minteSecend[0]);
        second = Integer.parseInt(minteSecend[1]);
        type= CommonUtil.identity(bean.cstatus,bean.nowchancetype,bean.chancetype);
        if(Constants.NORMAL_REGISTER== type)
        {
            isNormalRegister = true;
        }else if(Constants.ORG_CALIM==type){
           isOrgCalim=true;
        }else if(Constants.ORG_REGISTER==type){
            isOrgRegister=true;
        }
    }
    @SuppressLint("HandlerLeak")
    private Handler handler =  new Handler(){
        public void handleMessage(Message msg){
            if (minute == 0) {
                if (second == 0) {
                    tvTime.setText("超时");
                    tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
                    tvRefuse.setBackgroundResource(R.drawable.bg_c_2_f_104);
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    if (timerTask != null) {
                        timerTask = null;
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        tvTime.setText("0" + minute + ":" + second);
                    } else {
                        tvTime.setText("0" + minute + ":0" + second);
                    }
                }
            } else {
                if (second == 0) {
                    second = 59;
                    minute--;
                    if (minute >= 10) {
                        tvTime.setText(minute + ":" + second);
                    } else {
                        tvTime.setText("0" + minute + ":" + second);
                    }
                } else {
                    second--;
                    if (second >= 10) {
                        if (minute >= 10) {
                            tvTime.setText(minute + ":" + second);
                        } else {
                            tvTime.setText("0" + minute + ":" + second);
                        }
                    } else {
                        if (minute >= 10) {
                            tvTime.setText(minute + ":0" + second);
                        } else {
                            tvTime.setText("0" + minute + ":0" + second);
                        }
                    }
                }
            }
        }
    };
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
    /**判断登记 认领拒绝*/
    private  void  refuseReason(){
        if(isNormalRegister){

        }else if(isOrgRegister){

        }else if(isOrgCalim){

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

    @Override
    protected void onDestroy() {
        if (timer != null){
            timer.cancel();
            timer = null;
        }
        if (timerTask != null){
            timerTask = null;
        }
        super.onDestroy();

    }
}
