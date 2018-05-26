package com.trip.animaljie.makeinabyss;

import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trip.animaljie.makeinabyss.WeatherAdapter.FragmentAdapter;
import com.trip.animaljie.makeinabyss.WeatherAdapter.Weather_fragment1;
import com.trip.animaljie.makeinabyss.WeatherAdapter.Weather_fragment2;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Weather_fragment1());
        fragments.add(new Weather_fragment2());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(),fragments);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentAdapter);
    }
}
