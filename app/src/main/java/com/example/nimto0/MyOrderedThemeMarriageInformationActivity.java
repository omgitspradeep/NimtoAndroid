package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nimto0.adapters.BDPageAdapter;
import com.example.nimto0.adapters.MarriagePageAdapter;
import com.example.nimto0.adapters.ViewPagerAdapter;
import com.example.nimto0.models.marriagedata.AllMarriageData;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyOrderedThemeMarriageInformationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MarriagePageAdapter pageAdapter;
    TabItem tabContact, tabGallery, tabMainData, tabMeetingPoint, tabParents, tabTestimonials;

    AllMarriageData allMarriageData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ordered_book_information);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitleText(Constants.CONTACT_SCREEN);

        init();

    }

    private void setTitleText(String titleText) {
        getSupportActionBar().setTitle(titleText);
    }


    private void init() {
        allMarriageData = ResourceManager.getAllMarriageDataOfselectedOrder();
        Log.d("Maine", allMarriageData.toString());



        tabContact = findViewById(R.id.contact_marriage);
        tabGallery = findViewById(R.id.gallery_marriage);
        tabMainData = findViewById(R.id.maindata_marriage);
        tabMeetingPoint = findViewById(R.id.meeting_point_marriage);
        tabParents = findViewById(R.id.parents_marriage);
        tabTestimonials = findViewById(R.id.testimonials_marriage);
        viewPager = findViewById(R.id.viewPager_Marriage);
        tabLayout = findViewById(R.id.tablayoutMarriage);


        pageAdapter = new MarriagePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.e("Maine","Selected position:"+ position);

                viewPager.setCurrentItem(position);
                switch (position){
                    case 0:
                        setTitleText(Constants.CONTACT_SCREEN);
                        break;
                    case 1:
                        setTitleText(Constants.GALLERY_SCREEN);
                        break;
                    case 2:
                        setTitleText(Constants.MAIN_SCREEN);
                        break;
                    case 3:
                        setTitleText(Constants.MEETINGPOINT_SCREEN);
                        break;
                    case 4:
                        setTitleText(Constants.PARENTS_SCREEN);
                        break;
                    case 5:
                        setTitleText(Constants.TESTIMONIALS_SCREEN);
                        break;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //call super
        super.onActivityResult(requestCode, resultCode, data);
    }


}