package com.ztstech.vgmate.weigets;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ztstech.vgmate.R;

/**
 * Created by lenovo on 2018/1/15.
 */

public class DialogMultiSelect extends Dialog{
    private Context context;
    private String[] datasource;
    private AdapterView.OnItemClickListener onItemClickListener;

    public DialogMultiSelect(Context context, String[] datasource, AdapterView.OnItemClickListener onItemClickListener){
        super(context, R.style.transdialog);
        this.context = context;
        this.datasource = datasource;
        this.onItemClickListener = onItemClickListener;
        initView();
    }

    public DialogMultiSelect(@NonNull Context context) {
        super(context);
    }

    public DialogMultiSelect(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogMultiSelect(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void initView(){
        View view = LayoutInflater.from(context).inflate(
                R.layout.dialog_multi_select, null);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setContentView(view);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        window.setAttributes(lp);
    }

    BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return datasource.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_dialog_muliti_select,viewGroup,false);
            TextView tvCancel = view.findViewById(R.id.tv_cancel);
            TextView textView = view.findViewById(R.id.tv_item);
            textView.setText(datasource[i]);
            return view;
        }
    };

}
