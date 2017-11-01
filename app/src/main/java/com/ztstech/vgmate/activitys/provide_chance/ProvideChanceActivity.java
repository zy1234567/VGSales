package com.ztstech.vgmate.activitys.provide_chance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import com.ztstech.vgmate.activitys.category_info.CategoryTagsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.weigets.CustomGridView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 提供销售机会
 */
public class ProvideChanceActivity extends MVPActivity<ProvideChanceContract.Presenter> implements
        ProvideChanceContract.View, View.OnClickListener, InvokeListener,
        TakePhoto.TakeResultListener{

    /**
     * 请求标签key
     */
    public static final int REQUEST_TAG = 1;

    @BindView(R.id.cgv)
    CustomGridView customGridView;

    @BindView(R.id.tv_tag)
    TextView tvTag;

    private ImageView imgAddImg;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    /**图片文件*/
    private List<File> imageFiles = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_provide_chance;
    }

    @Override
    protected ProvideChanceContract.Presenter initPresenter() {
        return new ProvideChancePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        addDefaultImage();

        tvTag.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_TAG == requestCode) {

        }else {
            takePhoto.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(
                requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }


    /**
     * 增加默认图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(this);
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();
    }

    @Override
    public void onClick(View view) {
        if (view == tvTag) {
            startActivityForResult(new Intent(this, CategoryTagsActivity.class), REQUEST_TAG);
        }else if (view == imgAddImg) {
            showPickImage();
        }
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
            .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        takePhoto.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
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
            final View itemView = LayoutInflater.from(this).inflate(R.layout.item_img_with_del,
                    customGridView, false);
            ImageView img = itemView.findViewById(R.id.img);
            Glide.with(this).load(f).into(img);
            View del = itemView.findViewById(R.id.del);

            imageFiles.add(f);
            customGridView.addView(itemView, 0);

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customGridView.removeView(itemView);
                    imageFiles.remove(f);
                }
            });

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
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(
                TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam;
        }
        return type;
    }
}
