package com.ztstech.vgmate.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztstech.appdomain.constants.Constants;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.data.beans.MainListBean;
import com.ztstech.vgmate.data.dto.UploadProtocolData;

import static java.security.AccessController.getContext;

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
                dialog.dismiss();
            }
        });
        llNormalFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(1);
                dialog.dismiss();
            }
        });
        llContinuedFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(2);
                dialog.dismiss();
            }
        });
        llNoFllowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onFllowUpClick(3);
                dialog.dismiss();
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
        layoutParams.gravity = Gravity.BOTTOM;
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showUploadMsg(Context context, UploadProtocolData uploadProtocolData, final showUploadMsgCallBack callBack){
        final Dialog dialog = new Dialog(context,R.style.dark_transdialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_upload_protocol,null);
        RelativeLayout rlUploadProtocol = view.findViewById(R.id.rl_upload_protocol);
        TextView tvUploadProtocolState = view.findViewById(R.id.tv_upload_protocol_state);
        RelativeLayout rlUploadPoster = view.findViewById(R.id.rl_upload_poster);
        TextView tvUploadPosterState = view.findViewById(R.id.tv_upload_poster_state);
        RelativeLayout rlUploadPrivary = view.findViewById(R.id.rl_upload_privary);
        TextView tvUploadPrivaryState = view.findViewById(R.id.tv_upload_privary_state);
        RelativeLayout rlCancel = view.findViewById(R.id.rl_cancel);
        if (uploadProtocolData.protocolMap != null) {
            if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.teamworkprotocalpicurl)) {
                if (TextUtils.equals(uploadProtocolData.protocolMap.teamworkprotocalstatus, Constants.PASS_TYPE)) {
                    tvUploadProtocolState.setText("已结成合作伙伴");
                    tvUploadProtocolState.setTextColor(context.getResources().getColor(R.color.color_001));
                } else if (TextUtils.equals(uploadProtocolData.protocolMap.teamworkprotocalstatus, Constants.REFUSE_TYPE)) {
                    tvUploadProtocolState.setText("已拒绝");
                    tvUploadProtocolState.setTextColor(context.getResources().getColor(R.color.color_006));
                } else if (TextUtils.equals(uploadProtocolData.protocolMap.teamworkprotocalstatus, Constants.CHECK_PEND_TYPE)) {
                    tvUploadProtocolState.setText("已上传-审核中");
                    tvUploadProtocolState.setTextColor(context.getResources().getColor(R.color.color_006));
                }
            }
            if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.posterpicurl)) {
                tvUploadPosterState.setText("已上传");
                tvUploadPosterState.setTextColor(context.getResources().getColor(R.color.color_001));
            }
            if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.secretagreementpicurl) &&
                    !TextUtils.isEmpty(uploadProtocolData.protocolMap.promisebookpicurl)) {
                tvUploadPrivaryState.setText("已上传/已上传");
                tvUploadPrivaryState.setTextColor(context.getResources().getColor(R.color.color_001));
            } else if (TextUtils.isEmpty(uploadProtocolData.protocolMap.secretagreementpicurl) &&
                    !TextUtils.isEmpty(uploadProtocolData.protocolMap.promisebookpicurl)) {
                String[] strs = {"未上传/", "已上传"};
                final int[] colors = new int[]{ContextCompat.getColor(context, R.color.color_102),
                        ContextCompat.getColor(context, R.color.color_001)};
                SpannableStringBuilder spannableStringBuilder =
                        ViewUtils.getDiffColorSpan(null, strs, colors);
                tvUploadPrivaryState.setText(spannableStringBuilder);
            } else if (!TextUtils.isEmpty(uploadProtocolData.protocolMap.secretagreementpicurl) &&
                    TextUtils.isEmpty(uploadProtocolData.protocolMap.promisebookpicurl)) {
                String[] strs = {"已上传", "/未上传"};
                final int[] colors = new int[]{ContextCompat.getColor(context, R.color.color_001),
                        ContextCompat.getColor(context, R.color.color_102)};
                SpannableStringBuilder spannableStringBuilder =
                        ViewUtils.getDiffColorSpan(null, strs, colors);
                tvUploadPrivaryState.setText(spannableStringBuilder);
            }
        }
        rlUploadPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onUploadposterClick();
                dialog.dismiss();
            }
        });
        rlUploadProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onUploadprotocolClick();
                dialog.dismiss();
            }
        });
        rlUploadPrivary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onUploadprivaryClick();
                dialog.dismiss();
            }
        });
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(layoutParams);
    }
    public interface showUploadMsgCallBack{
        void onUploadprotocolClick();
        void onUploadposterClick();
        void onUploadprivaryClick();
    }
    public static void showdialogknow(Context context,String content){
        final Dialog dialog = new Dialog(context,R.style.dark_transdialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_me_know,null);
        TextView tvKnow = view.findViewById(R.id.tv_me_know);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(content);
        tvKnow.setOnClickListener(new View.OnClickListener() {
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
        layoutParams.width= ViewUtils.dp2px(context,300);
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(layoutParams);
    }
}
