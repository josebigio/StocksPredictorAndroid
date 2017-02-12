package com.josebigio.stockprediction.ui.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>MainAdapter</h1>
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    private List<android.support.v4.app.Fragment> fragmentList = new ArrayList<>();


    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(android.support.v4.app.Fragment fragment) {
        fragmentList.add(fragment);

    }
}
