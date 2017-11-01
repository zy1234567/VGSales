package com.ztstech.vgmate.activitys.org_detail.dialog.org_delete;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.ViewImpl;
import com.ztstech.vgmate.data.beans.GetOrgListItemsBean;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/10/10.
 * 删除组织对话框
 */

public class OrgDeleteDialog extends Dialog implements View.OnClickListener, OrgDeleteContract.View{

    private GetOrgListItemsBean.ListBean bean;
    private Activity activity;

    private View contentView;
    private ImageView imgClose;
    private CheckBox cbRepeat, cbClosed, cbUnStart, cbNotExist;
    private EditText etOther;

    private CheckBox[] cbs;

    private TextView tvSubmit;
    private OrgDeleteContract.Presenter presenter;

    private ViewImpl<OrgDeleteContract.Presenter> mViewImpl;

    /**删除回调*/
    private OnDeleteListener deleteListener;

    public OrgDeleteDialog(@NonNull Context context, GetOrgListItemsBean.ListBean bean) {
        super(context);

        this.activity = (Activity) context;
        this.bean = bean;

        mViewImpl = new ViewImpl<>(context);
        presenter = new OrgDeletePresenter(this);


        ViewUtils.setDialogFullScreen(this);

        this.contentView = getLayoutInflater().inflate(R.layout.dialog_org_del, null, false);

        imgClose = contentView.findViewById(R.id.img_close);
        cbRepeat = contentView.findViewById(R.id.cb_repeat);
        cbClosed = contentView.findViewById(R.id.cb_closed);
        cbUnStart = contentView.findViewById(R.id.cb_un_start);
        cbNotExist = contentView.findViewById(R.id.cb_not_exist);
        etOther = contentView.findViewById(R.id.et_other);
        tvSubmit = contentView.findViewById(R.id.tv_submit);

        cbs = new CheckBox[4];

        cbs[0] = cbRepeat;
        cbs[1] = cbClosed;
        cbs[2] = cbUnStart;
        cbs[3] = cbNotExist;

        imgClose.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        setCancelable(false);
        setContentView(contentView);

    }

    public void setOnDeleteListener(OnDeleteListener listener) {
        this.deleteListener = listener;
    }

    @Override
    public void onClick(View view) {

        if (view == imgClose) {
            dismiss();
        }else if (view == tvSubmit) {
            //提交
            StringBuilder sbMsg = new StringBuilder();
            for (CheckBox cb : cbs) {
                if (cb.isChecked()) {
                    sbMsg.append(cb.getText().toString());
                    sbMsg.append(",");
                }
            }
            String etStr = etOther.getText().toString();
            if (!TextUtils.isEmpty(etStr)) {
                sbMsg.append(etStr);
                sbMsg.append(",");
            }
            if (sbMsg.length() == 0) {
                ToastUtil.toastCenter(getContext(), "请选择或者输入删除原因！");
                return;
            }
            sbMsg.subSequence(0, sbMsg.length() - 1);

            tvSubmit.setEnabled(false);
            imgClose.setClickable(false);
            tvSubmit.setText("提交中");
            presenter.deleteOrg(String.valueOf(bean.rbiid), sbMsg.toString());
        }
    }

    @Override
    public void showLoading(String message) {
        mViewImpl.showLoading(message);
    }

    @Override
    public void hideLoading(@Nullable String errorMessage) {
        mViewImpl.hideLoading(errorMessage);
    }

    @Override
    public boolean isViewFinish() {
        return !isShowing();
    }

    @Override
    public void onFinish(@Nullable String message) {
        tvSubmit.setEnabled(true);
        imgClose.setClickable(true);
        tvSubmit.setText("确定删除");
        if (message != null) {
            ToastUtil.toastCenter(getContext(), "删除机构失败：" + message);
        }else {
            ToastUtil.toastCenter(getContext(), "删除机构成功！");
            dismiss();
            if (deleteListener != null) {
                deleteListener.onDelete();
            }
        }
    }

    /**
     * 删除app
     */
    public interface OnDeleteListener {

        void onDelete();
    }
}
