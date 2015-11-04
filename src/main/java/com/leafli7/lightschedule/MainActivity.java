package com.leafli7.lightschedule;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lightschedule.R;
import com.leafli7.lightschedule.Adapter.DayScheduleAdapter;
import com.leafli7.lightschedule.Entity.DaySchedule;
import com.leafli7.lightschedule.Entity.WeekSchedule;
import com.leafli7.lightschedule.Fragment.DayScheduleFragment;
import com.leafli7.lightschedule.Utils.Constant;
import com.leafli7.lightschedule.Utils.OwnDbHelper;

import java.util.HashMap;

import SlidingTabs.SlidingTabLayout;

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
    SlidingTabLayout mSlidingTabLayout;
    ViewPager mViewPager;
    ViewPager.OnPageChangeListener mSlidingTabLayoutOnPageChangeListener;
    Toolbar mToolbar;
    OwnDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        setContentView(R.layout.activity_main);

        initialDatabase();
        Constant.initial(this);
        initialFindView();
        initialTabAndToolbar();
        initialNav();
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
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(7); // tabcachesize (=tabcount for better performance)
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
    }

    private void initialTabAndToolbar() {
        mToolbar.setTitle(getString(R.string.title_activity_main));
        setSupportActionBar(mToolbar);
        // use own style rules for tab layout
        mSlidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);

        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.tab_indicator_color));
        mSlidingTabLayout.setDistributeEvenly(true);
        MainTabs mainTabs = new MainTabs();
        mViewPager.setAdapter(mainTabs);
        mViewPager.setCurrentItem(Constant.CURRENT_DAY_OF_WEEK);   //leafli7 设置初始week选择
        mSlidingTabLayout.setViewPager(mViewPager);

        // Tab events
        mSlidingTabLayoutOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "page : " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        if (mSlidingTabLayout != null) {
            mSlidingTabLayout.setOnPageChangeListener(mSlidingTabLayoutOnPageChangeListener);
        }
    }

    private void initialNav() {
        // Click events for Navigation Drawer
        LinearLayout navButton = (LinearLayout) findViewById(R.id.txtNavButton);
        navButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // close drawer if you want
                /*if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
                    mDrawerLayout.closeDrawers();
                }*/
                Toast.makeText(v.getContext(), "navitem clicked", Toast.LENGTH_SHORT).show();

                // update loaded Views if you want
                //mViewPager.getAdapter().notifyDataSetChanged();
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

    /**
     * The {@link android.support.v4.view.PagerAdapter} used to display pages in this sample.
     * The individual pages are simple and just display two lines of text. The important section of
     * this class is the {@link #getPageTitle(int)} method which controls what is displayed in the
     * {@link SlidingTabLayout}.
     */
    class MainTabs extends PagerAdapter {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        HashMap<String, DayScheduleFragment> fragmentHashMap = new HashMap<String, DayScheduleFragment>();

        SparseArray<View> views = new SparseArray<View>();

        public MainTabs(){
            int i = 0;
            for (String day : week){
                DayScheduleFragment curFragment = new DayScheduleFragment();
                curFragment.setCurFragmentDayOfWeek(i);
                fragmentHashMap.put(day, curFragment);
                getSupportFragmentManager().beginTransaction().add(curFragment, day).commit();
                i++;
            }
        }


        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return week.length;
        }

        /**
         * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
         * same object as the {@link View} added to the {@link ViewPager}.
         */
        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we construct one using the position value, but for real application the title should
         * refer to the item's contents.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return week[position].substring(0, 3);
        }

        /**
         * Instantiate the {@link View} which should be displayed at {@code position}. Here we
         * inflate a layout from the apps resources and then change the text view to signify the position.
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View viewTest = fragmentHashMap.get(week[position]).getView();
            container.addView(viewTest);
            views.put(position, viewTest);
            return viewTest;
        }

        /**
         * Destroy the item from the {@link ViewPager}. In our case this is simply removing the
         * {@link View}.
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            views.remove(position);
        }

        @Override
        public void notifyDataSetChanged() {
            int position = 0;
            for (DayScheduleFragment dayScheduleFragment : fragmentHashMap.values()){
                ((DayScheduleAdapter)dayScheduleFragment.getLvDayScheduleListView().getAdapter()).notifyDataSetChanged();
            }
            super.notifyDataSetChanged();
        }

    }
}
