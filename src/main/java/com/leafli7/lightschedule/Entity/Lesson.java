package com.leafli7.lightschedule.Entity;

/**
 * @author leafli7
 * Created by xxcub on 2015/10/29.
 */
public class Lesson {
    private int id;
    private String name;
    private int startWeek;
    private int endWeek;
    private int lessonTimeNum;  //上课节数
    private String teacherName;
    private String classroom;
    private boolean isTinyLesson;   //是否为半节课的小课
    private boolean isFirstHalf;    //是否为前半小节课 false则为后半节
    private boolean isSingleWeekLesson; //是否为单双周周课程
    private boolean isOddWeekLesson; //是否为奇数周课程

    public Lesson(String classroom, int id, String name, int startWeek, int endWeek, int lessonTimeNum, String teacherName) {
        this.classroom = classroom;
        this.id = id;
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.lessonTimeNum = lessonTimeNum;
        this.teacherName = teacherName;
        this.isTinyLesson = false;
        this.isFirstHalf = false;
        this.isSingleWeekLesson = false;
        this.isOddWeekLesson = false;
    }

    public Lesson(String classroom, int id, String name, int startWeek, int endWeek, int lessonTimeNum, String teacherName,
                  boolean isTinyLesson, boolean isFirstHalf) {
        this.classroom = classroom;
        this.id = id;
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.lessonTimeNum = lessonTimeNum;
        this.teacherName = teacherName;
        this.isTinyLesson = isTinyLesson;
        this.isFirstHalf = isFirstHalf;
        this.isSingleWeekLesson = false;
        this.isOddWeekLesson = false;
    }

    public Lesson(String classroom, int id, String name, int startWeek, int endWeek, int lessonTimeNum, String teacherName,
                  boolean isTinyLesson, boolean isFirstHalf, boolean isSingleWeekLesson, boolean isOddWeekLesson) {
        this.classroom = classroom;
        this.id = id;
        this.name = name;
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.lessonTimeNum = lessonTimeNum;
        this.teacherName = teacherName;
        this.isTinyLesson = isTinyLesson;
        this.isFirstHalf = isFirstHalf;
        this.isSingleWeekLesson = isSingleWeekLesson;
        this.isOddWeekLesson = isOddWeekLesson;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public int getLessonTimeNum() {
        return lessonTimeNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getClassroom() {
        return classroom;
    }

    public boolean isTinyLesson() {
        return isTinyLesson;
    }

    public boolean isFirstHalf() {
        return isFirstHalf;
    }

    public boolean isSingleWeekLesson() {
        return isSingleWeekLesson;
    }

    public boolean isOddWeekLesson() {
        return isOddWeekLesson;
    }
}
