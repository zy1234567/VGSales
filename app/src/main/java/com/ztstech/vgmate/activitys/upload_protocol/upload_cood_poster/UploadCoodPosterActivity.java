package com.ztstech.vgmate.activitys.upload_protocol.upload_cood_poster;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity;
import com.ztstech.vgmate.activitys.upload_protocol.UploadActivity;
import com.ztstech.vgmate.data.dto.UploadProtocolData;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity.ARG_ORGID;
import static com.ztstech.vgmate.activitys.org_detail_v2.OrgDetailV2Activity.ARG_RBIONAMW;
import static com.ztstech.vgmate.manager.MatissePhotoHelper.REQUEST_CODE_CHOOSE;

/**
 * Created by dongdong on 2018/3/26.
 */

public class UploadCoodPosterActivity extends MVPActivity<UploadCoodPosterContract.Presenter> implements UploadCoodPosterContract.View {
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.img_upload_photo)
    ImageView imgUploadPhoto;

    //上个页面传递的判断显示布局的标志位
    private String layoutflg;
    //页面的现实情况
    public static final String UPLOAD_KEY = "upload";
    public static final String UPLOAD_COOP_VALUSE = "upload_coop";
    public static final String UPLOAD_POSTER_VALUSE = "upload_poster";
    String flg;
    /**
     * 图片文件
     */
    private List<File> imageFiles = new ArrayList<>();

    private UploadProtocolData uploadProtocolData;
    String orgid,rbioname;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_upload;
    }
    @Override
    protected UploadCoodPosterContract.Presenter initPresenter() {
        return new UploadCoodPosterPresenter(this);
    }
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        layoutflg = getIntent().getStringExtra(UPLOAD_KEY);
        orgid = getIntent().getStringExtra(ARG_ORGID);
        rbioname = getIntent().getStringExtra(ARG_RBIONAMW);
        uploadProtocolData = new Gson().fromJson(getIntent().getStringExtra(OrgDetailV2Activity.ARG_BEAN_PROTOCOL), UploadProtocolData.class);
            uploadProtocolData.protocolMap.oname = rbioname;
            uploadProtocolData.protocolMap.orgid = orgid;
        initview();
    }
    private void initview(){
        if (TextUtils.equals(UPLOAD_POSTER_VALUSE,layoutflg)){
            tvHint.setText("如合作海报在机构张贴成功，请上传实际效果图");
            if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.posterpicurl)) {
                Glide.with(UploadCoodPosterActivity.this).load(uploadProtocolData.protocolMap.posterpicurl).into(imgUploadPhoto);
            }
        }else {
            if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.teamworkprotocalpicurl)) {
                Glide.with(UploadCoodPosterActivity.this).load(uploadProtocolData.protocolMap.teamworkprotocalpicurl).into(imgUploadPhoto);
            }
        }

    }

    @OnClick(R.id.img_upload_photo)
    public void onClick() {
        MatissePhotoHelper.selectPhoto(this, 1, REQUEST_CODE_CHOOSE, MimeType.ofImage());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            String uri = Matisse.obtainPathResult(data).get(0);
            final File f = new File(uri);
            imageFiles.add(0, f);
            Glide.with(UploadCoodPosterActivity.this).load(f).into(imgUploadPhoto);
            if (TextUtils.equals(UPLOAD_POSTER_VALUSE,layoutflg)){
                flg = UploadCoodPosterPresenter.UPLOAD_POSTER;
                mPresenter.rtSubmie(uploadProtocolData,flg);
            }else{
                flg = UploadCoodPosterPresenter.UPLOAD_TEAM;
                mPresenter.rtSubmie(uploadProtocolData,flg);
            }
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
