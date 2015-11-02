package com.leafli7.lightschedule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Entity.Lesson;
import com.leafli7.lightschedule.Entity.SingleLessonTime;
import com.leafli7.lightschedule.Utils.Constant;
import com.leafli7.lightschedule.View.LessonItemLayout;

import java.util.ArrayList;

/**
 * Created by xxcub on 2015/10/29.
 */
public class DayScheduleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SingleLessonTime> singleLessonTimes = new ArrayList<>();
    private int curAdapterDayOfWeek;
    private int curAdapterLessonTimeNum;

    public DayScheduleAdapter(Context context, int curAdapterDayOfWeek){
        this.context = context;
        this.curAdapterDayOfWeek = curAdapterDayOfWeek;
        singleLessonTimes = Constant.weekSchedule.get(curAdapterDayOfWeek);
    }


    @Override
    public int getCount() {
        return singleLessonTimes.size();
    }

    @Override
    public Object getItem(int position) {
        return singleLessonTimes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String[] lessonNum = Constant.LESSON_NUM;
        String[] lessonTime = Constant.LESSON_TIME;

        LinearLayout llMain = new LinearLayout(context);
        llMain = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.day_schedule_list_item, null);

        LinearLayout llSingleLessonTime = (LinearLayout) llMain.findViewById(R.id.llSingleLessonTime);
        ArrayList<Lesson> curLessones = singleLessonTimes.get(position);
        for (Lesson lesson:curLessones) {
            final LessonItemLayout lessonItemLayout = new LessonItemLayout(context, lesson.getId());
            ((TextView)lessonItemLayout.findViewById(R.id.tvLessonName)).setText(lesson.getName());
            ((TextView)lessonItemLayout.findViewById(R.id.tvLessonClassroom)).setText(lesson.getClassroom());
            if (lesson.isTinyLesson()){
                TextView tvTinyLesson = (TextView) lessonItemLayout.findViewById(R.id.tvTinyLesson);
                tvTinyLesson.setVisibility(View.VISIBLE);
                tvTinyLesson.setText(lesson.isFirstHalf() ? "前" : "后");
            }
            if (lesson.isSingleWeekLesson()){
                TextView tvSingleWeekLesson = (TextView) lessonItemLayout.findViewById(R.id.tvSingleWeekLesson);
                tvSingleWeekLesson.setVisibility(View.VISIBLE);
                tvSingleWeekLesson.setText(lesson.isOddWeekLesson() ? "单" : "双");
            }
            lessonItemLayout.setFocusable(true);
            lessonItemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "clicked " + lessonItemLayout.getLessonId(), Toast.LENGTH_SHORT).show();
                }
            });
            llSingleLessonTime.addView(lessonItemLayout);
        }


//        lessonItemLayout = new LessonItemLayout(context, 123);
//        lessonItemLayout.setFocusable(true);
//        llSingleLessonTime.addView(lessonItemLayout);

        TextView tvLessonNum = (TextView) llMain.findViewById(R.id.tvLessonNum);
        TextView tvLessonTime = (TextView) llMain.findViewById(R.id.tvLessonTime);

        tvLessonNum.setText(lessonNum[position]);
        tvLessonTime.setText(lessonTime[position]);

        return llMain;
    }
}
