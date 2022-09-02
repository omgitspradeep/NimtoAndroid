package com.pk.nimto.ui.birthdaydata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pk.nimto.MyOrderedThemeMarriageInformationActivity;
import com.pk.nimto.R;

public class Fragment1 extends Fragment {

    MyOrderedThemeMarriageInformationActivity mContext;
    public Fragment1(MyOrderedThemeMarriageInformationActivity context) {
        this.mContext = context;
    }

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated m-ethod stub
        return inflater.inflate(R.layout.fragment1,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
