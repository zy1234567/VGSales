package com.ztstech.vgmate.activitys.org_detail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.ViewImpl;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/21.
 */

public class OrgAuditDialog extends Dialog implements OrgAuditDialogContract.View, View.OnClickListener{


    public static final int MODE_PASS = 0;
    public static final int MODE_REPEAT = 1;
    public static final int MODE_INVALID = 2;

    private TextView tvPass, tvRepeat, tvInvalid;
    private TextView tvPassCount, tvInvalidCount;
    private ImageView imgClose;

    private EditText etPass, etInvalid;
    private RelativeLayout rlPass, rlRepeat, rlInvalid;

    private TextView tvSubmit;


    private ViewImpl<OrgAuditDialogContract.Presenter> viewImpl;
    private OrgAuditDialogContract.Presenter presenter;



    public OrgAuditDialog(@NonNull Context context) {
        super(context);

        presenter = new OrgAuditDialogPresenter(this);
        viewImpl = new ViewImpl<>(context);
        setCancelable(false);


        ViewUtils.setDialogFullScreen(this);

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_audit_org,
                null, false);

        imgClose = (ImageView) v.findViewById(R.id.img_close);

        tvPass = (TextView) v.findViewById(R.id.tv_pass);
        tvRepeat = (TextView) v.findViewById(R.id.tv_repeat);
        tvInvalid = (TextView) v.findViewById(R.id.tv_invalid);

        rlPass = (RelativeLayout) v.findViewById(R.id.rl_pass);
        rlRepeat = (RelativeLayout) v.findViewById(R.id.rl_repeat);
        rlInvalid = (RelativeLayout) v.findViewById(R.id.rl_invalid);

        tvPassCount = (TextView) v.findViewById(R.id.tv_pass_count);
        tvInvalidCount = (TextView) v.findViewById(R.id.tv_invalid_count);

        etPass = (EditText) v.findViewById(R.id.et_pass);
        etInvalid = (EditText) v.findViewById(R.id.et_invalid);

        tvSubmit = (TextView) v.findViewById(R.id.tv_submit);


        imgClose.setOnClickListener(this);
        tvPass.setOnClickListener(this);
        tvRepeat.setOnClickListener(this);
        tvInvalid.setOnClickListener(this);

        setContentView(v);

        setMode(MODE_PASS);


    }


    @Override
    public void onClick(View view) {
        if (view == imgClose) {
            if (isShowing()) {
                dismiss();
            }
        }else if (view == tvPass) {
            setMode(MODE_PASS);
        }else if (view == tvInvalid) {
            setMode(MODE_INVALID);
        }else if (view == tvRepeat) {
            setMode(MODE_REPEAT);
        }
    }

    private void setMode(int mode) {
        if (MODE_PASS == mode) {
            tvPass.setSelected(true);
            tvInvalid.setSelected(false);
            tvRepeat.setSelected(false);

            rlPass.setVisibility(View.VISIBLE);
            rlInvalid.setVisibility(View.GONE);
            rlRepeat.setVisibility(View.GONE);
        }else if (MODE_INVALID == mode) {
            tvPass.setSelected(false);
            tvInvalid.setSelected(true);
            tvRepeat.setSelected(false);

            rlPass.setVisibility(View.GONE);
            rlInvalid.setVisibility(View.VISIBLE);
            rlRepeat.setVisibility(View.GONE);
        }else if (MODE_REPEAT == mode) {
            tvPass.setSelected(false);
            tvInvalid.setSelected(false);
            tvRepeat.setSelected(true);

            rlPass.setVisibility(View.GONE);
            rlInvalid.setVisibility(View.GONE);
            rlRepeat.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void showLoading(String message) {
        viewImpl.showLoading(message);
    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {
        viewImpl.hideLoading(errorMessage);
    }

    @Override
    public boolean isViewFinish() {
        return !isShowing();
    }
}
