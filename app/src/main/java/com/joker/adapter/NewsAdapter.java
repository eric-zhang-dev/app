package com.joker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joker.fragment.GifFragment;
import com.joker.fragment.HotFragment;
import com.joker.fragment.JokerFragment;
import com.joker.fragment.SearchFragment;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class NewsAdapter extends FragmentPagerAdapter {
    private HotFragment jokerFragment;
    private SearchFragment gifFragment;

    public NewsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (jokerFragment == null) {
                    jokerFragment = new HotFragment();
                }
                return jokerFragment;
            case 1:
                if (gifFragment == null) {
                    gifFragment = new SearchFragment();
                }
                return gifFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return new String[] { "实时热点", "新闻检索"}[position % 2].toUpperCase();
    }
}
