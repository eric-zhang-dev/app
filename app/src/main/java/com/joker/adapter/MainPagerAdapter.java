package com.joker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joker.fragment.Four;
import com.joker.fragment.One;
import com.joker.fragment.Three;
import com.joker.fragment.Two;

/**
 * Created by zhangyue on 2016/5/14.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private One one;
    private Two two;
    private Three three;
    private Four four;

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (one == null) {
                    one = new One();
                }
                return one;
            case 1:
                if (two == null) {
                    two = new Two();
                }
                return two;
            case 2:
                if (three == null) {
                    three = new Three();
                }
                return three;
            case 3:
                if (four == null) {
                    four = new Four();
                }
                return four;
        }
        return new One();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
