package com.leafli7.lightschedule.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.leafli7.lightschedule.Entity.Lesson;

/**
 * Created by xxcub on 2015/10/30.
 */
public class OwnDbHelper extends SQLiteOpenHelper {
    String TAG = "leafli7 debug : " + getClass().getSimpleName();
    private static String dbScheduleTableName = Constant.DbScheduleTableName;
    private static int version  = 1;
    private Context context;

    public OwnDbHelper(Context context) {
        super(context, dbScheduleTableName, null, version);
        this.context = context;
        Log.e(TAG, "sql construct");

//        String sql = "create table if not exists " + dbScheduleTableName + "(" +
//                "id integer primary key AUTOINCREMENT," +
//                "name text not null," +
//                "start_week tinyint not null," +
//                "end_week tinyint not null," +
//                "lesson_time_num tinyint not null," +
//                "teacher_name nchar(55)," +
//                "classroom text not null," +
//                "is_tiny_lesson int not null," +
//                "is_first_half int," +
//                "is_single_week_lesson int not null," +
//                "is_odd_week_lesson int," +
//                Constant.tableColumnDayOfWeek + " int" +
//                ");";
//        getWritableDatabase().execSQL(sql);

        showAllData();
    }

    //show all date in db
    //for debug
    void showAllData(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from " + dbScheduleTableName + ";";
        Log.e(TAG, "Show all data from database");
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Log.e(TAG, "------");
            String[] names = cursor.getColumnNames();
            for (String name:names) {
                Log.e(TAG, name + " : " + cursor.getString(cursor.getColumnIndex(name)));
            }
            Log.e(TAG, "------");
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        private int id;
//        private String name;
//        private int startWeek;
//        private int endWeek;
//        private int lessonTimeNum;  //上课节数
//        private String teacherName;
//        private String classroom;
//        private boolean isTinyLesson;   //是否为半节课的小课
//        private boolean isFirstHalf;    //是否为前半小节课 false则为后半节
//        private boolean isSingleWeekLesson; //是否为单双周周课程
//        private boolean isOddWeekLesson; //是否为奇数周课程

        String sql = "create table if not exists " + dbScheduleTableName + "(" +
                "id integer primary key AUTOINCREMENT," +
                "name text not null," +
                "start_week tinyint not null," +
                "end_week tinyint not null," +
                "lesson_time_num tinyint not null," +
                "teacher_name nchar(55)," +
                "classroom text not null," +
                "is_tiny_lesson int not null," +
                "is_first_half int," +
                "is_single_week_lesson int not null," +
                "is_odd_week_lesson int," +
                Constant.tableColumnDayOfWeek + " int" +
                ");";
        db.execSQL(sql);
        Log.e(TAG, "sqlite oncreate");
    }

    public boolean insertLesson(Lesson lesson){
        try{
            int isTiny = lesson.isTinyLesson()?1:0;
            int isSingle = lesson.isSingleWeekLesson()?1:0;
            int isFirst = lesson.isFirstHalf()?1:0;
            int isOdd = lesson.isOddWeekLesson()?1:0;
            String sql = "";
            if (!lesson.isSingleWeekLesson() && !lesson.isTinyLesson()) {
                sql = "insert into " + dbScheduleTableName + "" +
                        "(name,start_week,end_week,lesson_time_num,teacher_name,classroom,is_tiny_lesson,is_single_week_lesson," + Constant.tableColumnDayOfWeek + ") " +
                        "values " +
                        "('" + lesson.getName() + "'," + lesson.getStartWeek() + "," + lesson.getEndWeek() + "," + lesson.getLessonTimeNum() +  ",'" + lesson.getTeacherName()+ "'" +
                        ",'" + lesson.getClassroom() + "'," + isTiny + "," + isSingle + "," + lesson.getDayOfWeek() + ")";
                getWritableDatabase().execSQL(sql);
            }else if (lesson.isSingleWeekLesson() && !lesson.isTinyLesson()){
                sql = "insert into " + dbScheduleTableName + " {" +
                        "(name,start_week,end_week,lesson_time_num,teacher_name,classroom,is_tiny_lesson,is_single_week_lesson,is_odd_week_lesson," + Constant.tableColumnDayOfWeek + ") " +
                        "values " +
                        "('" + lesson.getName() + "'," + lesson.getStartWeek() + "," + lesson.getEndWeek() + "," + lesson.getLessonTimeNum() + ",'" + lesson.getTeacherName()+ "'" +
                        ",'" + lesson.getClassroom() + "'," + lesson.isTinyLesson() + "," + lesson.isSingleWeekLesson() + "," + lesson.isOddWeekLesson() + "," + lesson.getDayOfWeek() + ")";
                getWritableDatabase().execSQL(sql);
            }else if (!lesson.isSingleWeekLesson() && lesson.isTinyLesson()){
                sql = "insert into " + dbScheduleTableName + " {" +
                        "(name,start_week,end_week,lesson_time_num,teacher_name,classroom,is_tiny_lesson,is_first_half,is_single_week_lesson," + Constant.tableColumnDayOfWeek + ") " +
                        "values " +
                        "('" + lesson.getName() + "'," + lesson.getStartWeek() + "," + lesson.getEndWeek() + "," + lesson.getLessonTimeNum() + ",'" + lesson.getTeacherName()+ "'" +
                        ",'" + lesson.getClassroom() + "'," + lesson.isTinyLesson() + "," + lesson.isFirstHalf() + ","+ lesson.isSingleWeekLesson() + "," + lesson.getDayOfWeek() + ")";
                getWritableDatabase().execSQL(sql);
            }else if (lesson.isSingleWeekLesson() && lesson.isTinyLesson()){
                sql = "insert into " + dbScheduleTableName + " {" +
                        "(name,start_week,end_week,lesson_time_num,teacher_name,classroom,is_tiny_lesson,is_first_half,is_single_week_lesson,is_odd_week-lesson," + Constant.tableColumnDayOfWeek + ") " +
                        "values " +
                        "('" + lesson.getName() + "'," + lesson.getStartWeek() + "," + lesson.getEndWeek() + "," + lesson.getLessonTimeNum() + ",'" + lesson.getTeacherName()+ "'" +
                        ",'" + lesson.getClassroom() + "'," + lesson.isTinyLesson() + "," + lesson.isFirstHalf() + ","+ lesson.isSingleWeekLesson() + "," + lesson.isOddWeekLesson() + "," + lesson.getDayOfWeek() + ")";
                getWritableDatabase().execSQL(sql);
            }else{
                Log.e(TAG, "no sql insert!");
            }
        }catch (Exception e){
            Toast.makeText(context, "Wrong input info! Please check!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "wrong input info! Exception!");
            Log.e(TAG, e.toString());
            return false;
        }

        return true;
    }

    public boolean deleteLesson(int lessonId){
        try {
            String sql = "delete from " + dbScheduleTableName + " where id=" + lessonId + ";";
            getWritableDatabase().execSQL(sql);
        }catch(Exception e){
            e.printStackTrace();
            Log.e(TAG, "delete item exception!");
            Toast.makeText(context, "delete item exception!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public Cursor querySchedule(){
        String sql = "select * from " + dbScheduleTableName + ";";
        return getWritableDatabase().rawQuery(sql, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
