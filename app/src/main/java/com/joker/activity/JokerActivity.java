package com.joker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.joker.R;
import com.joker.adapter.JokerPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class JokerActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "段子", "gif笑话"};
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joker_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        JokerPagerAdapter mainPageAdapter = new JokerPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}
