package com.ztstech.vgmate.activitys.add_certification.PhoneCertification_next;

import android.content.Intent;
import android.os.Bundle;
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
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    protected PhoneCertificationContract.Presenter initPresenter() {
        return new PhoneCertificationPresenter(this);
    }


    @OnClick({R.id.img_vido, R.id.img_location, R.id.ll_buttom})
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
            case R.id.ll_buttom:
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
}
