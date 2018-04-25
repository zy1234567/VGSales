package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.RobAddVCertificationActivity;
import com.ztstech.vgmate.activitys.upload_protocol.UploadActivity;
import com.ztstech.vgmate.activitys.upload_protocol.UploadPresenter;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.event.ApproveEvent;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/20.
 */

public class PhoneCertificationActivity extends MVPActivity<PhoneCertificationContract.Presenter>
        implements PhoneCertificationContract.View {
    //orgpassdata实体类
    public static final String ORG_PASS_DATA = "orgpassdata_key";
    /**
     * 请求视频认证拍照
     */
    private static final int REQUEST_CODE_VIDO = 29;
    /**
     * 请求拍照定位
     */
    private static final int REQUEST_CODE_LOCATION = 28;
    OrgPassData orgPassData;
    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_wechat_num)
    EditText etWechatNum;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.img_vido)
    ImageView imgVido;
    @BindView(R.id.img_location)
    ImageView imgLocation;
    @BindView(R.id.rl_img)
    RelativeLayout rlImg;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    double lasttime;

    private CountDownHandler mCountDownHandler;
    /**
     * 图片文件
     */
    private List<File> imageFiles = new ArrayList<>();
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_weixin_check;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initData();
    }

    private void initData() {
        orgPassData = new Gson().fromJson(getIntent().getStringExtra(ORG_PASS_DATA), OrgPassData.class);
        mPresenter.lastTime(orgPassData.rbiid);
    }

    @Override
    protected PhoneCertificationContract.Presenter initPresenter() {
        return new PhoneCertificationPresenter(this);
    }


    @OnClick({R.id.img_vido, R.id.img_location, R.id.tv_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_vido:
                MatissePhotoHelper.selectPhoto(this,
                        1, REQUEST_CODE_VIDO, MimeType.ofImage());
                break;
            case R.id.img_location:
                MatissePhotoHelper.selectPhoto(this,
                        1, REQUEST_CODE_LOCATION, MimeType.ofImage());
                break;
            case R.id.tv_pass:
                if (TextUtils.equals(tvTime.getText().toString(),"超时")){
                    ToastUtil.toastCenter(this,"已超时");
                    return;
                }
                if(TextUtils.isEmpty(etWechatNum.getText().toString())){
                    orgPassData.wechatid = etWechatNum.getText().toString();
                    mPresenter.submit(orgPassData);
                }else{
                    mPresenter.submit(orgPassData);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public File[] getImgaeFiles() {
        File[] files = new File[imageFiles.size()];
        for (int i = 0; i < imageFiles.size(); i++) {
            files[i] = imageFiles.get(i);
        }
        return files;
    }

    @Override
    public void onSubmitFinish(String errorMessage) {
        EventBus.getDefault().post(new ApproveEvent(RobAddVCertificationActivity.APPROVE_FINISH));
        finish();
    }

    @Override
    public void setLastTime(double lasttime) {
        this.lasttime = lasttime;
        mCountDownHandler = new PhoneCertificationActivity.CountDownHandler(this, lasttime);
        mCountDownHandler.startTimer();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_VIDO && resultCode == RESULT_OK) {
            String uri = Matisse.obtainPathResult(data).get(0);
            File f = new File(uri);
            imageFiles.clear();
            imageFiles.add(0, f);
            Glide.with(this).load(f).into(imgVido);
            mPresenter.submitimg(orgPassData,1);
        }else if (requestCode == REQUEST_CODE_LOCATION && resultCode == RESULT_OK){
            String uri = Matisse.obtainPathResult(data).get(0);
            File f = new File(uri);
            imageFiles.clear();
            imageFiles.add(0, f);
            Glide.with(this).load(f).into(imgLocation);
            mPresenter.submitimg(orgPassData,0);
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

        private WeakReference<PhoneCertificationActivity> mActivityRef;

        public CountDownHandler(PhoneCertificationActivity activty, double lasttime) {
            mActivityRef = new WeakReference<PhoneCertificationActivity>(activty);

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
            PhoneCertificationActivity activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeFinish();
        }

        /**
         * 回调Activity 倒计时时间变化
         */
        private void callbackActivityTimeTextChange() {
            PhoneCertificationActivity activty = mActivityRef.get();
            if (activty == null || activty.isFinishing()) {
                return;
            }
            activty.onTimeChanged(getCurrentText());
        }

    }
}
