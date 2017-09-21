package com.ztstech.vgmate.activitys.org_detail.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.utils.ViewUtils;

import butterknife.BindView;

/**
 * Created by zhiyuan on 2017/9/21.
 */

public class OrgAuditDialog extends Dialog {

    private TextView tvPass, tvRepeat, tvInvalid;
    private TextView tvPassCount, tvInvalidCount;

    private EditText etPass, etInvalid;

    private TextView tvSubmit;


    public OrgAuditDialog(@NonNull Context context) {
        super(context);

        setCancelable(false);

        Point point = new Point();
        getWindow().getWindowManager().getDefaultDisplay().getSize(point);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams mWindowAttributes = getWindow().getAttributes();
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        mWindowAttributes.width = point.x;
        mWindowAttributes.height = WindowManager.LayoutParams.MATCH_PARENT;

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_audit_org,
                null, false);

        tvPass = (TextView) v.findViewById(R.id.tv_pass);
        tvRepeat = (TextView) v.findViewById(R.id.tv_repeat);
        tvInvalid = (TextView) v.findViewById(R.id.tv_invalid);

        tvPassCount = (TextView) v.findViewById(R.id.tv_pass_count);
        tvInvalidCount = (TextView) v.findViewById(R.id.tv_invalid_count);

        etPass = (EditText) v.findViewById(R.id.et_pass);
        etInvalid = (EditText) v.findViewById(R.id.et_invalid);

        tvSubmit = (TextView) v.findViewById(R.id.tv_submit);

        tvPass.setSelected(true);

        setContentView(v);

    }





}
