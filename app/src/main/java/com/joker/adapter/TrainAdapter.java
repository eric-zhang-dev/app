package com.joker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joker.fragment.TrainNumberFragment;
import com.joker.fragment.TrainStationFragment;

/**
 * Created by zhangyue on 2016/5/30.
 */
public class TrainAdapter extends FragmentPagerAdapter {
    private TrainNumberFragment trainNumberFragment;
    private TrainStationFragment trainStationFragment;
    public TrainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (trainStationFragment == null) {
                    trainStationFragment = new TrainStationFragment();
                }
                return trainStationFragment;
            case 1:
                if (trainNumberFragment == null) {
                    trainNumberFragment = new TrainNumberFragment();
                }
                return trainNumberFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return new String[] { "站站查询", "车次查询"}[position % 2].toUpperCase();
    }
}
