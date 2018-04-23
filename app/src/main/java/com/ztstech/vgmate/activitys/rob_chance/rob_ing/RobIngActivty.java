package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import android.content.Intent;
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
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.RobAddVCertificationActivity;
import com.ztstech.vgmate.activitys.add_certification.appoint_sale.RobAddVAppointSaleActivity;
import com.ztstech.vgmate.data.beans.OrgFollowlistBean;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.data.dto.RefuseOrPassData;
import com.ztstech.vgmate.event.ApproveEvent;
import com.ztstech.vgmate.utils.CategoryUtil;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.LocationUtils;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.ORG_CHECK_IN_OR_CALIM;
import static com.ztstech.vgmate.activitys.rob_chance.adapter.RobChanceViewHolder.PASSER_CHECK_IN;

/**
 * Created by dongdong on 2018/4/19.
 */

public class RobIngActivty extends MVPActivity<RobIngContract.Presenter>implements RobIngContract.View {
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


    /**当前机构来源是 路人登记，机构登记，机构认领*/
    public static  boolean  isNormalRegister;
    public static  boolean isOrgRegister;
    public static  boolean isOrgCalim;
    int type;
    /**拒绝原因01重复，02已关闭/暂停营业，03尚未营业，04机构不存在*/
    static  String []strReason=new String []{"01","02","03","04"};
    /**拒绝认领*/
    RefuseOrPassData refuseOrPassData;
    /**status 01拒绝  00t通过*/
    static String refuseStatus="01";
    static String passStatus="00";

    /**identificationtype 02加v通过 01 定位通过*/
    static String lIdenttype="01";
    static String vIdenttype="02";
    /**
     * 处理终端
     */
    public static final String TENMINAL_TYPE = "02";
    /**登记机构拒绝*/
    RefuseOrPassData orgRegisterRefuseData;

    private CountDownHandler mCountDownHandler;

    private OrgPassData orgPassData;
    /** 指定销售 审核*/
    public static final String APPOINT_SALE_KEY="appoint_sale_key";
    public static final String APPOINT_SALE_VALUE="appoint_sale_value";
     /**是否是指定销售*/
     boolean ISSALE=false;
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
        if(isNormalRegister){
            tvPass.setText("通过(定位认证)");
        }else {
            tvPass.setText("通过(加V认证)");
        }
        if(ISSALE){
            tvNames.setText("认领人");
            tvTime.setVisibility(View.GONE);
        }else {
            tvNames.setText("对方姓名");
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(mCountDownHandler.getCurrentText());
        }
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

