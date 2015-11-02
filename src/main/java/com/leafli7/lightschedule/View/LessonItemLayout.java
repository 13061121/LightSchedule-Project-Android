package com.leafli7.lightschedule.View;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.lightschedule.R;

/**
 * Created by xxcub on 2015/10/29.
 */
public class LessonItemLayout extends RelativeLayout {
    private Context context;

    private int lessonId;

    public LessonItemLayout(Context context, int lessonId) {
        super(context);
        this.context = context;
        this.lessonId = lessonId;

        LayoutInflater.from(context).inflate(R.layout.lesson_item_layout, this, true);
    }

    public int getLessonId() {
        return lessonId;
    }
}
