package com.example.nimto0.ui.marriagedata;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.SendData.TempMainData;
import com.example.nimto0.models.SendData.TempParents;
import com.example.nimto0.models.marriagedata.MainData;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import java.util.Calendar;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MarriageMainDataFragment extends Fragment implements Validator.ValidationListener, commonListener {

    ImageView iconIV, headerIV, aboutUsIV;

    @NotEmpty
    @Order(1)
    @Length(min=4, max = 100, message="Date cannot be empty (4-100 characters).")
    EditText marryDateET, engagementDateET,weddingDayET, receptionDateET;

    @NotEmpty
    @Order(2)
    @Length(min=4, max = 50, message="Time cannot be empty (4-50 characters). ")
    EditText jantiPranstanTimeET, receptionTimeET;

    @NotEmpty
    @Order(3)
    @Length(min=4, max = 100, message="Place cannot be empty (4-100 characters).")
    EditText jantiPranstanPlaceET, receptionPlaceET, groomAddressET, brideAddressET;


    EditText orderNameET, brideGroomNameET, defaultInvitationMessageET, groomInfoET, brideInfoET, footerMessageET;
    TextInputLayout marryDateTI, orderNameTI, brideGroomNameTI, engagementDateTI, weddingDayTI, jantiPranstanTimeTI, jantiPranstanPlaceTI, receptionDateTI, receptionPlaceTI,receptionTimeTI, defaultInvitationMessageTI, groomInfoTI, brideInfoTI, groomAddressTI, brideAddressTI, footerMessageTI;
    String marryDateText, orderNameText, brideGroomNameText, engagementDateText, weddingDayText, jantiPranstanTimeText, jantiPranstanPlaceText, receptionDateText, receptionPlaceText, receptionTimeText, defaultInvitationMessageText, groomInfoText, brideInfoText, groomAddressText, brideAddressText, footerMessageText;

    SwitchCompat MDSwitch;
    ProgressBar progressBar;
    AppCompatButton updateMDButton;
    Validator validator;

    Calendar calendar;
    int year, month, day;
    MainData mainData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marriage_main_data, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainData = ResourceManager.getAllMarriageDataOfselectedOrder().getMain_data();

        //Initialization

        marryDateET = view.findViewById(R.id.main_data_marry_date_et);
        orderNameET = view.findViewById(R.id.main_data_order_name_et);
        brideGroomNameET = view.findViewById(R.id.main_data_b_g_name_et);
        engagementDateET = view.findViewById(R.id.main_data_engagement_date_et);
        weddingDayET = view.findViewById(R.id.main_data_wedding_day_et);
        jantiPranstanTimeET = view.findViewById(R.id.main_data_janti_prasthan_time_et);
        jantiPranstanPlaceET = view.findViewById(R.id.main_data_janti_prasthan_place_et);
        receptionDateET = view.findViewById(R.id.main_data_reception_date_et);
        receptionPlaceET = view.findViewById(R.id.main_data_reception_place_et);
        receptionTimeET = view.findViewById(R.id.main_data_reception_time_et);
        defaultInvitationMessageET = view.findViewById(R.id.main_data_invitation_msz_et);
        groomInfoET = view.findViewById(R.id.main_data_groom_info_et);
        brideInfoET = view.findViewById(R.id.main_data_bride_info_et);
        groomAddressET = view.findViewById(R.id.main_data_groom_address_et);
        brideAddressET = view.findViewById(R.id.main_data_bride_address_et);
        footerMessageET = view.findViewById(R.id.main_data_footer_message_et);

        marryDateTI = view.findViewById(R.id.main_data_marry_date_et_);
        orderNameTI = view.findViewById(R.id.main_data_order_name_et_);
        brideGroomNameTI = view.findViewById(R.id.main_data_b_g_name_et_);
        engagementDateTI = view.findViewById(R.id.main_data_engagement_date_et_);
        weddingDayTI = view.findViewById(R.id.main_data_wedding_day_et_);
        jantiPranstanTimeTI = view.findViewById(R.id.main_data_janti_prasthan_time_et_);
        jantiPranstanPlaceTI = view.findViewById(R.id.main_data_janti_prasthan_place_et_);
        receptionDateTI = view.findViewById(R.id.main_data_reception_date_et_);
        receptionPlaceTI = view.findViewById(R.id.main_data_reception_place_et_);
        receptionTimeTI = view.findViewById(R.id.main_data_reception_time_et_);
        defaultInvitationMessageTI = view.findViewById(R.id.main_data_invitation_msz_et_);
        groomInfoTI = view.findViewById(R.id.main_data_groom_info_et_);
        brideInfoTI = view.findViewById(R.id.main_data_bride_info_et_);
        groomAddressTI = view.findViewById(R.id.main_data_groom_address_et_);
        brideAddressTI = view.findViewById(R.id.main_data_bride_address_et_);
        footerMessageTI = view.findViewById(R.id.main_data_footer_message_et_);

        iconIV = view.findViewById(R.id.main_data_wedding_icon_iv);
        headerIV = view.findViewById(R.id.main_data_header_image_iv);
        aboutUsIV = view.findViewById(R.id.main_data_about_us_image_iv);


        updateMDButton = view.findViewById(R.id.marraige_main_data_update);
        MDSwitch  = view.findViewById(R.id.main_data_switch_compat_marriage);
        progressBar = view.findViewById(R.id.main_data_progressbar_marriage);

        validator = new Validator(this);
        validator.setValidationListener(this);


        inflateData(mainData);
        onClickEvents();
        disableAllEditText();





    }

    private void inflateData(MainData mainData) {
        marryDateET.setText(mainData.getMarry_date_text());
        orderNameET.setText(mainData.getName());
        brideGroomNameET.setText(mainData.getBride_groom_name());
        engagementDateET.setText(mainData.getEngagement_date());
        weddingDayET.setText(mainData.getWedding_day());
        jantiPranstanTimeET.setText(mainData.getJanti_prasthan_time());
        jantiPranstanPlaceET.setText(mainData.getJanti_prsthan_place());
        receptionDateET.setText(mainData.getReception_date());
        receptionPlaceET.setText(mainData.getReception_place());
        receptionTimeET.setText(mainData.getReception_time());
        defaultInvitationMessageET.setText(mainData.getDefault_invitation_msz());
        groomInfoET.setText(mainData.getGroom_info());
        brideInfoET.setText(mainData.getBride_info());
        groomAddressET.setText(mainData.getGroom_address());
        brideAddressET.setText(mainData.getBride_address());
        footerMessageET.setText(mainData.getFooter_message());

        Glide.with(getActivity())
                .load(Constants.baseURL+mainData.getTitle_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(iconIV);

        Glide.with(getActivity())
                .load(Constants.baseURL+mainData.getAbout_us_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(aboutUsIV);

        Glide.with(getActivity())
                .load(Constants.baseURL+mainData.getHeader_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(headerIV);

    }

    private void onClickEvents() {
        marryDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarEvent(marryDateET);
            }
        });



        updateMDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marryDateText = marryDateET.getText().toString();
                orderNameText = orderNameET.getText().toString();
                brideGroomNameText = brideGroomNameET.getText().toString();
                engagementDateText = engagementDateET.getText().toString();
                weddingDayText = weddingDayET.getText().toString();
                jantiPranstanTimeText = jantiPranstanPlaceET.getText().toString();
                jantiPranstanPlaceText = jantiPranstanPlaceET.getText().toString();
                receptionDateText = receptionDateET.getText().toString();
                receptionPlaceText = receptionPlaceET.getText().toString();
                receptionTimeText = receptionTimeET.getText().toString();
                defaultInvitationMessageText = defaultInvitationMessageET.getText().toString();
                groomInfoText = groomInfoET.getText().toString();
                brideInfoText = brideInfoET.getText().toString();
                groomAddressText = groomAddressET.getText().toString();
                brideAddressText = brideAddressET.getText().toString();
                footerMessageText = footerMessageET.getText().toString();

                validator.validate();
            }
        });



        MDSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    //Switch is Turned On using on checked changed
                    updateMDButton.setVisibility(View.VISIBLE);
                    enableAllEditText();
                }else {
                    //Switch is Turned Off using on checked changed
                    updateMDButton.setVisibility(View.GONE);
                    disableAllEditText();

                }
            }
        });

        iconIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.imageSelection(getActivity(),Constants.PICK_IMAGE);
            }
        });

        headerIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.imageSelection(getActivity(),Constants.PICK_IMAGE);
            }
        });

        aboutUsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.imageSelection(getActivity(),Constants.PICK_IMAGE);
            }
        });


    }

    private void disableAllEditText() {
        marryDateTI.setEnabled(false);
        orderNameTI.setEnabled(false);
        brideGroomNameTI.setEnabled(false);
        engagementDateTI.setEnabled(false);
        weddingDayTI.setEnabled(false);
        jantiPranstanTimeTI.setEnabled(false);
        jantiPranstanPlaceTI.setEnabled(false);
        receptionDateTI.setEnabled(false);
        receptionPlaceTI.setEnabled(false);
        receptionTimeTI.setEnabled(false);
        defaultInvitationMessageTI.setEnabled(false);
        groomInfoTI.setEnabled(false);
        brideInfoTI.setEnabled(false);
        groomAddressTI.setEnabled(false);
        brideAddressTI.setEnabled(false);
        footerMessageTI.setEnabled(false);

        iconIV.setClickable(false);
        aboutUsIV.setClickable(false);
        headerIV.setClickable(false);

    }

    private void enableAllEditText() {
        marryDateTI.setEnabled(true);
        orderNameTI.setEnabled(true);
        brideGroomNameTI.setEnabled(true);
        engagementDateTI.setEnabled(true);
        weddingDayTI.setEnabled(true);
        jantiPranstanTimeTI.setEnabled(true);
        jantiPranstanPlaceTI.setEnabled(true);
        receptionDateTI.setEnabled(true);
        receptionPlaceTI.setEnabled(true);
        receptionTimeTI.setEnabled(true);
        defaultInvitationMessageTI.setEnabled(true);
        groomInfoTI.setEnabled(true);
        brideInfoTI.setEnabled(true);
        groomAddressTI.setEnabled(true);
        brideAddressTI.setEnabled(true);
        footerMessageTI.setEnabled(true);

        iconIV.setClickable(true);
        aboutUsIV.setClickable(true);
        headerIV.setClickable(true);

    }

    private void calendarEvent(EditText editField) {
        calendar  = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = day +"/"+month +"/"+year;
                editField.setText(date);
            }
        },year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onValidationSucceeded() {

        new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Want to update Marriage Data!")
                .setConfirmText("Just do it!")
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.dismiss();

                    progressBar.setVisibility(View.VISIBLE);
                    updateMDButton.setClickable(false);
                    WebServices.verifyToken(new MainInterface.tokenListener() {
                        @Override
                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {

                            WebServices.updateMarriageMainData(MarriageMainDataFragment.this, "Bearer "+accessToken, mainData.getMarriage_order(), new TempMainData(orderNameText, brideGroomNameText, marryDateText, engagementDateText, weddingDayText, jantiPranstanTimeText, jantiPranstanPlaceText, receptionDateText, receptionTimeText, receptionPlaceText, defaultInvitationMessageText, groomInfoText, brideInfoText, groomAddressText, brideAddressText, footerMessageText));
                            Log.e("Maine", mainData.getMarriage_order()+"");

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


        updateMDButton.setClickable(true);

        if (flag){
            inflateData(ResourceManager.getAllMarriageDataOfselectedOrder().getMain_data());
            MDSwitch.setChecked(false);
        }else{
            Utils.displayShortToast(getContext(),"Something went wrong try again.");
        }
        progressBar.setVisibility(View.GONE);

    }
}