package com.leafli7.lightschedule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Entity.SingleLessonTime;
import com.leafli7.lightschedule.Utils.Constant;
import com.leafli7.lightschedule.fragment.DayScheduleFragment;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by xxcub on 2015/10/29.
 */
public class DayScheduleAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SingleLessonTime> singleLessonTimes = new ArrayList<>();

    public DayScheduleAdapter(Context context){
        this.context = context;
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
        String[] lessonNum = {"一", "二", "三", "四", "五", "六"};
        String[] lessonTime = {"8:00\n9:45", "10:00\n11:45", "14:00\n15:45", "16:00\n17:45", "18:00\n19:45", "20:00\n21:45"};

        LinearLayout ll = new LinearLayout(context);
        ll = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.day_schedule_list_item, null);

        TextView tvLessonNum = (TextView) ll.findViewById(R.id.tvLessonNum);
        TextView tvLessonTime = (TextView) ll.findViewById(R.id.tvLessonTime);

        tvLessonNum.setText(lessonNum[position]);
        tvLessonTime.setText(lessonTime[position]);

        return ll;
    }
}
