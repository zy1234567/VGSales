package com.ztstech.vgmate.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.matisse.engine.impl.GlideEngine;
import com.ztstech.vgmate.matisse.engine.impl.PicassoEngine;
import com.ztstech.vgmate.matisse.filter.Filter;
import com.ztstech.vgmate.matisse.internal.entity.CaptureStrategy;
import com.ztstech.vgmate.matisse.internal.utils.GifSizeFilter;

import java.util.Set;

import rx.Observer;

/**
 * Created by smm on 2018/3/9.
 */

public class MatissePhotoHelper {

    public static final int REQUEST_CODE_CHOOSE = 23;

    public static void selectPhoto(final Activity activity, final int maxsize, final int requestcode, final Set<MimeType> mimeTypeSet){

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Matisse.from(activity)
                            .choose(mimeTypeSet,false)
                            .countable(true)
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.ztstech.vgmate.fileprovider"))
                            .maxSelectable(maxsize)
                            .addFilter(new GifSizeFilter(8 * Filter.K * Filter.K,4))
                            .gridExpectedSize(
                                    activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.85f)
                            .imageEngine(new GlideEngine())
                            .forResult(requestcode);
                } else {
                    Toast.makeText(activity,"请打开相机相册读取权限", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
    public static void selectfromalbum(final Activity activity, final int maxsize){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    RxPermissions rxPermissions = new RxPermissions(activity);
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(Boolean aBoolean) {
                            if (aBoolean) {
                                Matisse.from(activity)
                                        .choose(MimeType.ofImage())
                                        .theme(R.style.Matisse_Zhihu)
                                        .countable(false)
                                        .maxSelectable(maxsize)
                                        .imageEngine(new PicassoEngine())
                                        .forResult(REQUEST_CODE_CHOOSE);
                            } else {
                                Toast.makeText(activity,"请打开相机相册读取权限", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(activity,"请打开相机相册读取权限", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
