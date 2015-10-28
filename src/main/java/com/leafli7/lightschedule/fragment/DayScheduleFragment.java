package com.leafli7.lightschedule.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lightschedule.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayScheduleFragment extends Fragment {

    String TAG = getClass().getSimpleName();

    public DayScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_day_schedule, container, false);
        return inflater.inflate(R.layout.day_schedule_list_item, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        LinearLayout mainLl = (LinearLayout) view.findViewById(R.id.mainLl);
        mainLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "linear layout clicked!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "linear layout clicked!");
            }
        });
        Log.e(TAG, "asd");
    }

}
