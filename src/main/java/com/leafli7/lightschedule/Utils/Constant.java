package com.leafli7.lightschedule.Utils;

import android.util.Log;

import com.leafli7.lightschedule.Entity.WeekSchedule;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xxcub on 2015/10/29.
 */
public abstract class Constant {
    /**
     * 原则：程序中计数全部从０开始，数据库中计数按照实际情况技术．
     *      即，程序中的第０周代表数据库（实际上）的第一周．
     */

    public static int DAY_ACCOUNT_OF_ONE_WEEK = 7;
    public static int CURRENT_WEEK = 0;
    public static int CURRENT_DAY_OF_WEEK = 0;
    public static int LESSEN_TIME_ACCOUNT = 6;
    public static String[] LESSON_NUM = {"一", "二", "三", "四", "五", "六"};
    public static String[] LESSON_TIME = {"8:00\n9:45", "10:00\n11:45", "14:00\n15:45", "16:00\n17:45", "18:00\n19:45", "20:00\n21:45"};
    public static final String DatabaseName = "own_schedule.db";

    public static WeekSchedule weekSchedule;
    public static boolean isShowAllLesson;  //是否显示所有课程(非本周课程)

    public static void initial(){
        initialConfig();
        initialTime();
        initialWeekSchedule();
    }

    private static void initialWeekSchedule(){

    }

    private static void initialConfig(){
        isShowAllLesson = false;
    }

    private static void initialTime(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int curDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        curDayOfWeek = curDayOfWeek-1 == 0 ? 6 : curDayOfWeek-1;
        CURRENT_DAY_OF_WEEK = curDayOfWeek - 1;
    }
}
