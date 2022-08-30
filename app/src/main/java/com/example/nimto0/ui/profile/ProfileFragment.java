package com.example.nimto0.ui.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nimto0.ChangePasswordActivity;
import com.example.nimto0.R;
import com.example.nimto0.UpdateProfile;
import com.example.nimto0.databinding.FragmentProfileBinding;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.models.CustomerProfileModel;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.WebServices;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {


    ImageView profilePic;
    TextView profileName, email, phone, country, address;
    Button changePassword, updateProfile;
    SwipeRefreshLayout swipeRefreshLayout;
    CustomerProfileModel currentUser;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        profileName = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        phone = view.findViewById(R.id.profile_phone);
        country = view.findViewById(R.id.profile_country);
        address = view.findViewById(R.id.profile_address);
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout_profile);

        profilePic = view.findViewById(R.id.profile_image);
        changePassword = view.findViewById(R.id.profile_change_pass_btn);
        updateProfile = view.findViewById(R.id.profile_update_btn);
        changePassword = view.findViewById(R.id.profile_change_pass_btn);

        setUserAndInflate();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            // First verify that token i c4s valid then hit the profile api.

            WebServices.getMyProfile(new MainInterface.ProfileCallListener() {
                @Override
                public void profileCallSuccess(CustomerProfileModel userProfile) {
                    inflateData(userProfile);
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e("Maine", "Profile data received successfully.");
                }

                @Override
                public void profileCallFail(String message) {
                    Log.e("Maine", message + "");
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UpdateProfile.class);
                someActivityResultLauncher.launch(i);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(i);
            }
        });


    }


    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        setUserAndInflate();
                    }
                }
            });

    private void setUserAndInflate() {
        currentUser = ResourceManager.getUserProfile();
        inflateData(currentUser);
    }


    @SuppressLint("SetTextI18n")
    private void inflateData(CustomerProfileModel currentUser) {
        //Glide.with(this).load(currentUser.getProfile_pic()).into(profilePic);
        profileName.setText(currentUser.getFullName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhone_number());
        country.setText(currentUser.getCountry());
        address.setText(currentUser.getAddress());


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}