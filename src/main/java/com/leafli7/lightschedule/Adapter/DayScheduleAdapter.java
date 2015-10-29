package com.leafli7.lightschedule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lightschedule.R;
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

    public DayScheduleAdapter(Context context, int curAdapterDayOfWeek){
        this.context = context;
        this.curAdapterDayOfWeek = curAdapterDayOfWeek;
        for (int i = 0; i < Constant.LESSEN_TIME_ACCOUNT; i++) {
            singleLessonTimes.add(new SingleLessonTime());
        }
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
        LessonItemLayout lessonItemLayout = new LessonItemLayout(context, 123);
        lessonItemLayout.setFocusable(true);
        llSingleLessonTime.addView(lessonItemLayout);

        lessonItemLayout = new LessonItemLayout(context, 123);
        lessonItemLayout.setFocusable(true);
        llSingleLessonTime.addView(lessonItemLayout);

        TextView tvLessonNum = (TextView) llMain.findViewById(R.id.tvLessonNum);
        TextView tvLessonTime = (TextView) llMain.findViewById(R.id.tvLessonTime);

        tvLessonNum.setText(lessonNum[position]);
        tvLessonTime.setText(lessonTime[position]);

        return llMain;
    }
}
