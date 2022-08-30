package com.example.nimto0.ui.marriagedata;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.SendData.TempMeetingPoint;
import com.example.nimto0.models.SendData.TempTestimonial;
import com.example.nimto0.models.marriagedata.Meetingpoint;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MarriageMeetingPointFragment extends Fragment implements Validator.ValidationListener, commonListener {

    @NotEmpty
    @Order(1)
    @Length(max = 250, message="Direction information must be provided for guest's ease (250 characters).")
    private EditText directionInfoET;


    @NotEmpty
    @Order(2)
    @Length(min=10, max = 15, message="Phone Number should be between 10 to 15 characters.")
    private EditText contactNumET;

    @NotEmpty
    @Order(3)
    @Length(message="Field must not be empty.")
    private EditText palaceNameET, addressET, emailOrFbLinkET;

    private EditText latitudeET, longitudeET;

    TextInputLayout directionInfoTI, contactNumTI, palaceNameTI, addressTI, emailOrFbLinkTI, latitudeTI, longitudeTI;
    String directionInfoText, contactNumText, palaceNameText, addressText, emailOrFbLinkText, latitudeText, longitudeText;

    SwitchCompat mpSwitch;
    ProgressBar progressBar;
    AppCompatButton updateMpButton;
    Validator validator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_meeting_point, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        directionInfoET = view.findViewById(R.id.mp_direction_info_et);
        contactNumET = view.findViewById(R.id.mp_contact_num_et);
        palaceNameET = view.findViewById(R.id.mp_palace_name_et);
        addressET = view.findViewById(R.id.mp_address_et);
        emailOrFbLinkET = view.findViewById(R.id.mp_email_or_fb_link_et);
        latitudeET = view.findViewById(R.id.mp_latitude_et);
        longitudeET = view.findViewById(R.id.mp_longitude_et);


        directionInfoTI = view.findViewById(R.id.mp_direction_info_et_);
        contactNumTI = view.findViewById(R.id.mp_contact_num_et_);
        palaceNameTI = view.findViewById(R.id.mp_palace_name_et_);
        addressTI = view.findViewById(R.id.mp_address_et_);
        emailOrFbLinkTI = view.findViewById(R.id.mp_email_or_fb_link_et_);
        latitudeTI = view.findViewById(R.id.mp_latitude_et_);
        longitudeTI = view.findViewById(R.id.mp_longitude_et_);


        updateMpButton = view.findViewById(R.id.marraige_mp_update);
        mpSwitch  = view.findViewById(R.id.mp_switch_compat_marriage);
        progressBar = view.findViewById(R.id.mp_progressbar_marriage);

        validator = new Validator(this);
        validator.setValidationListener(this);

        inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getMeetingpoint());
        clickEvents();
        disableAllEditText();


    }

    private void clickEvents() {

        updateMpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directionInfoText = directionInfoET.getText().toString();
                contactNumText = contactNumET.getText().toString();
                palaceNameText = palaceNameET.getText().toString();
                addressText = addressET.getText().toString();
                emailOrFbLinkText = emailOrFbLinkET.getText().toString();
                latitudeText = latitudeET.getText().toString();
                longitudeText = longitudeET.getText().toString();

                validator.validate();

            }
        });


        mpSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    updateMpButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    updateMpButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });



    }

    private void disableAllEditText() {
        directionInfoTI.setEnabled(false);
        addressTI.setEnabled(false);
        contactNumTI.setEnabled(false);
        palaceNameTI.setEnabled(false);
        emailOrFbLinkTI.setEnabled(false);
        latitudeTI.setEnabled(false);
        longitudeTI.setEnabled(false);
    }

    private void enableAllEditText() {
        directionInfoTI.setEnabled(true);
        addressTI.setEnabled(true);
        contactNumTI.setEnabled(true);
        palaceNameTI.setEnabled(true);
        emailOrFbLinkTI.setEnabled(true);
        latitudeTI.setEnabled(true);
        longitudeTI.setEnabled(true);
    }

    private void inflateData(Meetingpoint meetingpoint) {

        latitudeET.setText(meetingpoint.getLatitude());
        longitudeET.setText(meetingpoint.getLongitude());
        directionInfoET.setText(meetingpoint.getDirection_info());
        contactNumET.setText(meetingpoint.getContact_num());
        palaceNameET.setText(meetingpoint.getPalace_name());
        addressET.setText(meetingpoint.getAddress());
        emailOrFbLinkET.setText(meetingpoint.getEmail_or_fb_link());

    }

    @Override
    public void onValidationSucceeded() {


        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Want to update Meetup place!")
                .setConfirmText("Just do it!")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();


                    progressBar.setVisibility(View.VISIBLE);
                    updateMpButton.setClickable(false);
                    WebServices.verifyToken(new MainInterface.tokenListener() {
                        @Override
                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                            if(flag){
                                if(returnWithNewRefresh){
                                    Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                }
                                WebServices.updateMarriageMeetingpoint(MarriageMeetingPointFragment.this, "Bearer "+accessToken, ResourceManager.getAllMarriageDataOfselectedOrder().getMeetingpoint().getMarriage_data(), new TempMeetingPoint(longitudeText, latitudeText,directionInfoText, palaceNameText, addressText,contactNumText, emailOrFbLinkText));
                            }else{
                                Utils.goBackToLoginWhenRefreshExpires(getActivity());
                            }

                        }
                    }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());






                })
                .show();













    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Log.e("ANON",message);
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onCommonResponse(boolean flag) {
        updateMpButton.setClickable(true);

        if (flag){
            inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getMeetingpoint());
            mpSwitch.setChecked(false);

        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);
    }
}