package com.ztstech.vgmate.activitys.qr_code.scan;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jph.takephoto.model.TResult;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.qr_code.confirm.QRCodeLoginActivity;
import com.ztstech.vgmate.utils.TakePhotoHelperWapper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import rx.functions.Action1;


/**
 * 扫描二维码
 */
public class QRCodeScanActivity extends MVPActivity<QRCodeScanContract.Presenter>
        implements QRCodeScanContract.View, View.OnClickListener {


    public static final int REQ_LOGIN = 1;

    @BindView(R.id.tv_open_light)
    TextView tvOpenLight;

    @BindView(R.id.zxingview)
    ZXingView zXingView;

    @BindView(R.id.top_bar)
    TopBar topBar;


    private boolean enableCamera;

    /**
     * 拍照包装类
     */
    private TakePhotoHelperWapper takePhotoHelperWapper;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_qrcode_scan;
    }

    @Override
    protected QRCodeScanContract.Presenter initPresenter() {
        return new QRCodeScanPresenter(this);
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        tvOpenLight.setOnClickListener(this);
        topBar.getRightTextView().setOnClickListener(this);

        takePhotoHelperWapper = new TakePhotoHelperWapper(this) {
            @Override
            public void takeSuccess(TResult result) {
                String uri = result.getImage().getOriginalPath();
                if (TextUtils.isEmpty(uri)) {
                    ToastUtil.toastCenter(QRCodeScanActivity.this, "获取图片失败");
                }else {
                    new DecodeQrCodeTask().execute(uri);
                }
            }
        };
        takePhotoHelperWapper.onCreate(savedInstanceState);


        //请求权限
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        enableCamera = aBoolean;
                        if (aBoolean) {
                            zXingView.startCamera();
                            zXingView.startSpotAndShowRect();
                        }else {
                            Toast.makeText(QRCodeScanActivity.this,
                                    "无权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        zXingView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                if (TextUtils.isEmpty(result)) {
                    ToastUtil.toastCenter(QRCodeScanActivity.this, "解析失败");
                    startScan();
                }else {
                    checkUUID(result);
                }
            }

            @Override
            public void onScanQRCodeOpenCameraError() {
                ToastUtil.toastCenter(QRCodeScanActivity.this, "打开相机失败");
            }
        });

        // TODO: 2017/11/2 没有打开手电筒图片，暂时随意找了个图片
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (enableCamera) {
            startScan();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopScan();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        takePhotoHelperWapper.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhotoHelperWapper.onActivityResult(requestCode, resultCode, data);
        if (REQ_LOGIN == requestCode && resultCode == RESULT_OK) {
            //web登录成功
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == topBar.getRightTextView()) {
            //相册
            takePhotoHelperWapper.pickFromGallery();
        }else if (v == tvOpenLight) {
            //开灯
            if ("开灯".equals(tvOpenLight.getText().toString())) {
                zXingView.openFlashlight();
                tvOpenLight.setText("关灯");
            }else {
                zXingView.closeFlashlight();
                tvOpenLight.setText("开灯");
            }

        }
    }

    private void checkUUID(String uuid) {
        mPresenter.checkUUID(uuid);
    }

    @Override
    public void checkUUIDFinish(String uuid, @Nullable String errmsg) {
        // 返回了个这么个玩意，成功同时失败？？？？
        // {"status":0,"loginUrl":"/code/phoneLogin?uuid=","errmsg":"出错java.lang.NullPointerException"}
        if (errmsg == null) {
            Intent it = new Intent(QRCodeScanActivity.this, QRCodeLoginActivity.class);
            it.putExtra(QRCodeLoginActivity.ARG_UUID, uuid);
            startActivityForResult(it, REQ_LOGIN);
        }else {
            ToastUtil.toastCenter(this, "" + errmsg);
            startScan();
        }
    }

    private void startScan() {
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
    }

    private void stopScan() {
        zXingView.stopCamera();
        zXingView.stopSpot();
    }


    private class DecodeQrCodeTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoading("正在解析");
        }

        @Override
        protected String doInBackground(String... params) {
            String filePath = params[0];
            return QRCodeDecoder.syncDecodeQRCode(filePath);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            hideLoading(null);

            if (TextUtils.isEmpty(s)) {
                ToastUtil.toastCenter(QRCodeScanActivity.this, "解析失败");
                startScan();
            }else {
                checkUUID(s);
            }

        }
    }
}
