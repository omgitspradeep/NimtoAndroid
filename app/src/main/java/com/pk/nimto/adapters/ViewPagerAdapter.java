package com.pk.nimto.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pk.nimto.MyOrderedThemeMarriageInformationActivity;
import com.pk.nimto.ui.birthdaydata.Fragment1;
import com.pk.nimto.ui.birthdaydata.Fragment2;
import com.pk.nimto.ui.birthdaydata.Fragment3;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    MyOrderedThemeMarriageInformationActivity mContext;
    public ViewPagerAdapter(@NonNull FragmentManager fm, MyOrderedThemeMarriageInformationActivity context) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new Fragment1(mContext);
        else if (position == 1)
            fragment = new Fragment2(mContext);
        else if (position == 2)
            fragment = new Fragment3(mContext);

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "One";
        else if (position == 1)
            title = "Two";
        else if (position == 2)
            title = "Three";
        return title;
    }

}
