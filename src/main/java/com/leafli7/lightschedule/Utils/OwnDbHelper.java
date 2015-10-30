package com.leafli7.lightschedule.Utils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.leafli7.lightschedule.Entity.Lesson;

/**
 * Created by xxcub on 2015/10/30.
 */
public class OwnDbHelper extends SQLiteOpenHelper {
    String TAG = getClass().getSimpleName();
    private static String databaseName = Constant.DatabaseName;
    private static int version  = 1;
    private Context context;

    public OwnDbHelper(Context context) {
        super(context, databaseName, null, version);
        this.context = context;
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

        String sql = "create table if not exist " + databaseName + " {" +
                "id int primary key not null," +
                "name text not null," +
                "start_week tinyint not null," +
                "end_week tinyint not null," +
                "lesson_time_num tinyint not null," +
                "teacher_name nchar(55)," +
                "classroom text not null," +
                "is_tiny_lesson int not null," +
                "is_first_half int," +
                "is_single_week_lesson int not null," +
                "is_odd_week_lesson int" +
                "};";
        db.execSQL(sql);
    }

    public boolean insertLesson(Lesson lesson){
        try{
            String sql = "";
            if (!lesson.isSingleWeekLesson() && !lesson.isTinyLesson()) {
                sql = "insert into " + databaseName + " {" +
                        "(name,start_week,end_week,lesson_time_num,teacher_name,classroom,is_tiny_lesson,is_single_week_lesson) " +
                        "values " +
                        "('" + lesson.getName() + "'," + lesson.getStartWeek() + "," + lesson.getEndWeek() + ",'" + lesson.getTeacherName()+ "'" +
                        ",'" + lesson.getClassroom() + "'," + lesson.isTinyLesson() + "," + lesson.isSingleWeekLesson() + ")";
                getWritableDatabase().execSQL(sql);
            }
        }catch (Exception e){
            Toast.makeText(context, "Wrong input info! Please check!", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "wrong input info! Exception!");
            return false;
        }

        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
