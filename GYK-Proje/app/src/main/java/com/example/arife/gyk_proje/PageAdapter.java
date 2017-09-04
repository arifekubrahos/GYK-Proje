package com.example.arife.gyk_proje;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Arife on 30.08.2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ArrayList<String> tabTitles = new ArrayList<String>();

    public void addFragment(Fragment fragment, String title){
        this.fragments.add(fragment);
        this.tabTitles.add(title);
    }

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
