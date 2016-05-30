package com.joker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joker.fragment.GifFragment;
import com.joker.fragment.JokerFragment;

/**
 * Created by zhangyue on 2016/5/27.
 */
public class JokerPagerAdapter extends FragmentPagerAdapter {
    private JokerFragment jokerFragment;
    private GifFragment gifFragment;

    public JokerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (jokerFragment == null) {
                    jokerFragment = new JokerFragment();
                }
                return jokerFragment;
            case 1:
                if (gifFragment == null) {
                    gifFragment = new GifFragment();
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
        return new String[] { "段子", "gif笑话"}[position % 2].toUpperCase();
    }
}
