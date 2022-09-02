package com.pk.nimto.ui.birthdaydata;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pk.nimto.MyOrderedThemeMarriageInformationActivity;
import com.pk.nimto.R;


public class Fragment3 extends Fragment {

    MyOrderedThemeMarriageInformationActivity mContext;
    public Fragment3(MyOrderedThemeMarriageInformationActivity context) {
        this.mContext = context;
    }

    public Fragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}