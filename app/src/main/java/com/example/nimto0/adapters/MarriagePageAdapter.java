package com.example.nimto0.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.nimto0.ui.marriagedata.MarriageContactFragment;
import com.example.nimto0.ui.marriagedata.MarriageGalleryFragment;
import com.example.nimto0.ui.marriagedata.MarriageMainDataFragment;
import com.example.nimto0.ui.marriagedata.MarriageMeetingPointFragment;
import com.example.nimto0.ui.marriagedata.MarriageParentsFragment;
import com.example.nimto0.ui.marriagedata.MarriageTestimonialsFragment;

public class MarriagePageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public MarriagePageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MarriageContactFragment();
            case 1:
                return new MarriageGalleryFragment();
            case 2:
                return new MarriageMainDataFragment();
            case 3:
                return new MarriageMeetingPointFragment();
            case 4:
                return new MarriageParentsFragment();
            case 5:
                return new MarriageTestimonialsFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
