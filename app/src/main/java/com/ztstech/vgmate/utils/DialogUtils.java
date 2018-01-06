package com.ztstech.vgmate.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.MainListBean;

/**
 * Created by smm on 2017/11/7.
 * 弹框管理类
 */

public class DialogUtils {


    /**
     * 删除资讯或公告的弹框
     * @param context
     */
    public static void showEditDialog(Context context, final MainListBean.ListBean bean, final EditInfoCallBack callBack){

        final Dialog dialog = new Dialog(context,R.style.transdialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_delete,null);
        TextView tvDelete = view.findViewById(R.id.tv_delete);
        TextView tvResend = view.findViewById(R.id.tv_resend);
        TextView tvEdit = view.findViewById(R.id.tv_ediit_resend);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClickDelete(bean.nid);
                dialog.dismiss();
            }
        });
        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClickResend(bean);
                dialog.dismiss();
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClickEdit(bean);
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(layoutParams);


    }

    public interface EditInfoCallBack{
        void onClickDelete(String nid);
        void onClickResend(MainListBean.ListBean bean);
        void onClickEdit(MainListBean.ListBean bean);
    }

}
