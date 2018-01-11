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
import android.widget.LinearLayout;
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
    /**
     * 跟进方式dialog
     */
    public static void showFllowUpDialog(Context context, final showFllowUpCallBack callback){
        final Dialog dialog = new Dialog(context,R.style.transdialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_fllow_up,null);
        LinearLayout llAccelerateFllowUp = view.findViewById(R.id.ll_accelerate_fllow_up);
        LinearLayout llNormalFllowUp = view.findViewById(R.id.ll_normal_fllow_up);
        LinearLayout llContinuedFllowUp = view.findViewById(R.id.ll_continued_fllow_up);
        LinearLayout llNoFllowUp = view.findViewById(R.id.ll_no_fllow_up);
        TextView tvBack = view.findViewById(R.id.tv_back);
        llAccelerateFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(0);
            }
        });
        llNormalFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(1);
            }
        });
        llContinuedFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(2);
            }
        });
        llNoFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(3);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public interface showFllowUpCallBack{
        void onFllowUpClick(int position);
    }
    public static void showCommitDilog(Context context, String title, String contenttop, String contentbottom, String lefttv, String righttv, final showCommitCallBack callBack){
        final Dialog dialog = new Dialog(context,R.style.transdialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_confirm_commit,null);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvContentTop = view.findViewById(R.id.tv_content_top);
        TextView tvContentBottom = view.findViewById(R.id.tv_content_bottom);
        TextView tvBack = view.findViewById(R.id.tv_back);
        TextView tvCommit = view.findViewById(R.id.tv_commit);
        tvTitle.setText(title);
        tvContentTop.setText(contenttop);
        tvContentBottom.setText(contentbottom);
        tvBack.setText(lefttv);
        tvCommit.setText(righttv);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onBackClick();
                dialog.dismiss();
            }
        });
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onCommitClick();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.width= ViewUtils.dp2px(context,300);
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(layoutParams);

    }
    public interface showCommitCallBack{
        void onBackClick();
        void onCommitClick();
    }
}
