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
 * 年月日时间选择器
 * 使用示例：
 * onClick(){
 * showDatePicker(DateUtil.getDateForString("1990-01-01"));
 * }
 * public void showDatePicker(List<Integer> date){
 * DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
 * builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
 *
 * @Override public void onDateSelected(int[] dates) {
 * tvAge.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
 * + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
 * }
 * @Override public void onCancel() {
 * }
 * })
 * .setSelectYear(date.get(0) - 1)
 * .setSelectMonth(date.get(1) - 1)
 * .setSelectDay(date.get(2) - 1);
 * <p>
 * builder.setMaxYear(DateUtil.getYear());
 * builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
 * builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
 * dateDialog = builder.create();
 * dateDialog.show();
 * }
 */

public class DatePickerDialog extends Dialog {

    private static int MIN_YEAR = 1900;
    private static int MAX_YEAR = 2100;
    private Params params;

    public DatePickerDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    private void setParams(DatePickerDialog.Params params) {
        this.params = params;
    }

    public interface OnDateSelectedListener {
        void onDateSelected(int[] dates);

        void onCancel();
    }


    private static final class Params {
        private boolean shadow = true;
        private boolean canCancel = true;
        private LoopView loopYear, loopMonth, loopDay;
        private OnDateSelectedListener callback;
    }

    public static class Builder {
        private final Context context;
        private final DatePickerDialog.Params params;
        private Integer minYear;
        private Integer maxYear;
        private Integer selectYear;
        private Integer selectMonth;
        private Integer selectDay;
        private Integer minMonth;
        private Integer maxMonth;
        private Integer minDay;
        private Integer maxDay;

        public Builder(Context context) {
            this.context = context;
            params = new DatePickerDialog.Params();
        }

        public Builder setMinYear(int year) {
            minYear = year;
            MIN_YEAR = minYear;
            return this;
        }

        public Builder setMaxYear(int year) {
            maxYear = year;
            MAX_YEAR = maxYear;
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

        public Builder setMinDay(int day) {
            minDay = day;
            return this;
        }

        public Builder setMaxDay(int day) {
            maxDay = day;
            return this;
        }

        public Builder setSelectYear(int year) {
            this.selectYear = year;
            return this;
        }

        public Builder setSelectMonth(int month) {
            this.selectMonth = month;
            return this;
        }

        public Builder setSelectDay(int day) {
            this.selectDay = day;
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
            int currDay = Integer.parseInt(params.loopDay.getCurrentItemValue());
            return new int[]{currYear, currMonth, currDay};
        }

        public Builder setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
            params.callback = onDateSelectedListener;
            return this;
        }


