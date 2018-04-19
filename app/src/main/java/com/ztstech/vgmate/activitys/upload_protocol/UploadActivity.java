package com.ztstech.vgmate.activitys.upload_protocol;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity.ARG_ORGID;
import static com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity.ARG_RBIONAMW;

/**
 * Created by dongdong on 2018/3/26.
 */

public class UploadActivity extends  MVPActivity<UploadContract.Presenter> implements UploadContract.View {


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.img_nad)
    ImageView imgNad;
    @BindView(R.id.img_foc)
    ImageView imgFoc;
    File f;
    String orgid,rbioname;
    /**
     * 图片文件
     */
    private List<File> imageFiles = new ArrayList<>();
    //保密请求拍照和免费协议
    public static final int REQUEST_CODE_CHOOSE_NAD = 001;
    public static final int REQUEST_CODE_CHOOSE_FOC = 000;
    //判断上传图片具体内容
    String flg;
    private UploadProtocolData uploadProtocolData;
    @Override
    protected UploadContract.Presenter initPresenter() {
        return new UploadPresenter(this);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_upload_privary;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        uploadProtocolData = new Gson().fromJson(getIntent().getStringExtra(OrgDetailV2Activity.ARG_BEAN_PROTOCOL), UploadProtocolData.class);
        orgid = getIntent().getStringExtra(ARG_ORGID);
        rbioname = getIntent().getStringExtra(ARG_RBIONAMW);
        uploadProtocolData.protocolMap.oname = rbioname;
        uploadProtocolData.protocolMap.orgid = orgid;
        initview();
    }
    private void initview(){
        if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.secretagreementpicurl)){
            Glide.with(UploadActivity.this).load(uploadProtocolData.protocolMap.secretagreementpicurl).into(imgNad);
        }
        if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.promisebookpicurl)){
            Glide.with(UploadActivity.this).load(uploadProtocolData.protocolMap.promisebookpicurl).into(imgFoc);
        }
    }
    @OnClick({R.id.img_nad, R.id.img_foc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_nad:
                MatissePhotoHelper.selectPhoto(this, 1, REQUEST_CODE_CHOOSE_NAD, MimeType.ofImage());
                break;
            case R.id.img_foc:
                MatissePhotoHelper.selectPhoto(this, 1, REQUEST_CODE_CHOOSE_FOC, MimeType.ofImage());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_NAD && resultCode == RESULT_OK) {
            String uri = Matisse.obtainPathResult(data).get(0);
            f = new File(uri);
            imageFiles.add(0, f);
            flg = UploadPresenter.UPLOAD_NAD;
            Glide.with(UploadActivity.this).load(f).into(imgNad);
            mPresenter.rtSubmie(uploadProtocolData,flg);
        } else if (requestCode == REQUEST_CODE_CHOOSE_FOC && resultCode == RESULT_OK) {
            String uri = Matisse.obtainPathResult(data).get(0);
            f = new File(uri);
            imageFiles.add(0, f);
            flg = UploadPresenter.UPLOAD_FOC;
            Glide.with(UploadActivity.this).load(f).into(imgFoc);
            mPresenter.rtSubmie(uploadProtocolData,flg);
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
        if (errorMessage == null) {
        }else {
            Toast.makeText(this, "添加失败：" + errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
