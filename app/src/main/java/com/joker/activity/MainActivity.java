package com.joker.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joker.R;
import com.joker.adapter.MainPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private LinearLayout tb0;
    private TextView tv0;
    private ImageView icon00, icon01;

    private LinearLayout tb1;
    private TextView tv1;
    private ImageView icon10, icon11;

    private LinearLayout tb2;
    private TextView tv2;
    private ImageView icon20, icon21;

    private LinearLayout tb3;
    private TextView tv3;
    private ImageView icon30, icon31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }


    private void findViews() {

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tb0 = (LinearLayout) findViewById(R.id.tab0);
        icon00 = (ImageView) findViewById(R.id.icon00);
        icon01 = (ImageView) findViewById(R.id.icon01);
        tv0 = (TextView) findViewById(R.id.tv0);

        tb1 = (LinearLayout) findViewById(R.id.tab1);
        icon10 = (ImageView) findViewById(R.id.icon10);
        icon11 = (ImageView) findViewById(R.id.icon11);
        tv1 = (TextView) findViewById(R.id.tv1);

        tb2 = (LinearLayout) findViewById(R.id.tab2);
        icon20 = (ImageView) findViewById(R.id.icon20);
        icon21 = (ImageView) findViewById(R.id.icon21);
        tv2 = (TextView) findViewById(R.id.tv2);

        tb3 = (LinearLayout) findViewById(R.id.tab3);
        icon30 = (ImageView) findViewById(R.id.icon30);
        icon31 = (ImageView) findViewById(R.id.icon31);
        tv3 = (TextView) findViewById(R.id.tv3);


    }

    public Object evaluate(float fraction, Object startValue, Object endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                (int) ((startB + (int) (fraction * (endB - startB))));
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void changeTabColor(int position, float offset) {
        int c0 = (Integer) evaluate(offset, Color.parseColor("#05bf8d"), Color.parseColor("#999999"));
        int c1 = (Integer) evaluate(offset, Color.parseColor("#999999"), Color.parseColor("#05bf8d"));
        switch (position) {
            case 0:
                tv0.setTextColor(c0);
                icon00.setAlpha(1 - offset);
                icon01.setAlpha(offset);

                tv1.setTextColor(c1);
                icon11.setAlpha(1 - offset);
                icon10.setAlpha(offset);
                break;
            case 1:
                tv1.setTextColor(c0);
                icon10.setAlpha(1 - offset);
                icon11.setAlpha(offset);

                tv2.setTextColor(c1);
                icon21.setAlpha(1 - offset);
                icon20.setAlpha(offset);

                break;
            case 2:
                tv2.setTextColor(c0);
                icon20.setAlpha(1 - offset);
                icon21.setAlpha(offset);

                tv3.setTextColor(c1);
                icon31.setAlpha(1 - offset);
                icon30.setAlpha(offset);

                break;
            default:
                break;

        }

    }

    private void setPageChanged(int position) {
        tv0.setTextColor(Color.parseColor("#999999"));
        icon00.setAlpha(0.0f);
        icon01.setAlpha(1.0f);
        tv1.setTextColor(Color.parseColor("#999999"));
        icon10.setAlpha(0.0f);
        icon11.setAlpha(1.0f);
        tv2.setTextColor(Color.parseColor("#999999"));
        icon20.setAlpha(0.0f);
        icon21.setAlpha(1.0f);
        tv3.setTextColor(Color.parseColor("#999999"));
        icon30.setAlpha(0.0f);
        icon31.setAlpha(1.0f);

        switch (position) {
            case 0:
                tv0.setTextColor(Color.parseColor("#05bf8d"));
                icon00.setAlpha(1.0f);
                icon01.setAlpha(0.0f);
                break;
            case 1:
                tv1.setTextColor(Color.parseColor("#05bf8d"));
                icon10.setAlpha(1.0f);
                icon11.setAlpha(0.0f);
                break;
            case 2:
                tv2.setTextColor(Color.parseColor("#05bf8d"));
                icon20.setAlpha(1.0f);
                icon21.setAlpha(0.0f);
                break;
            case 3:
                tv0.setTextColor(Color.parseColor("#05bf8d"));
                icon30.setAlpha(1.0f);
                icon31.setAlpha(0.0f);
                break;

        }
    }

    public void initViews() {
        viewPager.setOnPageChangeListener(this);
        MainPagerAdapter mainPageAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPageAdapter.notifyDataSetChanged();
        viewPager.setAdapter(mainPageAdapter);
        viewPager.setOffscreenPageLimit(4);
        tb0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0, false);

            }
        });
        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1, false);
            }
        });
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2, false);
            }
        });
        tb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3, false);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            changeTabColor(position, positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setPageChanged(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}