package com.ztstech.vgmate.weigets.dateTimePicker;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ztstech.vgmate.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 可自定义滚动显示的内容，传入对应数据的List即可
 * 内置了一个年龄选择的dialog和性别选择dialog，方法为getAgePickcerDialog和getGenderPickerDialog,需要的时候 .show 就可以
 * <p>
 * 普遍使用示例：
 * onClick(){
 * showAgePickerDialog(ageList);
 * }
 * <p>
 * public void showAgePickerDialog(List<String> ageList) {
 * DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
 * agePickerDialog = builder.setData(ageList).setSelection(17).setTitle("取消")
 * .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
 *
 * @Override public void onDataSelected(String itemValue, int position) {
 * tvAge.setText(itemValue);
 * }
 * @Override public void onCancel() {
 * <p>
 * }
 * }).create();
 * agePickerDialog.show();
 * }
 */
public class DataPickerDialog extends Dialog {

    private Params params;

    public DataPickerDialog(Context context, int  themeResId) {
        super(context, themeResId);
    }

    private void setParams(DataPickerDialog.Params params) {
        this.params = params;
    }


    public void setSelection(String itemValue) {
        if (params.dataList.size() > 0) {
            int idx = params.dataList.indexOf(itemValue);
            if (idx >= 0) {
                params.initSelection = idx;
                params.loopData.setCurrentItem(params.initSelection);
            }
        }
    }

    public interface OnDataSelectedListener {
        void onDataSelected(String itemValue, int position);

        void onCancel();
    }

    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopData;
        private String title;
        private String unit;
        private int initSelection;
        private OnDataSelectedListener callback;
        private final List<String> dataList = new ArrayList<>();
    }

    public static class Builder {
        private final Context context;
        private final DataPickerDialog.Params params;

        public Builder(Context context) {
            this.context = context;
            params = new DataPickerDialog.Params();
        }

        private final String getCurrDateValue() {
            return params.loopData.getCurrentItemValue();
        }

        public Builder setData(List<String> dataList) {
            params.dataList.clear();
            params.dataList.addAll(dataList);
            return this;
        }

        public Builder setTitle(String title) {
            params.title = title;
            return this;
        }

        public Builder setUnit(String unit) {
            params.unit = unit;
            return this;
        }

        public Builder setSelection(int selection) {
            params.initSelection = selection;
            return this;
        }

        public Builder setOnDataSelectedListener(OnDataSelectedListener onDataSelectedListener) {
            params.callback = onDataSelectedListener;
            return this;
        }


        public DataPickerDialog create() {
            final DataPickerDialog dialog = new DataPickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.widget_picker_data, null);

            if (!TextUtils.isEmpty(params.title)) {
                TextView txTitle = (TextView) view.findViewById(R.id.tx_title);
                txTitle.setText(params.title);
                txTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        params.callback.onCancel();
                    }
                });
            }
            if (!TextUtils.isEmpty(params.unit)) {
                TextView txUnit = (TextView) view.findViewById(R.id.tx_unit);
                txUnit.setText(params.unit);
            }

            final LoopView loopData = (LoopView) view.findViewById(R.id.loop_data);
            loopData.setArrayList(params.dataList);
            loopData.setNotLoop();
            if (params.dataList.size() > 0) loopData.setCurrentItem(params.initSelection);
            view.findViewById(R.id.tx_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    params.callback.onDataSelected(getCurrDateValue(), loopData.getCurrentItem());
                }
            });

            Window win = dialog.getWindow();
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setAttributes(lp);
            win.setGravity(Gravity.BOTTOM);
            win.setWindowAnimations(R.style.Animation_Bottom_Rising);

            dialog.setContentView(view);
            dialog.setCanceledOnTouchOutside(params.canCancel);
            dialog.setCancelable(params.canCancel);

            params.loopData = loopData;
            dialog.setParams(params);

            return dialog;
        }
    }


    /**
     * 用于直接返回一个年龄选择dialog
     *
     * @param context   对应activity的context
     * @param selection 设置dialog显示的时候停留在哪个位置的数据上
     * @param tvAge     传入显示年龄的TextView
     * @return
     */
    public static DataPickerDialog getAgePickerDialog(Context context, int selection, final TextView tvAge) {
        List<String> ageList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            ageList.add(i + "");
        }
        DataPickerDialog agePickerDialog = new DataPickerDialog.Builder(context)
                .setData(ageList).setSelection(selection).setTitle("取消")
                .setOnDataSelectedListener(new OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        tvAge.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        return agePickerDialog;
    }


    /**
     * 用于直接返回一个性别选择dialog
     *
     * @param context   对应activity的context
     * @param selection 设置dialog显示的时候停留在哪个位置的数据上
     * @param tvGender  传入显示性别的TextView
     * @return
     */
    public static DataPickerDialog getGenderPickerDialog(Context context, int selection, final TextView tvGender) {
        List<String> ageList = new ArrayList<>();
        ageList.add("男");
        ageList.add("女");
        DataPickerDialog agePickerDialog = new DataPickerDialog.Builder(context)
                .setData(ageList).setSelection(selection).setTitle("取消")
                .setOnDataSelectedListener(new OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        tvGender.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        return agePickerDialog;
    }







}

