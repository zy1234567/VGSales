package com.ztstech.vgmate.activitys.qr_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;

import butterknife.BindView;

/**
 * 二维码扫描界面
 */
public class QRCodeActivity extends AppCompatActivity {

    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    @BindView(R.id.img_close)
    ImageView imgClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }
}
