package com.ztstech.vgmate.activitys.add_certification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next.PhoneCertificationActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.event.ApproveEvent;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.ContractUtils;
import com.ztstech.vgmate.utils.MaxTextLengthFilter;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.DialogMultiSelect;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.appdomain.constants.Constants.ORG_CALIM;
import static com.ztstech.appdomain.constants.Constants.ORG_REGISTER;

/**
 * Created by Administrator on 2018/4/20.
 */

public class RobAddVCertificationActivity extends MVPActivity<AddVContract.Presenter> implements AddVContract.View,View.OnClickListener {
    /**最多4张图*/
    public static final int MAX_PIC_COUNT = 5;
    /**
     * 02:其他人员,05:管理员,09:老板（法人）
     */
    public static final String IDEN_NOMAL = "02";
    public static final String IDEN_MANAGER = "05";
    public static final String IDEN_BOSS = "09";
    /**
     * 请求联系人手机
     */
    public static final int REQ_CONTACT_1 = 1;
    /**
     * 请求gps信息
     */
    public static final int REQ_GPS = 3;
    /**
     * 请求拍照
     */
    private static final int REQUEST_CODE = 29;
    /**
     *上个页面的实体类
     */
    public static final String ORG_BEAN = "org_bean";
    /**
     * 处理终端
     */
    public static final String TENMINAL_TYPE = "02";
    /**
     * 接收微信认证审核事件，销毁页面
     */
    public static final String APPROVE_FINISH = "approve_finish";
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
    @BindView(R.id.et_name)
    EditText etName;
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
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content_count)
    TextView tvContentCount;
    @BindView(R.id.et_more)
    EditText etMore;
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
    @BindView(R.id.rl_position)
    RelativeLayout rlPosition;
    @BindView(R.id.cgv)
    CustomGridView cgv;
    private ImageView imgAddImg;
    /**图片文件地址*/
    private List<String> imageFiles = new ArrayList<>();
    private String[] file;
    private OrgPassData orgPassData = new OrgPassData();
    private String[] datastr = {"机构法人/老板/店长", "一般管理人员", "其他机构人员"};
    private DialogMultiSelect dialogMultiSelect;
    RobChanceBean.ListBean bean;
    private CountDownHandler mCountDownHandler;
    double lasttime;

    int maxLenght=300;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_recoder_pass;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        etTheOpPhone.addTextChangedListener(new EditOnclick());
        etName.addTextChangedListener(new EditOnclick());
        initData();
        initView();
        addDefaultImage();
    }

    private void initView() {
        rbPhone.setChecked(true);
        rlHome.setVisibility(View.GONE);
        tvPass.setText("下一步");
        viewHome.setVisibility(View.INVISIBLE);
        if(!TextUtils.isEmpty(bean.rbioname)){
            topBar.setTitle(bean.rbioname);
        }else {
            topBar.setTitle("审核方式");
        }
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvContentCount.setText(etContent.getText().toString().length()+"/"+ maxLenght);
            }
        });
        etMore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvMoreCount.setText(etMore.getText().toString().length()+"/"+ maxLenght);
            }
        });
        etContent.setFilters(new InputFilter[]{new MaxTextLengthFilter(RobAddVCertificationActivity.this,maxLenght)});
        etMore.setFilters(new InputFilter[]{new MaxTextLengthFilter(RobAddVCertificationActivity.this,maxLenght)});
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_phone) {
                    tvNeedPhone.setVisibility(View.VISIBLE);
                    viewHome.setVisibility(View.INVISIBLE);
                    rlHome.setVisibility(View.GONE);
                    tvPass.setText("下一步");
                    viewPhone.setVisibility(View.VISIBLE);
                    orgPassData.communicationtype = Constants.PHONE_CATION_TYPE;
                    buttontype();
                    orgPassData.callon = "00";
                } else {
                    viewPhone.setVisibility(View.INVISIBLE);
                    rlHome.setVisibility(View.VISIBLE);
                    tvNeedPhone.setVisibility(View.GONE);
                    tvPass.setText("通过(加V认证)");
                    viewHome.setVisibility(View.VISIBLE);
                    orgPassData.communicationtype = Constants.HOME_VISI_TYPE;
                    buttontype();
                    orgPassData.callon = "01";
                }
            }
        });
    }

    private void initData() {
        orgPassData.communicationtype = Constants.PHONE_CATION_TYPE;
        bean = new Gson().fromJson(getIntent().getStringExtra(ORG_BEAN), RobChanceBean.ListBean.class);
        orgPassData.rbiid = String.valueOf(bean.rbiid);
        orgPassData.terminal = TENMINAL_TYPE;
        orgPassData.identificationtype = Constants.IDENTIFICATION_TYPE_REGISTER_ADD_V;
        orgPassData.rbiostatus = Constants.PASS_ORG;
        orgPassData.type = Constants.COMMUNICATION_TYPE_CHANCE;
        if (CommonUtil.identity(bean.cstatus,bean.nowchancetype,bean.chancetype) == ORG_CALIM){
            orgPassData.calid = bean.calid;
            orgPassData.approvetype = 0;
            orgPassData.status = "00";
        }else{
            orgPassData.approvetype = 1;
        }
        mPresenter.lastTime(String.valueOf(bean.rbiid));
    }

    @Override
    public File[] getImgaeFiles() {
        File[] files = new File[imageFiles.size()];
        for (int i = 0; i < imageFiles.size(); i++) {
            files[i] = new File(imageFiles.get(i));
        }
        return files;
    }

    @Override
    public void onSubmitFinish(String msg) {
        EventBus.getDefault().post(new ApproveEvent(RobAddVCertificationActivity.APPROVE_FINISH));
        finish();
    }

    @Override
    public void setLastTime(double lasttime) {
        this.lasttime = lasttime;
        mCountDownHandler = new CountDownHandler(this, lasttime);
        mCountDownHandler.startTimer();

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    protected AddVContract.Presenter initPresenter() {
        return new AddVPresenter(this);
    }

    /**
     * 选择担任职位
     */
    private void showSelectDialog() {
        if (dialogMultiSelect == null) {
            dialogMultiSelect = new DialogMultiSelect(this, datastr, new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tvPosition.setText(datastr[i]);
                    dialogMultiSelect.dismiss();
                    switch (i) {
                        case 0:
                            orgPassData.roletype = IDEN_BOSS;
                            break;
                        case 1:
                            orgPassData.roletype = IDEN_MANAGER;
                            break;
                        case 2:
                            orgPassData.roletype = IDEN_NOMAL;
                            break;
                        default:
                            break;
                    }
                    buttontype();
                }
            });
        }
        dialogMultiSelect.show();
    }


    @OnClick({R.id.tv_phone_mail_the_op, R.id.rl_position, R.id.rl_buttom, R.id.tv_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone_mail_the_op:
                //联系人手机
                ContractUtils.toContract(this, REQ_CONTACT_1);
                break;
            case R.id.rl_position:
                showSelectDialog();
                break;
            case R.id.rl_buttom:
                commit();
                break;
            case R.id.tv_location:
                Intent it = new Intent(this, GpsActivity.class);
                startActivityForResult(it, REQ_GPS);
                break;
            default:
                break;
        }
    }

    //检测提交
    private void commit() {
        String adver = etName.getText().toString().trim();
        String position = tvPosition.getText().toString();
        String location = tvLocation.getText().toString();
        if (TextUtils.equals(tvTime.getText().toString(),"超时")){
            ToastUtil.toastCenter(this,"已超时");
            return;
        }
        if (TextUtils.isEmpty(adver)) {
            ToastUtil.toastCenter(this, "请填写对方名称");
            return;
        }
        if (TextUtils.isEmpty(position)) {
            ToastUtil.toastCenter(this, "请选择担任职位");
            return;
        }
        if (TextUtils.equals(orgPassData.communicationtype, Constants.HOME_VISI_TYPE)) {
            if (TextUtils.isEmpty(location)) {
                ToastUtil.toastCenter(this, "请实地定位");
                return;
            }
            if (imageFiles.size() == 0){
                ToastUtil.toastCenter(this, "请选择实地定位照片");
                return;
            }
        } else if (TextUtils.equals(orgPassData.communicationtype, Constants.PHONE_CATION_TYPE)) {
            if (TextUtils.isEmpty(etTheOpPhone.getText().toString())){
                ToastUtil.toastCenter(this, "请选择联系人手机");
                return;
            }
        } else{
            return;
        }

        orgPassData.msg = etContent.getText().toString();
        orgPassData.description = etMore.getText().toString();

        if (TextUtils.equals(orgPassData.communicationtype, Constants.HOME_VISI_TYPE)) {
            orgPassData.contactsphone = etTheOpPhone.getText().toString().trim();
            orgPassData.contactsname = etName.getText().toString().trim();
            orgPassData.spotgps = tvLocation.getText().toString();
        }else if (TextUtils.equals(orgPassData.communicationtype, Constants.PHONE_CATION_TYPE)){
            if (imageFiles.size() > 0){
                imageFiles.clear();
            }
        }
        if (rbPhone.isChecked()) {
            Intent intent = new Intent(RobAddVCertificationActivity.this,
                    PhoneCertificationActivity.class);
            intent.putExtra(PhoneCertificationActivity.ORG_PASS_DATA,new Gson().toJson(orgPassData));
            startActivity(intent);
        } else {
            mPresenter.submit(orgPassData);
        }
    }
    //判断按钮颜色
    private void buttontype(){
        String adver = etName.getText().toString().trim();
        String position = tvPosition.getText().toString();
        String location = tvLocation.getText().toString();
        if (TextUtils.isEmpty(adver)) {
            tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
            return;
        }
        if (TextUtils.isEmpty(position)) {
            tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
            return;
        }
        if (TextUtils.equals(orgPassData.communicationtype, Constants.HOME_VISI_TYPE)) {
            if (TextUtils.isEmpty(location)) {
                tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
                return;
            }
            if (imageFiles.size() == 0){
                tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
                return;
            }
        } else if (TextUtils.equals(orgPassData.communicationtype, Constants.PHONE_CATION_TYPE)) {
            if (TextUtils.isEmpty(etTheOpPhone.getText().toString())){
                tvPass.setBackgroundResource(R.drawable.bg_c_2_f_104);
                return;
            }
        } else{
            return;
        }
        tvPass.setBackgroundResource(R.drawable.bg_c_2_f_009);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQ_CONTACT_1 && resultCode == RESULT_OK) {
            //得到联系人
            ContractUtils.ContractUser user = ContractUtils.readContract(this, data);
            if (TextUtils.isEmpty(user.phone)){
                ToastUtil.toastCenter(this,"权限未开启");
                return;
            }
            etTheOpPhone.setText(user.phone);
            buttontype();
//            orgPassData.contactsphone = user.phone;
        } else if (requestCode == REQ_GPS && resultCode == RESULT_OK) {
            // 获取经纬度
            double la = data.getDoubleExtra(GpsActivity.RESULT_LATITUDE, 0);
            double lo = data.getDoubleExtra(GpsActivity.RESULT_LONGITUDE, 0);
            String location = data.getStringExtra(GpsActivity.RESULT_LOCATION);
            // 经度在前纬度在后
            tvLocation.setText(lo + "," + la);
            tvLocation.setTag(lo + "," + la);
            buttontype();
//            orgPassData.spotgps = tvLocation.getText().toString();
        } else if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++){
                addItem(Matisse.obtainPathResult(data).get(i),null);
            }
            buttontype();
        }
    }
    /**
     * 增加默认图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(this);
        cgv.addView(imgAddImg);
        cgv.requestLayout();
    }

    @Override
    public void onClick(View v) {
        if (v == imgAddImg) {
            MatissePhotoHelper.selectPhoto(this,
                    MAX_PIC_COUNT - imageFiles.size(), REQUEST_CODE,MimeType.ofImage());
        }
    }
    private void addItem(final String imgPath, String desc) {
        final View itemView = LayoutInflater.from(this)
                .inflate(R.layout.item_img_with_del,
                        cgv, false);
        ImageView img = itemView.findViewById(R.id.img);
        Glide.with(this).load(imgPath).into(img);
        View del = itemView.findViewById(R.id.del);
        final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);
        tvAddDesc.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(desc)) {
            tvAddDesc.setText(desc);
            tvAddDesc.setTag(desc);
        }
        imageFiles.add(imgPath);
        cgv.addView(itemView, 0);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cgv.removeView(itemView);
                imageFiles.remove(imgPath);
                buttontype();
            }
        });
    }


    private class EditOnclick implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            buttontype();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ApproveEvent event) {
        finish();
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mCountDownHandler != null) {
            mCountDownHandler.cancel();
        }
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
    }
    private static class CountDownHandler extends Handler {

        private int mMinute;
        private int mSecond;

        private Timer timer;
        private TimerTask timerTask;

        private WeakReference<RobAddVCertificationActivity> mActivityRef;

        public CountDownHandler(RobAddVCertificationActivity activty, double lasttime) {
            mActivityRef = new WeakReference<RobAddVCertificationActivity>(activty);

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
            RobAddVCertificationActivity activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeFinish();
        }

        /**
         * 回调Activity 倒计时时间变化
         */
        private void callbackActivityTimeTextChange() {
            RobAddVCertificationActivity activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeChanged(getCurrentText());
        }

    }
}
