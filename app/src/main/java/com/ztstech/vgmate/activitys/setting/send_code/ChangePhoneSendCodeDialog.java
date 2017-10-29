package com.ztstech.vgmate.activitys.setting.send_code;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.ViewImpl;
import com.ztstech.vgmate.manager.CountDown;
import com.ztstech.vgmate.utils.KeyboardUtils;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

import java.lang.ref.WeakReference;

/**
 * Created by zhiyuan on 2017/10/28.
 *
 */

public class ChangePhoneSendCodeDialog extends Dialog implements ChangePhoneSendCodeContract.View {

    public static final int MAX_TEXT = 6;

    /**
     * 发送验证码
     */
    private TextView tvSendCode;
    private View[] lines;
    private TextView[] tvs;
    private KProgressHUD hud;

    private SpannableStringBuilder textResend;
    private SpannableStringBuilder textErrorResend;

    private ViewImpl<ChangePhoneSendCodeContract.Presenter> viewImpl;
    private ChangePhoneSendCodePresenter presenter;

    private ChangePhoneCallback callback;

    private static CountDown countDown;
    private Activity activity;
    private String phone;

    public ChangePhoneSendCodeDialog(@NonNull final Context context, final String phone) {
        super(context);
        this.phone = phone;
        activity = (Activity) context;
        hud = new KProgressHUD(context);

        presenter = new ChangePhoneSendCodePresenter(this);
        viewImpl = new ViewImpl<>(context);

        setCancelable(false);
        ViewUtils.setDialogFullScreen(this);

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_change_phone_send_code, null,
                false);




        TextView tvPhone = v.findViewById(R.id.tv_phone);
        final EditText etCode = v.findViewById(R.id.et);
        TextView tvTitle = v.findViewById(R.id.tv_title);
        TextView tvCode1 = v.findViewById(R.id.tv_code_1);
        TextView tvCode2 = v.findViewById(R.id.tv_code_2);
        TextView tvCode3 = v.findViewById(R.id.tv_code_3);
        TextView tvCode4 = v.findViewById(R.id.tv_code_4);
        TextView tvCode5 = v.findViewById(R.id.tv_code_5);
        TextView tvCode6 = v.findViewById(R.id.tv_code_6);

        View tvLine1 = v.findViewById(R.id.tv_code_line_1);
        View tvLine2 = v.findViewById(R.id.tv_code_line_2);
        View tvLine3 = v.findViewById(R.id.tv_code_line_3);
        View tvLine4 = v.findViewById(R.id.tv_code_line_4);
        View tvLine5 = v.findViewById(R.id.tv_code_line_5);
        View tvLine6 = v.findViewById(R.id.tv_code_line_6);

