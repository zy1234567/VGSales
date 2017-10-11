package com.ztstech.vgmate.activitys.org_detail.dialog.org_delete;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/10/10.
 */

public class OrgDeleteDialog extends Dialog implements View.OnClickListener{

    private GetOrgListItemsBean.ListBean bean;
    private Activity activity;

    private View contentView;
    private ImageView imgClose;
    private CheckBox cbRepeat, cbClosed, cbUnStart, cbNotExist;
    private EditText etOther;
    private TextView tvSubmit;

    public OrgDeleteDialog(@NonNull Context context, GetOrgListItemsBean.ListBean bean) {
        super(context);

        this.activity = (Activity) context;
        this.bean = bean;

        ViewUtils.setDialogFullScreen(this);

        this.contentView = getLayoutInflater().inflate(R.layout.dialog_org_del, null, false);

        imgClose = contentView.findViewById(R.id.img_close);
        cbRepeat = contentView.findViewById(R.id.cb_repeat);
        cbClosed = contentView.findViewById(R.id.cb_closed);
        cbUnStart = contentView.findViewById(R.id.cb_un_start);
        cbNotExist = contentView.findViewById(R.id.cb_not_exist);
        etOther = contentView.findViewById(R.id.et_other);
        tvSubmit = contentView.findViewById(R.id.tv_submit);

        imgClose.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        setCancelable(false);
        setContentView(contentView);

    }

    @Override
    public void onClick(View view) {
        if (view == imgClose) {
            dismiss();
        }
    }
}
