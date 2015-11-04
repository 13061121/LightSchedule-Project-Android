package com.leafli7.lightschedule.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Entity.Lesson;
import com.leafli7.lightschedule.Utils.Constant;

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

    private int curMode = 0;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        initialFindview();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        curMode = (int) bundle.get(MODE);
        switch (curMode) {
            case VIEW_MODE:
                setEditMode(false);
                break;
            case ADD_MODE:
            case MODIFY_MODE:
                setEditMode(true);
                break;
            default:
                Log.e(TAG, "Wrong mode!");
        }
        mToolbar.setTitle(getString(R.string.title_activity_add_lesson));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initialFindview() {
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
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
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

    private void updateLesson(Lesson lesson){

    }

    private void addLesson(Lesson lesson){
        Constant.weekSchedule.get(lesson.getDayOfWeek()).get(lesson.getLessonTimeNum()).add(lesson);
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
            setEditMode(false);
            menu.clear();
            finish();
//            getMenuInflater().inflate(R.menu.menu_add_lesson_view_mode, menu);
        }else if (id == R.id.action_add){
            addLesson(new Lesson("test room", 0, "test class", 0, 0, 0, "test teacher", 0));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