        String[] gps = bean.rbigps.split(",");
        tvGps.setText("E"+CommonUtil.convertToSexagesimal(Double.parseDouble(gps[0])).
                concat("N"+CommonUtil.convertToSexagesimal(Double.parseDouble(gps[1]))));
    }
    /**转化数据源bean*/
    private  void changeBean(){
        OrgFollowlistBean.ListBean orgFollowlistBean=  new Gson().fromJson(getIntent().
                getStringExtra(ORG_BEAN_ROB), OrgFollowlistBean.ListBean.class);
        bean.rbioname=orgFollowlistBean.rbioname;
        bean.rbiotype=orgFollowlistBean.rbiotype;
        bean.rbicity=orgFollowlistBean.rbicity;
        bean.rbiaddress=orgFollowlistBean.rbiaddress;
        bean.rbicontphone=orgFollowlistBean.rbicontphone;
        bean.rbigps=orgFollowlistBean.rbigps;
        bean.contractname=orgFollowlistBean.contractname;
        bean.contractphone=orgFollowlistBean.contractphone;
        bean.calid=orgFollowlistBean.calid;
        bean.rbiprovince=orgFollowlistBean.rbiprovince;
        bean.rbidistrict=orgFollowlistBean.rbidistrict;
        bean.chancetype=orgFollowlistBean.chancetype;
        bean.nowchancetype=orgFollowlistBean.nowchancetype;
        bean.cstatus=orgFollowlistBean.cstatus;
        bean.rbiid=orgFollowlistBean.rbiid;
    }
    //初始化数据
    private void initData() {
        bean=new RobChanceBean.ListBean();
        if(getIntent().getStringExtra(APPOINT_SALE_KEY)!=null&&
                getIntent().getStringExtra(APPOINT_SALE_KEY).equals(APPOINT_SALE_VALUE)){
            ISSALE=true;
            changeBean();
        }else {
            ISSALE=false;
            bean = new Gson().fromJson(getIntent().getStringExtra(ORG_BEAN_ROB), RobChanceBean.ListBean.class);
        }
        identityFlg = getIntent().getIntExtra(ORG_IDENTITY, 0);
        type= CommonUtil.identity(bean.cstatus,bean.nowchancetype,bean.chancetype);
        if(Constants.NORMAL_REGISTER== type)
        {
            isNormalRegister = true;
        }else if(Constants.ORG_CALIM==type){
            isOrgCalim=true;
        }else if(Constants.ORG_REGISTER==type){
            isOrgRegister=true;
        }
        if(!ISSALE) {
            double lasttime = getIntent().getDoubleExtra(LAST_TIME, 0);
            mCountDownHandler = new CountDownHandler(this, lasttime);
            mCountDownHandler.startTimer();
        }
    }

    @OnClick({R.id.rl_ico_gps, R.id.tv_refuse, R.id.tv_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_ico_gps:
                break;
            case R.id.tv_refuse:
                refuseReason();
                break;
            case R.id.tv_pass:
                if(isNormalRegister){
                    locationPass();
                }else {
                    if(getIntent().getStringExtra(APPOINT_SALE_KEY)!=null&&
                            getIntent().getStringExtra(APPOINT_SALE_KEY).equals(APPOINT_SALE_VALUE)){
                        Intent intent = new Intent(this,RobAddVAppointSaleActivity.class);
                        intent.putExtra(ORG_BEAN_ROB,new Gson().toJson(bean));
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(this,RobAddVCertificationActivity.class);
                        intent.putExtra(RobAddVCertificationActivity.ORG_BEAN,new Gson().toJson(bean));
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     *通过定位认证
     */
    private  void locationPass(){
        refuseOrPassData =new RefuseOrPassData();
        refuseOrPassData.calid=bean.calid;
        refuseOrPassData.rbiid=String.valueOf(bean.rbiid);
        refuseOrPassData.status=passStatus;
        refuseOrPassData.identificationtype=lIdenttype;
        DialogUtils.showdialogbottomtwobutton(RobIngActivty.this, "取消","确定",
                "通过提示","您确定要通过该机构的定位认证吗？",
                new DialogUtils.showdialogbottomtwobuttonCallBack() {
                    @Override
                    public void tvRightClick() {
                        orgPassData.rbiid  = String.valueOf(bean.rbiid);
                        orgPassData.terminal = TENMINAL_TYPE;
                        orgPassData.rbiostatus = Constants.PASS_ORG;
                        orgPassData.identificationtype = Constants.IDENTIFICATION_TYPE_REGISTER_LOCATION;
                        orgPassData.type = Constants.COMMUNICATION_TYPE_CHANCE;
                        mPresenter.locationCertificationCommit(orgPassData);
                    }
                    @Override
                    public void tvLeftClick() {

                    }
                });
    }
    /**判断登记 认领拒绝*/
    private  void  refuseReason(){
        if(isNormalRegister||isOrgRegister){
            DialogUtils.getInstance().showRefuseReasonDialog(RobIngActivty.this, maxLenght,
                    new DialogUtils.ShowRefuseReasonCallBack() {
                        @Override
                        public void confirm(TextView tvConfirm, EditText etReason, RadioButton rb1,
                                            RadioButton rb2, RadioButton rb3, RadioButton rb4) {
                            orgRegisterRefuseData=new RefuseOrPassData();
                            orgRegisterRefuseData.oname=bean.rbioname;
                            orgRegisterRefuseData.rbiid=String .valueOf(bean.rbiid);
                            orgRegisterRefuseData.type="00";
                            orgRegisterRefuseData.refuse=etReason.getText().toString();
                            orgRegisterRefuseData.rubbishtype=CommonUtil.isCheck(rb1,rb2,rb3,rb4,strReason);
                            if(isNormalRegister) {
                                mPresenter.refuseRegisterCommit(orgRegisterRefuseData,Constants.NORMAL_REGISTER);
                            }else {
                                mPresenter.refuseRegisterCommit(orgRegisterRefuseData,Constants.ORG_REGISTER);
                            }
                        }

                        @Override
                        public void textChanged(TextView tvConfirm, EditText etReason, RadioButton rb1,
                                                RadioButton rb2, RadioButton rb3, RadioButton rb4, TextView tvNum) {
                            tvNum.setText(etReason.getText().toString().length()+"/"+maxLenght);
                            isCommit(etReason,rb1,rb2,rb3,rb4,tvConfirm);
                        }

                        @Override
                        public void radioButtonCheck(TextView tvConfirm, EditText etReason, RadioButton rb1,
                                                     RadioButton rb2, RadioButton rb3, RadioButton rb4, TextView tvNum) {
                            isCommit(etReason,rb1,rb2,rb3,rb4,tvConfirm);
                        }
                    });
        }else if(isOrgCalim){
            refuseOrPassData =new RefuseOrPassData();
            refuseOrPassData.rbiid=String.valueOf(bean.rbiid);
            refuseOrPassData.status=refuseStatus;
            refuseOrPassData.calid=bean.calid;
            DialogUtils.showdialogbottomtwobutton(RobIngActivty.this, "取消","确定",
                    "拒绝提示","你确定要拒绝吗？", new DialogUtils.showdialogbottomtwobuttonCallBack() {
                        @Override
                        public void tvRightClick() {
                            mPresenter.refuse0rPassCommit(refuseOrPassData,Constants.ORG_CALIM);
                        }
                        @Override
                        public void tvLeftClick() {

                        }
                    });

        }
    }
    /**
     * 判断是原因否为空以及按钮是否选择
     */
    private  void isCommit( EditText etReason ,RadioButton rb1,RadioButton rb2,RadioButton rb3,
                            RadioButton rb4,TextView tvCommit){
        if(!TextUtils.isEmpty(CommonUtil.isCheck(rb1,
                rb2,rb3,rb4,strReason))){
            tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_001);
            tvCommit.setClickable(true);
        }else {
            tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_104);
            tvCommit.setClickable(false);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownHandler != null) {
            mCountDownHandler.cancel();
        }
    }

    @Override
    protected RobIngContract.Presenter initPresenter() {
        return new RobIngPresenter(this);
    }

    @Override
    public void Success() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onSubmitFinish(String errorMessage) {

    }

    /**
     * 倒计时时间变化调用
     * @param newTimeText
     */
    private void onTimeChanged(String newTimeText) {
        tvTime.setText(newTimeText);
    }

    /**
     * 倒计时结束
     */
    private void onTimeFinish() {
        tvTime.setText("超时");
        tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
        tvRefuse.setBackgroundResource(R.drawable.bg_c_2_f_104);
        tvRefuse.setEnabled(false);
        tvPass.setEnabled(false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ApproveEvent event) {
        finish();
    };

    private static class CountDownHandler extends Handler {

        private int mMinute;
        private int mSecond;

        private Timer timer;
        private TimerTask timerTask;

        private WeakReference<RobIngActivty> mActivityRef;

        public CountDownHandler(RobIngActivty activty, double lasttime) {
            mActivityRef = new WeakReference<RobIngActivty>(activty);

            String[] minteSecend = CommonUtil.secondToMinute(lasttime).split(":");
            if (minteSecend != null || minteSecend.length >= 2) {
                mMinute = Integer.parseInt(minteSecend[0]);
                mSecond = Integer.parseInt(minteSecend[1]);
            }else {
                // TODO: 2018/4/21 脏数据，记录log，给后台提示
            }
        }


        /**
         * 开始倒计时
         */
        public void startTimer() {
            timerTask = new TimerTask() {

                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                    sendMessage(msg);
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 0, 1000);
        }


        public String getCurrentText() {
            if (mSecond >= 10) {
                return "" + mMinute + ":" + mSecond;
            } else {
                return "" + mMinute + ":0" + mSecond;
            }
        }


        public void cancel() {
            if (timer != null){
                timer.cancel();
                timer = null;
            }
            if (timerTask != null){
                timerTask = null;
            }
        }


        public void handleMessage(Message msg){
            if (isCountDownOver()) {
                callbackActivityOnTimeFinish();
                return;
            }

            if (mMinute == 0) {
                if (mSecond != 0) {
                    mSecond--;
                    callbackActivityTimeTextChange();
                }

            } else {
                if (mSecond == 0) {
                    mSecond = 59;
                    mMinute--;
                    callbackActivityTimeTextChange();
                } else {
                    mSecond--;
                    if (mSecond >= 10) {
                        callbackActivityTimeTextChange();
                    } else {
                        callbackActivityTimeTextChange();
                    }
                }
            }
        } // handle message finish

        private boolean isCountDownOver() {
            return mMinute == 0 && mSecond == 0;
        }

        /**
         * 回调Activity 倒计时结束
         */
        private void callbackActivityOnTimeFinish() {
            RobIngActivty activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeFinish();
        }

        /**
         * 回调Activity 倒计时时间变化
         */
        private void callbackActivityTimeTextChange() {
            RobIngActivty activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeChanged(getCurrentText());
        }

    }
}