        public DatePickerDialog create() {
            final DatePickerDialog dialog = new DatePickerDialog(context, params.shadow ? R.style.Theme_Light_NoTitle_Dialog : R.style.Theme_Light_NoTitle_NoShadow_Dialog);
            View view = LayoutInflater.from(context).inflate(R.layout.widget_picker_date, null);

            view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    params.callback.onCancel();
                }
            });


            Calendar c = Calendar.getInstance();
            //天
            final LoopView loopDay = (LoopView) view.findViewById(R.id.loop_day);
            loopDay.setArrayList(d(1, 30));
            if (selectDay != null) {
                loopDay.setCurrentItem(selectDay);
            } else {
                loopDay.setCurrentItem(c.get(Calendar.DATE));
            }
            loopDay.setNotLoop();
            //年
            final LoopView loopYear = (LoopView) view.findViewById(R.id.loop_year);
            loopYear.setArrayList(d(MIN_YEAR, MAX_YEAR - MIN_YEAR + 1));
            if (selectYear != null) {
                loopYear.setCurrentItem(selectYear - MIN_YEAR + 1);
            } else {
                loopYear.setCurrentItem(MAX_YEAR);
            }

            loopYear.setNotLoop();
            //月
            final LoopView loopMonth = (LoopView) view.findViewById(R.id.loop_month);
            loopMonth.setArrayList(d(1, 12));
            if (selectMonth != null) {
                if(loopYear.mCurrentItem==(selectYear - minYear )){

                }
                Log.e("DataPickerDialog", "chenchen---执行---->onItemSelect:设置当前月的位置 "+(selectMonth+1-minMonth) );

            }
            loopMonth.setNotLoop();

            loopYear.setListener(new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    if(loopYear.mCurrentItem!=(selectYear - minYear )){
                        //如果当前年位置不是选中的位置，则将月置为0
                        loopMonth.setCurrentItem(0);
                    }
                }
            });

            final LoopListener maxDaySyncListener = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    Calendar c = Calendar.getInstance();
                    boolean needFixed = false;
                    /**
                     * 最小年的情况，如果当前为最小年，则月和日都要从最小到最大
                     */
                    if (minYear != null && Integer.parseInt(loopYear.getCurrentItemValue()) == minYear && minMonth != null) {
                        //当前月份显示为最小到最大
                        Log.e("DataPickerDialog", "chenchen---执行---->onItemSelect:设置当前月的起始值 :"+minMonth+"\n"+";"+(12 - minMonth + 1) );
                        loopMonth.setArrayList(d(minMonth, 12 - minMonth + 1));
                             /*   if(Integer.parseInt(loopMonth.getCurrentItemValue())<minMonth){
                                    loopMonth.setCurrentItem(minMonth - 1);
                                }*/

                    } else {
                        //未指定
                        loopMonth.setArrayList(d(1, 12));

                    }
                    if (maxYear != null) {
                        if (Integer.parseInt(loopYear.getCurrentItemValue()) == maxYear) {
                            if (maxMonth != null) {
                              /*  if(Integer.parseInt(loopMonth.getCurrentItemValue())>maxMonth){
                                    loopMonth.setCurrentItem(maxMonth - 1);
                                }*/
                            }
                        } else if (Integer.parseInt(loopYear.getCurrentItemValue()) > maxYear) {
                            loopYear.setCurrentItem(maxYear - MIN_YEAR);
                        }
                    }
                    Log.e("DataPickerDialog", "chenchen---执行---->onItemSelect: getCurrentItemValue():"+(loopMonth.getCurrentItemValue())+"\n mCurrentItem:"+loopMonth.mCurrentItem );
                        c.set(Integer.parseInt(loopYear.getCurrentItemValue()), Integer.parseInt(loopMonth.getCurrentItemValue()) - 1, 1);
                        c.roll(Calendar.DATE, false);

                /*    if (needFixed) {
                        int maxDayOfMonth = c.get(Calendar.DATE);
                        int fixedCurr = loopDay.getCurrentItem();
                        loopDay.setArrayList(d(1, maxDayOfMonth));
                        // 修正被选中的日期最大值
                        if (fixedCurr > maxDayOfMonth) fixedCurr = maxDayOfMonth - 1;
                        loopDay.setCurrentItem(fixedCurr);
                    }*/

                if(minYear != null && Integer.parseInt(loopYear.getCurrentItemValue()) == minYear && minMonth != null&&minMonth== Integer.parseInt(loopMonth.getCurrentItemValue())){
                    Log.e("DataPickerDialog", "chenchen---执行---->onItemSelect:默认选中日： "+(selectDay-minDay+1));
                    loopDay.setCurrentItem(selectDay-minDay+1);
                }else {
                    loopDay.setCurrentItem(0);
                }
                }
            };

            //日期的回调
            final LoopListener dayLoopListener = new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                   /* if (minYear != null && minMonth != null && minDay != null
                            && Integer.parseInt(loopYear.getCurrentItemValue()) == minYear
                            && Integer.parseInt(loopMonth.getCurrentItemValue()) == minMonth
                            && Integer.parseInt(loopDay.getCurrentItemValue()) < minDay
                            ) {
                        loopDay.setCurrentItem(minDay - 1);
                    }

                    if (maxYear != null && maxMonth != null && maxDay != null
                            && Integer.parseInt(loopYear.getCurrentItemValue()) == maxYear
                            && Integer.parseInt(loopMonth.getCurrentItemValue()) == maxMonth
                            && Integer.parseInt(loopDay.getCurrentItemValue()) > maxDay
                            ) {
                        loopDay.setCurrentItem(maxDay - 1);
                    }*/
                    //年月最小的情况
                    if(minYear != null && Integer.parseInt(loopYear.getCurrentItemValue()) == minYear && minMonth != null&& Integer.parseInt(loopMonth.getCurrentItemValue()) == minMonth){

                        loopDay.setArrayList(d(minDay,getDaysByYearMonth(minYear,minMonth)-minDay+1));
                    }else {
                        loopDay.setArrayList(d(1,getDaysByYearMonth(minYear,minMonth)));
                    }
                }
            };


            loopMonth.setListener(maxDaySyncListener);
            loopDay.setListener(dayLoopListener);

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
            params.loopDay = loopDay;
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
        /**
         * 根据年 月 获取对应的月份 天数
         * */
        public static int getDaysByYearMonth(int year, int month) {

            Calendar a = Calendar.getInstance();
            a.set(Calendar.YEAR, year);
            a.set(Calendar.MONTH, month - 1);
            a.set(Calendar.DATE, 1);
            a.roll(Calendar.DATE, -1);
            int maxDate = a.get(Calendar.DATE);
            return maxDate;
        }
    }
}
