package com.ztstech.vgmate.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.CropOptions;
import com.ztstech.vgmate.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by smm on 2017/5/31.
 * 拍照选择帮助类
 */

public class TakePhotoHelper {

    private Activity activity;
    private Dialog dialog;
    private TakePhoto takePhoto;

    /**
     * 用于多图片标记，调用showForOthers（）传入，在takeSuccess方法中可据此区别操作
     */
    private int imgTag = -1;

    /**
     * 存储临时图片的uri
     */
    private Uri imageUri;

    /**
     * 是否需要裁剪
     */
    private boolean iscrop;

    /**
     * 自定义裁剪配置信息
     */
    private CropOptions mCropOptions;

    /**宽高比*/
    private int widthPercent = 1;
    private int heightPercent = 1;

    public TakePhotoHelper(Activity activity, TakePhoto takePhoto, boolean iscrop) {
        this(activity, takePhoto, iscrop, -1, -1);
    }

    public TakePhotoHelper(Activity activity, TakePhoto takePhoto, boolean iscrop, int widthPercent,
                           int heightPercent) {
        this.widthPercent = widthPercent;
        this.heightPercent = heightPercent;
        this.activity = activity;
        this.takePhoto = takePhoto;
        this.iscrop = iscrop;
        File file = new File(activity.getCacheDir(), "/ztstechDown/" +
                System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);
    }

    public TakePhotoHelper(Activity activity, TakePhoto takePhoto, boolean iscrop, CropOptions mCropOptions) {
        this.activity = activity;
        this.takePhoto = takePhoto;
        this.iscrop = iscrop;
        this.mCropOptions = mCropOptions;
        File file = new File(activity.getCacheDir(), "/ztstechDown/" +
                System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);
    }

    public void show() {
        AlertDialog.Builder buider = new AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.mine_collection_dialog, null, false);
        LinearLayout xiangji = (LinearLayout) view
                .findViewById(R.id.layout_fengxiang);
        LinearLayout xiangce = (LinearLayout) view
                .findViewById(R.id.layout_delete);
        TextView tv1 = (TextView) view.findViewById(R.id.collection_fenxing);
        TextView tv2 = (TextView) view.findViewById(R.id.collection_delete);
        buider.setView(view);
        dialog = buider.create();
        ViewUtils.setDialogFullScreen(dialog);
        dialog.setCanceledOnTouchOutside(true);
        xiangji.setOnClickListener(new MyClickListener());
        xiangce.setOnClickListener(new MyClickListener());
        tv1.setText("相机");
        tv2.setText("相册");
        dialog.show();
    }

    /**
     * 显示照片选择dialog
     *
     * @param imgTag 多张图片位置标记
     */
    public void showForOthers(int imgTag) {
        this.imgTag = imgTag;
        show();
    }

    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            /*身份证裁剪*/
/*            if (imgTag == 11 || imgTag == 12) {
                mCropOptions = getCropOptions(56, 35, 500, 500);
                if (view.getId() == R.id.layout_fengxiang) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, mCropOptions);
                } else {
                    takePhoto.onPickFromGalleryWithCrop(imageUri, mCropOptions);
                }
                dialog.dismiss();
                mCropOptions = null;
                return;
            }*/

            //设置默认裁剪配置
            if (mCropOptions == null) {
                mCropOptions = getCropOptions(widthPercent, heightPercent, 500, 500);
            }

            /*其他图片*/
            if (view.getId() == R.id.layout_fengxiang) {  //去照相
                if (iscrop) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, mCropOptions);
                } else {
                    takePhoto.onPickFromCapture(imageUri);
                }
                dialog.dismiss();
            } else if (view.getId() == R.id.layout_delete) {  //去相册
                if (iscrop) {
                    takePhoto.onPickFromGalleryWithCrop(imageUri, mCropOptions);
                } else {
                    takePhoto.onPickFromGallery();
                }
                dialog.dismiss();
            }
        }
    }

    /**
     * 去相册选取
     */
    public void selectFromAlbum(){
        //设置默认裁剪配置
        if (mCropOptions == null) {
            mCropOptions = getCropOptions(widthPercent, heightPercent, 500, 500);
        }
        if (iscrop) {
            takePhoto.onPickFromGalleryWithCrop(imageUri, mCropOptions);
        } else {
            takePhoto.onPickFromGallery();
        }
    }

    /**
     * 图片路径转化为Bitmap对象
     *
     * @param path
     * @return
     */
    public Bitmap fileToBitmap(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(activity, "您可能未授予app文件读取权限!", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /**
     * 创建自定义裁剪配置 cropOptions
     */
    public CropOptions getCropOptions(int aspectX, int aspectY, int outPutX, int outPutY) {
        return new CropOptions.Builder().setWithOwnCrop(true).setAspectX(aspectX)
                .setAspectY(aspectY).setOutputX(outPutX).setOutputY(outPutY).create();
    }

    /**
     * 裁剪配置set方法
     *
     * @return
     */
    public void setCropOptions(int aspectX, int aspectY, int outPutX, int outPutY) {
        if (mCropOptions == null) {
            mCropOptions = getCropOptions(1, 1, 500, 500);
        }
        mCropOptions.setAspectX(aspectX);
        mCropOptions.setAspectY(aspectY);
        mCropOptions.setOutputX(outPutX);
        mCropOptions.setOutputY(outPutY);
    }

    public int getImgTag() {
        return imgTag;
    }
}
