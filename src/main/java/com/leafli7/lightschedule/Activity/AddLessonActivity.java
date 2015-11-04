package com.leafli7.lightschedule.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Entity.Lesson;
import com.leafli7.lightschedule.Utils.Constant;
import com.leafli7.lightschedule.Utils.OwnDbHelper;

import java.util.ArrayList;

public class AddLessonActivity extends AppCompatActivity {
    private String TAG = Constant.TAG + getClass().getSimpleName();
    public static final String MODE = "MODE";
    public static final int VIEW_MODE = 1;
    public static final int MODIFY_MODE = 2;
    public static final int ADD_MODE = 3;

    private Toolbar mToolbar;
    private EditText mEtLessonTitleEditText;
    private EditText mEtClassroomEditText;
    private EditText mEtTeacherNameEditText;
    private Spinner mSpLessonTimeNumSpinner;
    private Spinner mSpDayOfWeekSpinner;
    private Spinner mSpStartWeekSpinner;
    private Spinner mSpEndWeekSpinner;
    private CheckBox mCbIsTinyLessonCheckBox;
    private RadioButton mRbFirstRadioButton;
    private RadioButton mRbSecondRadioButton;
    private RadioGroup mRgIsTinyLessonRadioGroup;
    private CheckBox mCbIsSingleWeekLessonCheckBox;
    private RadioButton mRbOddRadioButton;
    private RadioButton mRbEvenRadioButton;
    private RadioGroup mRgIsSingleWeekLessonRadioGroup;
    private Menu menu;

    private int curMode = 0;
    private Lesson curLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        initialFindView();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        curLesson = null;
        curMode = (int) bundle.get(MODE);
        switch (curMode) {
            case VIEW_MODE:
                curLesson = bundle.getParcelable("lesson");
                initialWidgetText();
                setEditMode(false);
                mToolbar.setTitle(R.string.view_lesson);
                break;
            case ADD_MODE:
                mToolbar.setTitle(R.string.title_activity_add_lesson);
            case MODIFY_MODE:
                mToolbar.setTitle(R.string.modify_lesson);
                setEditMode(true);
                break;
            default:
                Log.e(TAG, "Wrong mode!");
        }
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initialFindView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mEtLessonTitleEditText = (EditText) findViewById(R.id.etLessonTitle);
        mEtClassroomEditText = (EditText) findViewById(R.id.etClassroom);
        mEtTeacherNameEditText = (EditText) findViewById(R.id.etTeacherName);
        mSpLessonTimeNumSpinner = (Spinner) findViewById(R.id.spLessonTimeNum);
        mSpDayOfWeekSpinner = (Spinner) findViewById(R.id.spDayOfWeek);
        mSpStartWeekSpinner = (Spinner) findViewById(R.id.spStartWeek);
        mSpEndWeekSpinner = (Spinner) findViewById(R.id.spEndWeek);
        mCbIsTinyLessonCheckBox = (CheckBox) findViewById(R.id.cbIsTinyLesson);
        mRbFirstRadioButton = (RadioButton) findViewById(R.id.rbFirst);
        mRbSecondRadioButton = (RadioButton) findViewById(R.id.rbSecond);
        mRgIsTinyLessonRadioGroup = (RadioGroup) findViewById(R.id.rgIsTinyLesson);
        mCbIsSingleWeekLessonCheckBox = (CheckBox) findViewById(R.id.cbIsSingleWeekLesson);
        mRbOddRadioButton = (RadioButton) findViewById(R.id.rbOdd);
        mRbEvenRadioButton = (RadioButton) findViewById(R.id.rbEven);
        mRgIsSingleWeekLessonRadioGroup = (RadioGroup) findViewById(R.id.rgIsSingleWeekLesson);

        mCbIsTinyLessonCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRgIsTinyLessonRadioGroup.setVisibility(View.VISIBLE);
                    mRbFirstRadioButton.setChecked(true);
                } else {
                    mRgIsTinyLessonRadioGroup.setVisibility(View.INVISIBLE);
                }
            }
        });
        mCbIsSingleWeekLessonCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRgIsSingleWeekLessonRadioGroup.setVisibility(View.VISIBLE);
                    mRbOddRadioButton.setChecked(true);
                } else {
                    mRgIsSingleWeekLessonRadioGroup.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initialWidgetText() {
        mEtLessonTitleEditText.setText(curLesson.getName());
        mEtClassroomEditText.setText(curLesson.getClassroom());
        mEtTeacherNameEditText.setText(curLesson.getTeacherName());
        if (curLesson.isTinyLesson()){
            mCbIsTinyLessonCheckBox.setChecked(true);
            if (curLesson.isFirstHalf()){
                mRbFirstRadioButton.setChecked(true);
            }else {
                mRbSecondRadioButton.setChecked(true);
            }
        }

        if (curLesson.isSingleWeekLesson()){
            mCbIsSingleWeekLessonCheckBox.setChecked(true);
            if (curLesson.isOddWeekLesson()){
                mRbOddRadioButton.setChecked(true);
            }else {
                mRbEvenRadioButton.setChecked(true);
            }
        }
    }

    public void setEditMode(boolean isEditable) {
        mEtLessonTitleEditText.setEnabled(isEditable);
        mEtClassroomEditText.setEnabled(isEditable);
        mEtTeacherNameEditText.setEnabled(isEditable);
        mSpLessonTimeNumSpinner.setEnabled(isEditable);
        mSpDayOfWeekSpinner.setEnabled(isEditable);
        mSpStartWeekSpinner.setEnabled(isEditable);
        mSpEndWeekSpinner.setEnabled(isEditable);
        mCbIsTinyLessonCheckBox.setEnabled(isEditable);
        mRbFirstRadioButton.setEnabled(isEditable);
        mRbSecondRadioButton.setEnabled(isEditable);
        mRgIsTinyLessonRadioGroup.setEnabled(isEditable);
        mCbIsSingleWeekLessonCheckBox.setEnabled(isEditable);
        mRbOddRadioButton.setEnabled(isEditable);
        mRbEvenRadioButton.setEnabled(isEditable);
        mRgIsSingleWeekLessonRadioGroup.setEnabled(isEditable);

        if (!isEditable) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    private void updateCurLesson(){
        curLesson = new Lesson(mEtClassroomEditText.getText().toString(), 0, mEtLessonTitleEditText.getText().toString(), 0, 0, 0, mEtTeacherNameEditText.getText().toString(), 0);
        curLesson.setIsTinyLesson(mCbIsTinyLessonCheckBox.isChecked());
        if (mCbIsTinyLessonCheckBox.isChecked()){
            if (mRbFirstRadioButton.isChecked()){
                curLesson.setIsFirstHalf(true);
            }else {
                curLesson.setIsFirstHalf(false);
            }
        }
        curLesson.setIsSingleWeekLesson(mCbIsSingleWeekLessonCheckBox.isChecked());
        if (mCbIsSingleWeekLessonCheckBox.isChecked()){
            if (mRbOddRadioButton.isChecked()){
                curLesson.setIsOddWeekLesson(true);
            }else {
                curLesson.setIsOddWeekLesson(false);
            }
        }
    }

    private void addLesson(){
        updateCurLesson();
        Constant.weekSchedule.get(curLesson.getDayOfWeek()).get(curLesson.getLessonTimeNum()).add(curLesson);
        OwnDbHelper dbHelper = new OwnDbHelper(this);
        dbHelper.insertLesson(curLesson);
    }

    private void modifyLesson(){
        OwnDbHelper dbHelper = new OwnDbHelper(this);
        dbHelper.deleteLesson(curLesson.getId());
        ArrayList<Lesson> lessons = Constant.weekSchedule.get(curLesson.getDayOfWeek()).get(curLesson.getLessonTimeNum());
        for (int i = 0; i < lessons.size(); i++){
            if (lessons.get(i).getId() == curLesson.getId()){
                lessons.remove(i);
                break;
            }
        }
        updateCurLesson();
        dbHelper.insertLesson(curLesson);
        Constant.weekSchedule.get(curLesson.getDayOfWeek()).get(curLesson.getLessonTimeNum()).add(curLesson);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        switch (curMode) {
            case ADD_MODE:
                getMenuInflater().inflate(R.menu.menu_add_lesson, menu);
                break;
            case VIEW_MODE:
                getMenuInflater().inflate(R.menu.menu_add_lesson_view_mode, menu);
                break;
            case MODIFY_MODE:
                getMenuInflater().inflate(R.menu.menu_add_lesson_modify, menu);
                break;
            default:
                Log.e(TAG, "wrong mode in menu created!");
        }
        this.menu = menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            Log.e(TAG, "Home clicked");
            this.finish();
        }else if (id == R.id.action_modify){
            setEditMode(true);
            menu.clear();
            mToolbar.setTitle(R.string.modify_lesson);
            getMenuInflater().inflate(R.menu.menu_add_lesson_modify, menu);

        }else if (id == R.id.action_finish_modify){
            modifyLesson();
            finish();
//            getMenuInflater().inflate(R.menu.menu_add_lesson_view_mode, menu);
        }else if (id == R.id.action_add){
            addLesson();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //考虑是不是为了防止误触关闭back键功能
        //或者存储back时的状态
        //双击返回
        finish();
    }

}
