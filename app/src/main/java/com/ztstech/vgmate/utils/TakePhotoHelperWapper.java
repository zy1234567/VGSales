package com.ztstech.vgmate.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

/**
 * Created by zhiyuan on 2017/10/18.
 * 选取图片包装类
 */

public abstract class TakePhotoHelperWapper implements InvokeListener,
        TakePhoto.TakeResultListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private Activity activity;

    public TakePhotoHelperWapper(Activity activity) {
        this.activity = activity;
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(activity,this));
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        takePhoto.onCreate(savedInstanceState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        takePhoto.onActivityResult(requestCode, resultCode, data);
    }

    public void onSaveInstanceState(Bundle outState) {
        takePhoto.onSaveInstanceState(outState);
    }

    /**
     * 显示选取图片
     */
    public void showPickDialog() {
        new TakePhotoHelper(activity, takePhoto, true).showPickDialog();
    }

    /**
     * 从相册选择图片
     */
    public void pickFromGallery() {
        new TakePhotoHelper(activity, takePhoto, true).pickFromGallery();
    }

    @Override
    public void takeFail(TResult result, String msg) {}

    @Override
    public void takeCancel() {}


    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(
                TContextWrap.of(this.activity), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }
}
