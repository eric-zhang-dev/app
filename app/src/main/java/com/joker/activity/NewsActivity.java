package com.joker.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.joker.R;
import com.joker.adapter.JokerPagerAdapter;
import com.joker.adapter.NewsAdapter;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class NewsActivity extends FragmentActivity {
    private  String[] CONTENT = new String[] { "实时热点", "新闻检索"};
    private ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joker_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        NewsAdapter mainPageAdapter = new NewsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }
}
