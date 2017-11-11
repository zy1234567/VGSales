package com.ztstech.vgmate.weigets.dateTimePicker;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.ztstech.vgmate.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 年月时间选择器
 * <p>
 * 年月的最大值和最小值不可以为空
 */

public class YMDatePickerDialog extends Dialog {


    private Params params;

    public YMDatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(YMDatePickerDialog.Params params) {
        this.params = params;
    }

    public interface OnDateSelectedListener {
        void onDateSelected(int[] dates);

        void onCancel();
    }


    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopYear, loopMonth;
        private OnDateSelectedListener callback;
    }

    public static class Builder {
        private final Context context;
        private final YMDatePickerDialog.Params params;
        private Integer minYear;
        private Integer maxYear;
        private Integer selectYear;
        private Integer selectMonth;
        private Integer minMonth;
        private Integer maxMonth;

        public Builder(Context context) {
            this.context = context;
            params = new YMDatePickerDialog.Params();
        }

        public Builder setMinYear(int year) {
            minYear = year;
            return this;
        }

        public Builder setMaxYear(int year) {
            maxYear = year;
            return this;
        }

        public Builder setMinMonth(int month) {
            minMonth = month;
            return this;
        }

        public Builder setMaxMonth(int month) {
            maxMonth = month;
            return this;
        }


        public Builder setSelectYear(int year) {
            this.selectYear = year-1;//为列表的position
            return this;
        }

        public Builder setSelectMonth(int month) {
            this.selectMonth = month-1;
            return this;
        }


        /**
         * 获取当前选择的日期
         *
         * @return int[]数组形式返回。例[1990,6,15]
         */
        private final int[] getCurrDateValues() {
            int currYear = Integer.parseInt(params.loopYear.getCurrentItemValue());
            int currMonth = Integer.parseInt(params.loopMonth.getCurrentItemValue());
            return new int[]{currYear, currMonth};
        }

        public Builder setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
            params.callback = onDateSelectedListener;
            return this;
        }


        public YMDatePickerDialog create() {
            final YMDatePickerDialog dialog = new YMDatePickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.widget_picker_y_m, null);

            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    params.callback.onCancel();
                }
            });


            Calendar c = Calendar.getInstance();


            //loopDay.setNotLoop();


            final LoopView loopYear = (LoopView) view.findViewById(R.id.loop_year);
            loopYear.setArrayList(d(minYear, maxYear - minYear + 1));
            if (selectYear != null) {
                loopYear.setCurrentItem(selectYear - minYear + 1);
            } else {
                selectYear=maxYear;
                loopYear.setCurrentItem(selectYear);
            }
            loopYear.setNotLoop();

            final LoopView loopMonth = (LoopView) view.findViewById(R.id.loop_month);

            loopMonth.setArrayList(d(1, 12 - 1 + 1));
            /*if(maxMonth!=null){
                loopMonth.setArrayList(d(1, maxMonth-1));
            }*/
            if (selectMonth != null) {
                selectYear=selectYear+1;
                Log.e("YMDatePickerDialog", "此时"+"maxYear:"+maxYear+"minYear"+minYear+"selectyear"+selectYear);

                if(selectYear.equals(minYear)&&!selectYear.equals(maxYear)){
                    loopMonth.setCurrentItem(selectMonth-minMonth+1);
                    Log.e("YMDatePickerDialog", "selectMonth 在最小年不在最大，此时"+"currentPosition:"+(selectMonth-minMonth+1)+"selectMonth"+selectMonth+"minMonth"+minMonth);
                }else if(!selectYear.equals(minYear)&&selectYear.equals(maxYear)){
                    loopMonth.setCurrentItem((selectMonth+1)-1);
                    Log.e("YMDatePickerDialog", "selectMonth 在最大年不在最小，此时"+"currentPosition:"+(selectMonth)+"selectMonth"+selectMonth+"minMonth"+minMonth+"maxMonth"+maxMonth);
                }else if(selectYear.equals(maxYear)&&selectYear.equals(minYear)){
                    Log.e("YMDatePickerDialog", "selectMonth 在最大年同时最小，此时"+"currentPosition:"+(selectMonth-minMonth+1)+"selectMonth"+selectMonth+"minMonth"+minMonth);
                    loopMonth.setCurrentItem(selectMonth-minMonth+1);
                }else  if(!selectYear.equals(maxYear)&&!selectYear.equals(minYear)){
                    Log.e("YMDatePickerDialog", "selectMonth 不在最大和最小，此时"+"currentPosition:"+(selectMonth-1+1)+"selectMonth"+selectMonth+"minMonth"+minMonth);
                    loopMonth.setCurrentItem(selectMonth-1+1);

                }
            } else {
                Log.e("YMDatePickerDialog", "selectMonth = null"+"currentPosition:"+selectMonth+"Calendar"+c.get(Calendar.MONTH));
                loopMonth.setCurrentItem(
                        maxMonth
                );
            }
            loopMonth.setNotLoop();


            final LoopListener maxDaySyncListener = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    Calendar c = Calendar.getInstance();

                    //如果当前为最小年，设置最小月份
                    if (Integer.parseInt(loopYear.getCurrentItemValue()) == minYear && Integer.parseInt(loopYear.getCurrentItemValue()) != maxYear) {

                        loopMonth.setArrayList(d(minMonth, 12 - minMonth + 1));
                        Log.e("YMDatePickerDialog", "此时最小年"+minMonth+"arrayList大小"+(12 - minMonth + 1));

                    } else if (Integer.parseInt(loopYear.getCurrentItemValue()) == maxYear && Integer.parseInt(loopYear.getCurrentItemValue()) != minYear) {
                        Log.e("YMDatePickerDialog", "此时最大年,但不是最小年："+"arrayList大小="+maxMonth);
                        loopMonth.setArrayList(d(1, maxMonth));

                    } else if (Integer.parseInt(loopYear.getCurrentItemValue())== maxYear && Integer.parseInt(loopYear.getCurrentItemValue()) == minYear) {
                        List arrayList=d(minMonth, maxMonth - minMonth + 1);
                        Log.e("YMDatePickerDialog", "arrayList大小 " + arrayList.size());
                        loopMonth.setArrayList(arrayList);
                        Log.e("YMDatePickerDialog", "此时最大年最小年相同 ：arrayList大小 " + arrayList.size());
                    } else {
                        Log.e("YMDatePickerDialog", "此时不在最大年也不在最小年");
                        loopMonth.setArrayList(d(1, 12));

                    }
                    Log.e("YMDatePickerDialog", "chenchen---执行---->onItemSelect: loopMonth.getCurrentItemValue()="+loopMonth.getCurrentItemValue());
                    c.set(Integer.parseInt(loopYear.getCurrentItemValue()), Integer.parseInt(loopMonth.getCurrentItemValue()) - 1, 1);
                    c.roll(Calendar.DATE, false);


                }
            };


            loopYear.setListener(new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    Log.e("YMDatePickerDialog", "年份当前选中位置onItemSelect为"+loopYear.mCurrentItem+"select位置为"+selectYear);
                    if(loopYear.mCurrentItem!=(selectYear - minYear )){
                        //如果当前年位置不是选中的位置，则将月置为0
                        loopMonth.setCurrentItem(0);
                    }
                }
            });
            loopMonth.setListener(maxDaySyncListener);

            view.findViewById(R.id.tx_finish).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    params.callback.onDateSelected(getCurrDateValues());
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

            params.loopYear = loopYear;
            params.loopMonth = loopMonth;
            dialog.setParams(params);

            return dialog;
        }

        /**
         * 将数字传化为集合，并且补充0
         *
         * @param startNum 数字起点
         * @param count    数字个数
         * @return
         */
        private static List<String> d(int startNum, int count) {
            String[] values = new String[count];
            for (int i = startNum; i < startNum + count; i++) {
                String tempValue = (i < 10 ? "0" : "") + i;
                values[i - startNum] = tempValue;
            }
            return Arrays.asList(values);
        }

    }
}
