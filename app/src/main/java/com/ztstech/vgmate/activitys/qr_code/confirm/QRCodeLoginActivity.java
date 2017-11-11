package com.ztstech.vgmate.activitys.qr_code.confirm;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.utils.ToastUtil;

import butterknife.BindView;

/**
 * 二维码确认登录
 */
public class QRCodeLoginActivity extends MVPActivity<QRCodeLoginContract.Presenter> implements
        QRCodeLoginContract.View {

    /**
     * 传入解析二维码结果
     */
    public static final String ARG_UUID = "arg_uuid";

    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    @BindView(R.id.img_close)
    ImageView imgClose;

    private String uuid;



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_qrcode;
    }

    @Override
    public void loginFinish(@Nullable String errmsg) {
        if (errmsg == null) {
            //登录成功
            ToastUtil.toastCenter(this, "登录成功");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setResult(RESULT_OK);
                    finish();
                }
            }, 1000);
        }else {
            //登录失败
            // TODO: 2017/11/2 登录超时
            ToastUtil.toastCenter(this, "登录失败：" + errmsg);
        }
    }

    @Override
    protected QRCodeLoginContract.Presenter initPresenter() {
        return new QRCodeLoginPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        uuid = getIntent().getStringExtra(ARG_UUID);
        if (TextUtils.isEmpty(uuid)) {
            throw new IllegalArgumentException("没有传入uuid");
        }
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(uuid);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
