package com.ztstech.vgmate.activitys.create_share.create_share_add_cover;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.data.api.CreateShareApi;
import com.ztstech.vgmate.data.dto.CreateShareData;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.utils.ToastUtil;

import java.io.File;
import java.util.Calendar;

import butterknife.BindView;

/**
 * 添加封面
 */
public class CreateShareAddCoverActivity extends MVPActivity<CreateShareAddCoverContract.Presenter>
        implements CreateShareAddCoverContract.View, View.OnClickListener , InvokeListener,
        TakePhoto.TakeResultListener {

    /**
     * 创建分享bean
     */
    public static final String ARG_CREATE_SHARE_BEAN = "arg_create_share_bean";

    @BindView(R.id.img)
    ImageView imgCover;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_item_title)
    TextView tvTitle;

    /**内容，日期*/
    @BindView(R.id.tv_content)
    TextView tvContent;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    /**
     * 创建分享bean
     */
    private CreateShareData createShareData;

    @Override
    protected int getLayoutRes() {
        if (createShareData.type.equals(CreateShareApi.SHARE_INFO)) {
            return R.layout.activity_create_share_add_cover;
        }else {
            return R.layout.activity_create_share_add_cover_notice;
        }
    }



    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);

        //从intent中获取创建分享数据
        Intent it = getIntent();
        createShareData = new Gson().fromJson(it.getStringExtra(ARG_CREATE_SHARE_BEAN),
                CreateShareData.class);

        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhoto.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected CreateShareAddCoverContract.Presenter initPresenter() {
        return new CreateShareAddCoverPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        imgCover.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        tvTitle.setText(createShareData.title);
        tvContent.setText(getDate());

        if (createShareData.type.equals(CreateShareApi.SHARE_INFO)) {
            //信息
            imgCover.setImageResource(R.mipmap.img_uploadcover);
            if (!TextUtils.isEmpty(createShareData.picurl)) {
                Glide.with(this).load(createShareData.picurl)
                        .placeholder(R.mipmap.img_uploadcover)
                        .into(imgCover);
                tvNext.setEnabled(true);
            }
        }else {
            imgCover.setImageResource(R.mipmap.img_uploadcover1);
            if (!TextUtils.isEmpty(createShareData.picurl)) {
                Glide.with(this).load(createShareData.picurl)
                        .placeholder(R.mipmap.img_uploadcover1)
                        .into(imgCover);
                tvNext.setEnabled(true);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        takePhoto.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        if (imgCover == view) {
            showPickImage();
        }else if (tvNext == view) {
            mPresenter.submit(createShareData);
        }
    }

    /**
     * 显示选取图片
     */
    private void showPickImage() {
        new TakePhotoHelper(this, takePhoto, true).show();
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null) {
            String uri = result.getImage().getOriginalPath();
            final File f = new File(uri);
            createShareData.headFile = f;
            Glide.with(this).load(f).into(imgCover);
            tvNext.setEnabled(true);
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(
                TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void submitFinish(@Nullable String errorMessage) {
        if (errorMessage == null) {
            //成功
            finish();
        }else {
            //失败
            ToastUtil.toastCenter(this, "分享失败：" + errorMessage);
        }
    }

    /**
     * 获取日期
     * @return
     */
    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "最后更新：" + month + "月" + day + "日";
    }
}
