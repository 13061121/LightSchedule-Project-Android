package com.leafli7.lightschedule;


import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Entity.WeekSchedule;
import com.leafli7.lightschedule.Fragment.MainScheduleFragment;
import com.leafli7.lightschedule.Utils.Constant;
import com.leafli7.lightschedule.Utils.OwnDbHelper;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author leafli7
 */
public class MainActivity extends AppCompatActivity {
    /*
    TODO : 添加课程时应该列出已有课程,若没有再开新activity添加.
     */
    String TAG = Constant.TAG + getClass().getSimpleName();

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    OwnDbHelper dbHelper;

    FragmentManager fragmentManager;
    HashMap<String, Fragment> fragmentHashMap;

    public static final String mainScheduleFragment = "mainScheduleFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragmentHashMap = new HashMap<String, Fragment>();
        fragmentHashMap.put(mainScheduleFragment, new MainScheduleFragment());
        fragmentManager.beginTransaction().add(R.id.main_container, fragmentHashMap.get(mainScheduleFragment)).commit();


        initialDatabase();
        initialStatue();
        Constant.initial(this);
        initialFindView();
        initialToolbar();
        initialNav();
    }

    private void initialStatue() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.colorMainDark));
        }
    }

    private void initialDatabase(){
        dbHelper = new OwnDbHelper(this);

//        try {
//            String del = "drop table " + Constant.DbScheduleTableName + ";";
//            dbHelper.getWritableDatabase().execSQL(del);
//        }catch (Exception e){
//            Log.e(TAG, "del exception");
//        }
        WeekSchedule weekSchedule = Constant.weekSchedule;
//        dbHelper.insertLesson(new Lesson("主201", 0, "计算机工程中最优化方法", 0, 14, 0, "冷彪", Lesson.DAY_MONDAY));
//        dbHelper.insertLesson(new Lesson("(一)301", 0, "编译技术", 0, 12, 1, "史晓华", Lesson.DAY_MONDAY));
//        dbHelper.insertLesson(new Lesson("-", 0, "剑术", 0, 16, 2, "张洋", Lesson.DAY_MONDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "C2", 0, 12, 3, "尹宝林", Lesson.DAY_MONDAY));
//        dbHelper.insertLesson(new Lesson("(一)301", 0, "数据库", 0, 12, 1, "刘瑞", Lesson.DAY_TUESDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "软件工程基础", 0, 16, 2, "姚淑珍", Lesson.DAY_TUESDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "Android", 0, 8, 3, "张炯", Lesson.DAY_TUESDAY));
//        dbHelper.insertLesson(new Lesson("F201", 0, "网络系统运行管理及性能分析", 1, 9, 4, "栾钟治", Lesson.DAY_TUESDAY));
//        dbHelper.insertLesson(new Lesson("(一)301", 0, "编译技术", 0, 12, 1, "史晓华", Lesson.DAY_WEDNESDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "RUBY", 0, 8, 2, "沃天宇", Lesson.DAY_WEDNESDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "大数据", 0, 12, 1, "吴文俊", Lesson.DAY_THURSDAY));
//        dbHelper.insertLesson(new Lesson("(一)301", 0, "数据库", 0, 12, 2, "刘瑞", Lesson.DAY_THURSDAY));
//        dbHelper.insertLesson(new Lesson("主349", 0, "数学建模", 0, 16, 0, "武三星", Lesson.DAY_FRIDAY));
//        dbHelper.insertLesson(new Lesson("主203", 0, "移动计算导论", 0, 12, 3, "牛建伟,盛浩", Lesson.DAY_FRIDAY));
//        Cursor cursor = dbHelper.querySchedule();
//        while (cursor.moveToNext()){
//            for (String name:cursor.getColumnNames()){
//                Log.e(TAG, name);
//            }
//        }
//        cursor.close();
    }

    private void initialFindView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorMainDark));
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void initialToolbar() {
        mToolbar.setTitle(getString(R.string.title_activity_main));
        setSupportActionBar(mToolbar);
        // use own style rules for tab layout
    }

    private void initialNav() {
        // Click events for Navigation Drawer
        LinearLayout navButton = (LinearLayout) findViewById(R.id.txtNavButton);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "nav main schedule clicked!", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragmentHashMap.get(mainScheduleFragment)).commit();
                mDrawerLayout.closeDrawers();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

}