        tvSendCode = v.findViewById(R.id.tv_bottom);
        tvSendCode.setClickable(true);
        tvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!countDown.isCounting()) {
                    presenter.sendCode(phone);
                }
            }
        });

        View keyCover = v.findViewById(R.id.tv_key_cover);


        v.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tvs = new TextView[] {tvCode1, tvCode2, tvCode3, tvCode4, tvCode5, tvCode6};
        lines = new View[] {tvLine1, tvLine2, tvLine3, tvLine4, tvLine5, tvLine6};

        //设置标题不同颜色风格字体
        String[] txts = new String[] {"我们已经发送 ", "验证码", " 到您的手机"};
        SpannableStringBuilder ssb = ViewUtils.getDiffColorSpan(null,
                txts,
                new int[] {ContextCompat.getColor(context, R.color.color_101),
                        ContextCompat.getColor(context, R.color.color_100),
                        ContextCompat.getColor(context, R.color.color_101)});
        ssb = ViewUtils.getDiffStyleSpan(ssb, txts,
                new int[] {Typeface.NORMAL,
                        Typeface.BOLD,
                        Typeface.NORMAL});
        tvTitle.setText(ssb);
        tvPhone.setText(phone);

        etCode.addTextChangedListener(new TextWatcher() {

            private String lastText = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < lastText.length()) {
                    //如果减少文本
                    removeOne();
                }else {
                    //增加文本
                    if (!s.toString().isEmpty() && s.length() - 1 >= 0) {
                        //如果文本不为空，截取最后一个
                        String last = s.toString().substring(s.length() - 1, s.length());
                        addOne(last);
                    }
                }

                lastText = s.toString();

            }
        });


        etCode.setFocusableInTouchMode(true);
        etCode.setFocusable(true);


        keyCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.showKeyBoard(context, etCode);
            }
        });



        String[] reSend = new String[] {"收不到验证码？", "重发短信"};
        String[] errorReSend = new String[] {"发送验证码失败！", "重发短信"};
        textResend = ViewUtils.getDiffColorSpan(null, reSend,
                new int[] {ContextCompat.getColor(context, R.color.color_102),
                        ContextCompat.getColor(context, R.color.color_001)});
        textResend = ViewUtils.getDiffClickSpan(textResend, reSend, new ClickableSpan[] {
                null,
                new ViewUtils.NoLineClickSpan() {

                    @Override
                    public void onClick(View widget) {
                        if (!countDown.isCounting()) {
                            //如果不在计时，发送验证码
                            presenter.sendCode(phone);
                        }
                    }
                }
        });

        textErrorResend = ViewUtils.getDiffColorSpan(null, errorReSend,
                new int[] {ContextCompat.getColor(context, R.color.color_102),
                        ContextCompat.getColor(context, R.color.color_001)});
        textErrorResend = ViewUtils.getDiffClickSpan(textErrorResend, errorReSend, new ClickableSpan[] {
                null,
                new ViewUtils.NoLineClickSpan() {

                    @Override
                    public void onClick(View widget) {
                        if (!countDown.isCounting()) {
                            //如果不在计时，发送验证码
                            presenter.sendCode(phone);
                        }
                    }
                }
        });


        updateLineColor();
        setContentView(v);


        //设置计时
        if (countDown == null) {
            countDown = new CountDown(60);
        }
        countDown.setListener(new CountDown.CountdownListener() {
            @Override
            public void updateSeconds(int seconds) {
                updateSecond(seconds);
            }
        });


    }


    @Override
    public void show() {
        super.show();
        if (countDown.isCounting()) {
            //如果正在计时
            updateSecond(countDown.getSeconds());
        }else {
            presenter.sendCode(phone);
        }
    }

    public void setCallback(ChangePhoneCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onBackPressed() {
        if (isShowing()) {
            dismiss();
        }
    }

    private void addOne(String num) {
        boolean addSucceed = false;
        for (int i = 0; i < tvs.length; i++) {
            if (tvs[i].getText().length() == 0) {
                //如果当前文本为空
                tvs[i].setText(num);
                addSucceed = true;
                break;
            }
        }
        if (addSucceed) {
            updateLineColor();
            if (checkInputFinish()) {
                checkCode();
            }
        }

    }

    private void removeOne() {
        if (tvs[0].getText().length() == 0) {
            return;
        }
        for (int i = tvs.length - 1; i >= 0; i--) {
            //倒序查找
            if (tvs[i].getText().length() != 0) {
                //如果当前文本不为空
                tvs[i].setText("");
                break;
            }
        }
        updateLineColor();

    }

    private void updateLineColor() {
        int indexOfEmpty = lines.length + 1;
        for (int i = 0; i < tvs.length; i++) {
            if (tvs[i].getText().length() == 0) {
                indexOfEmpty = i;
                break;
            }
        }
        for (int i = 0; i < lines.length; i++) {
            if (i == indexOfEmpty) {
                lines[i].setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.color_001));
            }else {
                lines[i].setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.color_100));
            }
        }
    }

    public void updateSecond(int seconds) {
        if (seconds == 0) {
            tvSendCode.setText(textResend);
        }else {
            tvSendCode.setText("" + seconds + "秒后重发");
        }
    }

    @Override
    public void showLoading(String message) {
        hud.show();
    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {
        hud.dismiss();
        if (errorMessage != null) {
            //网络访问错误
            tvSendCode.setText(textErrorResend);
        }
    }

    @Override
    public boolean isViewFinish() {
        return !isShowing() || activity == null || activity.isFinishing();
    }

    @Override
    public void onSendCodeFinish(@Nullable String errmsg) {
        hud.dismiss();
        if (errmsg == null) {
            countDown.start();
        }else {
            ToastUtil.toastCenter(activity, "发送验证码出错：" + errmsg);
        }
    }

    @Override
    public void onCheckCodeFinish(@Nullable String errmsg, String code) {
        hud.dismiss();
        if (errmsg == null) {
            if (callback != null) {
                callback.onCheckSucceed(code);
            }
            dismiss();
        }else {
            ToastUtil.toastCenter(activity, "" + errmsg);
        }
    }

    private boolean checkInputFinish() {
        boolean finish = true;
        for (TextView textView : tvs) {
            if (TextUtils.isEmpty(textView.getText())) {
                finish = false;
            }
        }
        return finish;
    }


    private void checkCode() {
        hud.show();
        String code = "";
        for (TextView tv: tvs) {
            code = code.concat(tv.getText().toString());
        }
        presenter.checkCode(phone, code);
    }




    public interface ChangePhoneCallback {

        void onCheckSucceed(String code);
    }
}
