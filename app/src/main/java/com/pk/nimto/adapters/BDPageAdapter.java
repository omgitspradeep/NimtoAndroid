package com.pk.nimto.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pk.nimto.ui.birthdaydata.Fragment1;
import com.pk.nimto.ui.birthdaydata.Fragment2;
import com.pk.nimto.ui.birthdaydata.Fragment3;

public class BDPageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public BDPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
