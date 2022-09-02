package com.pk.nimto.ui.marriagedata;

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

import com.pk.nimto.R;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.interfaces.commonListener;
import com.pk.nimto.models.SendData.TempTestimonial;
import com.pk.nimto.models.marriagedata.Testimonials;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MarriageTestimonialsFragment extends Fragment implements Validator.ValidationListener, commonListener {

    @NotEmpty
    @Order(1)
    @Length(max = 250, message="Name must not be empty.")
    private EditText grandParentsET, parentsET;

    private EditText brothersET, sistersET, nephewsET, cousinsET, maternalET;

    TextInputLayout grandParentsTI, parentsTI,brothersTI, sistersTI, nephewsTI, cousinsTI, maternalTI;
    String grandParentsNames, parentsNames,brothersNames, sistersNames, nephewsNames, cousinsNames, maternalNames;

    SwitchCompat testimonialSwitch;
    ProgressBar progressBar;
    AppCompatButton updateTestimonialButton;
    Validator validator;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_testimonials, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Initialization
        grandParentsTI = view.findViewById(R.id.testi_grand_parents_et_);
        parentsTI = view.findViewById(R.id.testi_parents_et_);
        brothersTI = view.findViewById(R.id.testi_brothers_et_);
        sistersTI = view.findViewById(R.id.testi_sisters_et_);
        nephewsTI = view.findViewById(R.id.testi_nephews_et_);
        cousinsTI = view.findViewById(R.id.testi_cousins_et_);
        maternalTI = view.findViewById(R.id.testi_maternal_et_);

        grandParentsET = view.findViewById(R.id.testi_grand_parents_et);
        parentsET = view.findViewById(R.id.testi_parents_et);
        brothersET = view.findViewById(R.id.testi_brothers_et);
        sistersET = view.findViewById(R.id.testi_sisters_et);
        nephewsET = view.findViewById(R.id.testi_nephews_et);
        cousinsET = view.findViewById(R.id.testi_cousins_et);
        maternalET = view.findViewById(R.id.testi_maternal_et);

        updateTestimonialButton = view.findViewById(R.id.marraige_testi_update);
        testimonialSwitch  = view.findViewById(R.id.testi_switch_compat_marriage);
        progressBar = view.findViewById(R.id.testi_progressbar_marriage);

        validator = new Validator(this);
        validator.setValidationListener(this);

        inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getTestimonials());
        clickEvents();
        disableAllEditText();
    }

    private void inflateData(Testimonials testimonials) {
        grandParentsET.setText(testimonials.getGrandParents());
        parentsET.setText(testimonials.getParents());
        brothersET.setText(testimonials.getBrothers());
        sistersET.setText(testimonials.getSisters());
        nephewsET.setText(testimonials.getNephews());
        cousinsET.setText(testimonials.getCousins());
        maternalET.setText(testimonials.getMaternal());

    }

    private void clickEvents() {

        updateTestimonialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grandParentsNames = grandParentsET.getText().toString();
                parentsNames = parentsET.getText().toString();
                brothersNames = brothersET.getText().toString();
                sistersNames = sistersET.getText().toString();
                cousinsNames = cousinsET.getText().toString();
                nephewsNames = nephewsET.getText().toString();
                maternalNames = maternalET.getText().toString();

                validator.validate();

            }
        });

        testimonialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    updateTestimonialButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    updateTestimonialButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });

    }


    private void enableAllEditText() {
        grandParentsTI.setEnabled(true);
        parentsTI.setEnabled(true);
        brothersTI.setEnabled(true);
        sistersTI.setEnabled(true);
        cousinsTI.setEnabled(true);
        nephewsTI.setEnabled(true);
        maternalTI.setEnabled(true);

        /*
        grandParentsET.setFocusable(true);
        parentsET.setFocusable(true);
        brothersET.setFocusable(true);
        sistersET.setFocusable(true);
        cousinsET.setFocusable(true);
        nephewsET.setFocusable(true);
        maternalET.setFocusable(true);

         */
    }

    private void disableAllEditText() {
        grandParentsTI.setEnabled(false);
        parentsTI.setEnabled(false);
        brothersTI.setEnabled(false);
        sistersTI.setEnabled(false);
        cousinsTI.setEnabled(false);
        nephewsTI.setEnabled(false);
        maternalTI.setEnabled(false);

        /*
        grandParentsET.setFocusable(false);
        parentsET.setFocusable(false);
        brothersET.setFocusable(false);
        sistersET.setFocusable(false);
        cousinsET.setFocusable(false);
        nephewsET.setFocusable(false);
        maternalET.setFocusable(false);

         */
    }


    @Override
    public void onValidationSucceeded() {

        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Want to update this Testimonials!")
                .setConfirmText("Just do it!")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();


                    progressBar.setVisibility(View.VISIBLE);
                    updateTestimonialButton.setClickable(false);
                    WebServices.verifyToken(new MainInterface.tokenListener() {
                        @Override
                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                            if(flag){
                                if(returnWithNewRefresh){
                                    Utils.setUserPreferences(getActivity(), accessToken, refreshToken);
                                }
                                WebServices.updateMarriageTestimonialsData(MarriageTestimonialsFragment.this, "Bearer "+accessToken, ResourceManager.getAllMarriageDataOfselectedOrder().getTestimonials().getMarriageData(), new TempTestimonial(grandParentsNames, parentsNames,brothersNames, sistersNames, nephewsNames, cousinsNames, maternalNames));
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
        updateTestimonialButton.setClickable(true);

        if (flag){
            inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getTestimonials());
            testimonialSwitch.setChecked(false);

        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);
    }
}