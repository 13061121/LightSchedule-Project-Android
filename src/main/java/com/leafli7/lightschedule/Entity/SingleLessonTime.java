package com.leafli7.lightschedule.Entity;

import java.util.ArrayList;

/**
 * Created by xxcub on 2015/10/29.
 */
public class SingleLessonTime {
    public ArrayList<Lesson> lessons = new ArrayList<>();

    void SingleLessonTime(){
        lessons.add(new Lesson());
    }
}
