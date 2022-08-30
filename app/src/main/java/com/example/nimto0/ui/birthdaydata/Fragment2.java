package com.example.nimto0.ui.birthdaydata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nimto0.MyOrderedThemeMarriageInformationActivity;
import com.example.nimto0.R;
import com.example.nimto0.utils.Constants;

public class Fragment2 extends Fragment {

    MyOrderedThemeMarriageInformationActivity mContext;
    public Fragment2(MyOrderedThemeMarriageInformationActivity context) {
        this.mContext = context;
    }
    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.fragment2,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
