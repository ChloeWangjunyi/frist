package com.lovcreate.core.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yuanYe on 2016/9/14.
 * QQ 962851730
 */
public class DateUtils {

    public static String pareTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
        String s = dateFormater.format(date);
        return s;
    }

    public static String pareTime3(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM-dd HH:mm");
        String s = dateFormater.format(date);
        return s;
    }

    public static String pareTime2(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        Date date = calendar.getTime();
        SimpleDateFormat dateFormater = new SimpleDateFormat("MM月dd日 HH:mm");
        String s = dateFormater.format(date);
        return s;
    }

    /**
     *
     * @param year
     * @param month
     *            从0开始，0代表一月
     * @param day
     * @return 返回的1代表周日，依次类推
     */
    public static int getWeekDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 通过年份和月份 得到当月的日子
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     * 
     * @param year
     *            年份
     * @param month
     *            月份，传入系统获取的，不需要正常的
     * @return
     *         日：1 一：2 二：3 三：4 四：5 五：6 六：7
     */
    public static int getFirstDayWeek(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
