package com.ztstech.vgmate.weigets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.utils.ViewUtils;

/**
 * Created by zhiyuan on 2017/10/28.
 *
 */

public class IOSStyleDialog extends Dialog {



    public IOSStyleDialog(@NonNull Context context,
                          String message, final DialogInterface.OnClickListener confirmListener) {
        super(context);

        setCancelable(false);
        ViewUtils.setDialogFullScreen(this);

        View v = LayoutInflater.from(context).inflate(R.layout.dialog_ios_style, null, false);
        TextView confirm = v.findViewById(R.id.tv_confirm);
        TextView cancel = v.findViewById(R.id.tv_cancel);
        TextView tvMessage = v.findViewById(R.id.tv_message);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.onClick(IOSStyleDialog.this, 1);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvMessage.setText(message);

        setContentView(v);
    }
}
