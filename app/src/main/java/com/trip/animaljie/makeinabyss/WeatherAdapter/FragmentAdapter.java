package com.trip.animaljie.makeinabyss.WeatherAdapter;



import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragments;
    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        mfragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return mfragments.get(position);
    }

    @Override
    public int getCount() {
        return mfragments.size();
    }
}
