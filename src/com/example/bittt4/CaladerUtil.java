
package com.example.bittt4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.text.format.DateFormat;

public class CaladerUtil {
    // 获得某一年某一个月一共有几天
    public  static int getDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    public static int getCurrentMonthDays(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }
    
    

    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    // 得到当前的日期
    public static String getCurrentDate() {
        String str = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        return str;
    }

    public static String[] getCurrentData_array() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.setTime(new Date());
        // 显示年份
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String[] strs = new String[] {
                "" + year, "" + month, "" + day
        };
        return strs;
    }

    public static int[] getCurrentData_array_int() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.setTime(new Date());
        // 显示年份
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int[] strs = new int[] {
                year, month, day
        };
        return strs;
    }

    // 获得星期几
    public static String getCurrentTimeWeekday() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.setTime(new Date());
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return "" + week;
    }
    

    // 获得星期几
    public static int getCurrentTimeWeekday_int() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.setTime(new Date());
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }
    
    // 获得星期几
    public static int getweekdayOnFirstDayOfMonth_int(int year, int month) {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DATE, 1);
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }    // 获得星期几
    
    
    public static String getweekdayOnFirstDayOfMonth(int year, int month) {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DATE, 1);
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week+"";
    }
    
    public static String getSpecificWeekday(int year, int month, int day) {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DATE, day);
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week+"";
    }
    
    // 获得星期几
    public static int getCurrentMonthFirstDayWeek_int() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(new Date());
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.set(Calendar.DATE, 1);
        // 显示年份
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }
    
    
    
    

    // 获得星期几
    public static String getDayOfYear() {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // 初始化 Calendar 对象，但并不必要，除非需要重置时间
        calendar.setTime(new Date());
        // 显示年份
        int numDays = calendar.get(Calendar.DAY_OF_YEAR);
        return "" + numDays;
    }

    public static Calendar calendar;

    public static Calendar getCalendar() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
            calendar.clear();
        }
        return calendar;
    }

    // // 3小时以后
    // calendar.add(Calendar.HOUR_OF_DAY, 3);
    // int HOUR_OF_DAY = calendar.get(Calendar.HOUR_OF_DAY);
    // System.out.println("HOUR_OF_DAY + 3 = " + HOUR_OF_DAY);
    //
    // // 当前分钟数
    // int MINUTE = calendar.get(Calendar.MINUTE);
    // System.out.println("MINUTE = " + MINUTE);
    //
    // // 15 分钟以后
    // calendar.add(Calendar.MINUTE, 15);
    // MINUTE = calendar.get(Calendar.MINUTE);
    // System.out.println("MINUTE + 15 = " + MINUTE);
    //
    // // 30分钟前
    // calendar.add(Calendar.MINUTE, -30);
    // MINUTE = calendar.get(Calendar.MINUTE);
    // System.out.println("MINUTE - 30 = " + MINUTE);
    //
    // // 格式化显示
    // str = (new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());
    // System.out.println(str);
    //
    // // 重置 Calendar 显示当前时间
    // calendar.setTime(new Date());
    // str = (new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());
    // System.out.println(str);
    //
    // // 创建一个 Calendar 用于比较时间
    // Calendar calendarNew = Calendar.getInstance();
    //
    // // 设定为 5 小时以前，后者大，显示 -1
    // calendarNew.add(Calendar.HOUR, -5);
    // System.out.println("时间比较：" + calendarNew.compareTo(calendar));
    //
    // // 设定7小时以后，前者大，显示 1
    // calendarNew.add(Calendar.HOUR, +7);
    // System.out.println("时间比较：" + calendarNew.compareTo(calendar));
    //
    // // 退回 2 小时，时间相同，显示 0
    // calendarNew.add(Calendar.HOUR, -2);
    // System.out.println("时间比较：" + calendarNew.compareTo(calendar));

}
