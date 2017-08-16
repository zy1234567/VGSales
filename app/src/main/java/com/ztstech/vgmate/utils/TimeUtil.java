package com.ztstech.vgmate.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by smm on 2017/5/19.
 * 时间格式转换工具类
 */

public class TimeUtil {
    /**
     * 根据格式获取时间
     * @param pattern
     * @return
     */
    public static String getDateWithFormater(String pattern) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        return format.format(date);
    }

    /**
     * 根据时间string获取时间戳
     * @param dateString 时间
     * @param pattern 格式
     * @return
     */
    public static String getDateWithString(String dateString, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        try {
            return format.format(format.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取空间gps存储 时间距离
     * <p> 3.61km/6小时前 <br>3.61km/23小时前 <br>3.61km/1天前</p>
     * @param oldDate 对方上传gps时间
     * @return
     */
    public static int getTimeHoursOffset(Date oldDate) {
        Date newDate = new Date();
        long diff = newDate.getTime() - oldDate.getTime();
        long hours = diff / (1000 * 60 * 60);
        return (int) hours;
    }

    /**
     * 将时间转换成距离现在的时间
     * @param time
     * @return
     */
    public static String InformationTime(String time) {
        Calendar current = Calendar.getInstance();
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;
        int day = current.get(Calendar.DATE);
        int hournow = current.get(Calendar.HOUR_OF_DAY);
        int minutenow = current.get(Calendar.MINUTE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time == null || "".equals(time)){
            return "";
        }
        try {
            Date date = sdf.parse(time);
            Calendar trueTime = Calendar.getInstance();
            trueTime.setTime(date);
            int trueYear = trueTime.get(Calendar.YEAR);
            int trueMonth = trueTime.get(Calendar.MONTH) + 1;
            int trueDay = trueTime.get(Calendar.DATE);
            int hour = trueTime.get(Calendar.HOUR_OF_DAY);
            int minute = trueTime.get(Calendar.MINUTE);
            String formatMonth = formatInt(trueMonth);
            String formatDay = formatInt(trueDay);
            if (trueYear != year) {//年份相同 ， 同年
                return trueYear + "年" + formatMonth +"月";
            } else if (month == trueMonth) {//同月
                int num = day - trueDay;//同日
                if (num == 0) {
                    int numHour = hournow - hour;
                    if(numHour == 0){//同时
                        int numMinute = minutenow - minute;
                        if(numMinute == 0){//同分
                            return "刚刚";
                        }else{
                            return numMinute +"分钟前";
                        }
                    }else{
                        return numHour + "小时前";
                    }
                } else if (num <= 7) {
                    return num + "天前";
                } else {
                    return formatMonth + "月" + formatDay + "日";
                }
            } else if (month != trueMonth) {//月份不同
                int dayOfYear = current.get(Calendar.DAY_OF_YEAR);
                int trueDayOfYear = trueTime.get(Calendar.DAY_OF_YEAR);
                if ((dayOfYear - trueDayOfYear) <= 7) {
                    return (dayOfYear - trueDayOfYear) +"天前";
                }else{
                    return formatMonth + "月" + formatDay + "日";
                }
            }
        } catch (ParseException e) {

        }
        return "";
    }

    private static String formatInt(int num) {
        if (num <= 9) {
            return "0" + num;
        } else {
            return "" + num;
        }
    }

    /**
     * 分享界面 计算当前时间距离
     */
    public static String calculateTimeDifference(String time) {
        Calendar current = Calendar.getInstance();
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;
        int day = current.get(Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(time);
            Calendar trueTime = Calendar.getInstance();
            trueTime.setTime(date);
            int trueYear = trueTime.get(Calendar.YEAR);
            int trueMonth = trueTime.get(Calendar.MONTH) + 1;
            int trueDay = trueTime.get(Calendar.DATE);
            int hour = trueTime.get(Calendar.HOUR_OF_DAY);
            int minute = trueTime.get(Calendar.MINUTE);
            String formatMonth = formatInt(trueMonth);
            String formatDay = formatInt(trueDay);
            String formatHour = formatInt(hour);
            String formatMinute = formatInt(minute);
            String HourAndMinute = formatHour + ":" + formatMinute;
            if (trueYear != year) {
                return trueYear + "/" + formatMonth + "/" + formatDay + " "
                        + HourAndMinute;
            } else if (month == trueMonth) {
                int num = day - trueDay;
                if (num == 0) {
                    return HourAndMinute;
                } else if (num == 1) {
                    return "昨天" + " " + HourAndMinute;
                } else if (num == 2) {
                    return "前天" + " " + HourAndMinute;
                } else {
                    return trueMonth + "月" + trueDay + "日" + " "
                            + HourAndMinute;
                }
            } else if (month != trueMonth) {
                int dayOfYear = current.get(Calendar.DAY_OF_YEAR);
                int trueDayOfYear = trueTime.get(Calendar.DAY_OF_YEAR);
                if ((dayOfYear - trueDayOfYear) == 1) {
                    return "昨天" + " " + HourAndMinute;
                } else if ((dayOfYear - trueDayOfYear) == 2) {
                    return "前天" + " " + HourAndMinute;
                }
            }
            return trueMonth + "月" + trueDay + "日" + " " + HourAndMinute;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 班级列表 计算当前时间距离
     */
    public static String calculateClassTime(String time) {
        Calendar current = Calendar.getInstance();
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH) + 1;
        int day = current.get(Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(time);
            Calendar trueTime = Calendar.getInstance();
            trueTime.setTime(date);
            int trueYear = trueTime.get(Calendar.YEAR);
            int trueMonth = trueTime.get(Calendar.MONTH) + 1;
            int trueDay = trueTime.get(Calendar.DATE);
            int hour = trueTime.get(Calendar.HOUR_OF_DAY);
            int minute = trueTime.get(Calendar.MINUTE);
            String formatMonth = formatInt(trueMonth);
            String formatDay = formatInt(trueDay);
            String formatHour = formatInt(hour);
            String formatMinute = formatInt(minute);
            String HourAndMinute = formatHour + ":" + formatMinute;
            if (trueYear != year) {
                return trueYear + "/" + formatMonth + "/" + formatDay + " "
                        + HourAndMinute;
            } else if (month == trueMonth) {
                int num = day - trueDay;
                if (num == 0) {
                    return "今天" + " " +HourAndMinute;
                } else if (num == 1) {
                    return "昨天" + " " + HourAndMinute;
                } else if (num == 2) {
                    return "前天" + " " + HourAndMinute;
                } else {
                    return trueYear + "-" + trueMonth + "-" + trueDay;
                }
            } else if (month != trueMonth) {
                int dayOfYear = current.get(Calendar.DAY_OF_YEAR);
                int trueDayOfYear = trueTime.get(Calendar.DAY_OF_YEAR);
                if ((dayOfYear - trueDayOfYear) == 1) {
                    return "昨天" + " " + HourAndMinute;
                }
            }
            return trueYear + "-" + trueMonth + "-" + trueDay;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }
}
