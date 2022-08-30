package com.example.nimto0.ui.marriagedata;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.SendData.TempContact;
import com.example.nimto0.models.SendData.TempParents;
import com.example.nimto0.models.marriagedata.Contact;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;
import com.google.android.material.textfield.TextInputLayout;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MarriageContactFragment extends Fragment implements commonListener {

    TextInputLayout phoneTI,emailTI, fbTI, twrTI, ytTI, linkedinTI;
    EditText phoneET,emailET, fbET, twrET, ytET, linkedinET;
    SwitchCompat contactSwitch;
    ProgressBar progressBar;
    AppCompatButton updateContactButton;

    String phoneNum, emailId, fbLink, twrLink, ytLink, linkedInLink;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        phoneET = view.findViewById(R.id.marraige_phone_number_et);
        phoneTI = view.findViewById(R.id.marraige_phone_number_et_);
        emailET = view.findViewById(R.id.marraige_email_et);
        emailTI = view.findViewById(R.id.marraige_email_et_);
        fbET = view.findViewById(R.id.marraige_fb_et);
        fbTI = view.findViewById(R.id.marraige_fb_et_);
        twrET = view.findViewById(R.id.marraige_twitter_et);
        twrTI = view.findViewById(R.id.marraige_twitter_et_);
        ytET = view.findViewById(R.id.marraige_yt_et);
        ytTI = view.findViewById(R.id.marraige_yt_et_);
        linkedinET = view.findViewById(R.id.marraige_linkedin_et);
        linkedinTI = view.findViewById(R.id.marraige_linkedin_et_);



        updateContactButton = view.findViewById(R.id.marraige_contact_update);
        contactSwitch  = view.findViewById(R.id.contact_switch_compat_marriage);
        progressBar = view.findViewById(R.id.contact_progressbar_marriage);

        disableAllEditText();
        inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getContact());


        updateContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum = phoneET.getText().toString();
                emailId = emailET.getText().toString();
                fbLink = fbET.getText().toString();
                twrLink = twrET.getText().toString();
                ytLink = ytET.getText().toString();
                linkedInLink = linkedinET.getText().toString();
                if(validate(phoneNum, emailId, fbLink, twrLink, ytLink, linkedInLink)){


                    new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Want to update contact Information!")
                            .setConfirmText("Just do it!")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                sweetAlertDialog.dismiss();


                                progressBar.setVisibility(View.VISIBLE);
                                updateContactButton.setClickable(false);
                                WebServices.verifyToken(new MainInterface.tokenListener() {
                                    @Override
                                    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                        if(flag){
                                            if(returnWithNewRefresh){
                                                Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                            }
                                            WebServices.updateMarriageContact(MarriageContactFragment.this, "Bearer "+accessToken, ResourceManager.getAllMarriageDataOfselectedOrder().getContact().getMarriage_data(), new TempContact(phoneNum,emailId, twrLink, fbLink, ytLink, linkedInLink));
                                        }else{
                                            Utils.goBackToLoginWhenRefreshExpires(getActivity());
                                        }

                                    }
                                }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());




                            })
                            .show();









                }
            }
        });

        contactSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    updateContactButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    updateContactButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });


    }

    private boolean validate(String phoneNum, String emailId, String fbLink, String twrLink, String ytLink, String linkedInLink) {
        boolean valid = true;
        if (phoneNum.isEmpty()) {
            phoneET.setError("Phone Number field is empty");
            valid = false;
        } else {
            phoneET.setError(null);
        }

        if (emailId.isEmpty()) {
            emailET.setError("Email Field is empty");
            valid = false;
        } else {
            emailET.setError(null);
        }

        if (fbLink.isEmpty()) {
            fbET.setError("Facebook Field is empty");
            valid = false;
        } else {
            fbET.setError(null);
        }

        if (twrLink.isEmpty()) {
            twrET.setError("Twitter Field is empty");
            valid = false;
        } else {
            twrET.setError(null);
        }

        if (ytLink.isEmpty()) {
            ytET.setError("Youtube Field is empty");
            valid = false;
        } else {
            ytET.setError(null);
        }

        if (linkedInLink.isEmpty()) {
            linkedinET.setError("LinkedIn Field is empty");
            valid = false;
        } else {
            linkedinET.setError(null);
        }

        return valid;
    }

    private void inflateData(Contact contact) {
        phoneET.setText(contact.getPhone());
        emailET.setText(contact.getEmail());
        fbET.setText(contact.getFb_link());
        twrET.setText(contact.getTwitter_link());
        ytET.setText(contact.getYt_link());
        linkedinET.setText(contact.getLnkd_link());

    }







    private void enableAllEditText() {
        phoneTI.setEnabled(true);
        emailTI.setEnabled(true);
        fbTI.setEnabled(true);
        twrTI.setEnabled(true);
        ytTI.setEnabled(true);
        linkedinTI.setEnabled(true);
    }

    private void disableAllEditText() {
        phoneTI.setEnabled(false);
        emailTI.setEnabled(false);
        fbTI.setEnabled(false);
        twrTI.setEnabled(false);
        ytTI.setEnabled(false);
        linkedinTI.setEnabled(false);
    }


    @Override
    public void onCommonResponse(boolean flag) {
        updateContactButton.setClickable(true);

        if (flag){
            inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getContact());
            contactSwitch.setChecked(false);
            updateContactButton.setVisibility(View.GONE);

        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);

    }
}