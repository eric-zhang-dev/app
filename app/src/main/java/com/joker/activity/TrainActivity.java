package com.joker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.joker.R;
import com.joker.adapter.TrainAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by zhangyue on 2016/5/30.
 */
public class TrainActivity extends FragmentActivity {
    private static final String[] CONTENT = new String[] { "站站查询", "车次查询"};
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joker_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TrainAdapter trainAdapter = new TrainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(trainAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}
