package com.ztstech.vgmate.weigets;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ztstech.vgmate.R;

/**
 *
 * @author smm
 * @date 2017/11/23
 * 底部弹出来有两个选项的dialog
 */

public class BottomDoubleSelectDialog extends Dialog {

    private Context context;

    private ClickListener clickListener;

    private String textOne,textTwo;

    public BottomDoubleSelectDialog(Context context,String textOne,String textTwo,ClickListener clickListener){
        super(context,R.style.transdialog);
        this.context = context;
        this.textOne = textOne;
        this.textTwo = textTwo;
        this.clickListener = clickListener;
        initView();
    }


    public BottomDoubleSelectDialog(Context context) {
        super(context);
    }

    public BottomDoubleSelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BottomDoubleSelectDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initView(){
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_bottom_double_select, null);
        LinearLayout layoutOne = (LinearLayout) view
                .findViewById(R.id.layout_one);
        LinearLayout layoutTwo = (LinearLayout) view
                .findViewById(R.id.layout_two);
        TextView tv1 = (TextView) view.findViewById(R.id.tv_one);
        TextView tv2 = (TextView) view.findViewById(R.id.tv_two);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        setContentView(view);
        setCanceledOnTouchOutside(true);
        layoutOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickOne();
                dismiss();
            }
        });
        layoutTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClickTwo();
                dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv1.setText(textOne);
        tv2.setText(textTwo);
        show();
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(lp);
    }

    public interface ClickListener{
        void onClickOne();
        void onClickTwo();
    }



}
