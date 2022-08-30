package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nimto0.adapters.BDPageAdapter;
import com.example.nimto0.utils.Constants;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

// SOurce : https://www.youtube.com/watch?v=Vxiy_h5hNII
public class MyOrderedThemeBirthDayInformationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    BDPageAdapter pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ordered_theme_birth_day_information);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitleText("Invitation Information");



        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);


        pageAdapter = new BDPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.e("Maine","Selected position:"+ position);

                viewPager.setCurrentItem(position);
                if (position == 0) {
                    setTitleText(Constants.MAIN_SCREEN);
                } else if ( position == 1) {
                    setTitleText(Constants.CONTACT_SCREEN);
                } else {
                    setTitleText(Constants.MEETINGPOINT_SCREEN);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setTitleText(String titleText) {
        getSupportActionBar().setTitle(titleText);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}