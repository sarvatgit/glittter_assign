package com.example.test;

import android.content.Context;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    int []res;
    ArrayList<Integer> time;
    ArrayList<String> cname;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs,int []res, ArrayList<Integer> time,ArrayList<String> cname) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.res=res;
        this.time=time;
        this.cname=cname;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PieFragment pie = new PieFragment(res);
                return pie;
            case 1:
                LineFragment line = new LineFragment(time,cname);
                return line;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}