package com.ztstech.vgmate.activitys.setting.change_phone;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.setting.send_code.ChangePhoneSendCodeDialog;
import com.ztstech.vgmate.manager.CountDown;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

import java.lang.ref.WeakReference;

/**
 * Created by zhiyuan on 2017/10/28.
 */

public class ChangePhoneDialog extends Dialog implements View.OnClickListener, ChangePhoneContract.View{

    private ImageView imgClose;
    private TextView tvSendCode;
    private TextView tvSubmit;
    private EditText etPhone;
    private EditText etCode;

    private CountDown countDown;

    private boolean submiting = false;
    private ChangePhoneContract.Presenter presenter;
    private OnChangePhoneListener listener;

    private final String OLD_PHONE;

    public ChangePhoneDialog(@NonNull Context context, String oldPhone) {
        super(context);
        OLD_PHONE = oldPhone;

        presenter = new ChangePhonePresenter(this);

        setCancelable(false);
        ViewUtils.setDialogFullScreen(this);

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_change_phone, null, false);
        imgClose = v.findViewById(R.id.img_close);
        tvSendCode = v.findViewById(R.id.tv_send_code);
        tvSubmit = v.findViewById(R.id.tv_submit);
        etPhone = v.findViewById(R.id.et_phone);
        etCode = v.findViewById(R.id.et_code);

        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkBtnEnable();
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkBtnEnable();
            }
        });
        tvSendCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        if (countDown == null) {
            countDown = new CountDown(60);
        }
        countDown.setListener(new CountDown.CountdownListener() {
            @Override
            public void updateSeconds(int seconds) {
                if (seconds == 0) {
                    tvSendCode.setEnabled(true);
                }else {
                    tvSendCode.setText(String.valueOf(seconds));
                }
            }
        });

        imgClose.setOnClickListener(this);
        setContentView(v);

    }

    public void setListener(OnChangePhoneListener listener) {
        this.listener = listener;
    }

    /**
     * 检查按钮是否可以点击
     */
    private void checkBtnEnable() {
        int phoneLength = etPhone.getText().length();
        int codeLength = etCode.getText().length();

        if (submiting) {
            //如果正在倒计时或者正在提交
            tvSubmit.setEnabled(false);
            tvSendCode.setEnabled(false);
        }else if (countDown.isCounting()) {
            tvSendCode.setEnabled(false);
            tvSubmit.setEnabled(phoneLength == 11 && codeLength == 6);
        }else {
            tvSendCode.setEnabled(phoneLength == 11);
            tvSubmit.setEnabled(phoneLength == 11 && codeLength == 6);
        }

    }

    @Override
    public void onClick(View v) {
        if (v == imgClose) {
            countDown.cancel();
            countDown.setListener(null);
            countDown = null;
            dismiss();
        }else if (v == tvSendCode) {
            if (!countDown.isCounting()) {
                tvSendCode.setEnabled(false);
                countDown.start();
            }
        }else if (v == tvSubmit) {
            tvSubmit.setEnabled(false);
            submiting = true;
            presenter.submit(etCode.getText().toString(), etPhone.getText().toString(), OLD_PHONE);
        }
    }


    @Override
    public void showLoading(String message) {
        tvSubmit.setEnabled(false);
    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {
        tvSubmit.setEnabled(true);

    }

    @Override
    public boolean isViewFinish() {
        return !isShowing();
    }

    @Override
    public void onSubmitFinish(@Nullable String errmsg, String newPhone) {
        submiting = false;
        tvSubmit.setEnabled(true);
        if (errmsg != null) {
            ToastUtil.toastCenter(getContext(), "" + errmsg);
        }else {
            ToastUtil.toastCenter(getContext(), "修改成功");
            if (listener != null) {
                listener.onSucceed(newPhone);
            }
        }
    }

    public interface OnChangePhoneListener {

        void onSucceed(String phone);
    }
}
