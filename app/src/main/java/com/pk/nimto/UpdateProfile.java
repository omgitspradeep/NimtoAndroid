package com.pk.nimto;

import androidx.appcompat.app.AppCompatActivity;

import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.models.CustomerProfileModel;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateProfile extends AppCompatActivity implements Validator.ValidationListener {


    @NotEmpty
    @Order(1)
    @Length(min = 3)
    private EditText fn,ln;

    //@Pattern(regex = "^[1-9][0-9]*$", message = "Phone should not start with 0.")
    @Length(min=10, max = 10, message="Phone number should have 10 digit.")
    @NotEmpty
    @Order(2)
    EditText phone;

    @NotEmpty(message="Must be between 1 and 500")
    @Order(3)
    EditText address;


    @Email(message = "Please enter valid email address")
    @Order(4)
    EditText email;

    TextView username;

    Spinner  countrySelector;
    CheckBox verifyDetails;
    CustomerProfileModel currentUserData;
    ProgressBar PBProfileUpdate;
    Button updateButton;
    Validator validator;

    CustomerProfileModel dataToBeUpdated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        currentUserData = ResourceManager.getUserProfile();
        init();
        inflateData();
        clickEvents();

    }

    private void clickEvents() {

        // When check button is clicked.
        verifyDetails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // Validate fields & if invalidates then uncheck the verify Details checkbox.
                    validator.validate();
                }

            }
        });

        // When Update button is clicked.
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyDetails.isChecked()){

                    WebServices.verifyToken(new MainInterface.tokenListener() {
                        @Override
                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                            if(flag){
                                if(returnWithNewRefresh){
                                    Utils.setUserPreferences(UpdateProfile.this, accessToken, refreshToken);
                                }

                                // Show Progressbar and make signup button unclickable
                                PBProfileUpdate.setVisibility(View.VISIBLE);
                                updateButton.setClickable(false);
                                dataToBeUpdated=new CustomerProfileModel(currentUserData.getId(),
                                        email.getText().toString(),
                                        fn.getText().toString(),
                                        ln.getText().toString(),
                                        phone.getText().toString(),
                                        countrySelector.getSelectedItem().toString(),
                                        currentUserData.getAddress());


                                // Hit api to update the Profile
                                WebServices.ProfileUpdateCaller(dataToBeUpdated, "Bearer "+accessToken, new MainInterface.ProfileUpdateListener() {
                                    @Override
                                    public void onProfileUpdateResponse(boolean flag, String msg) {
                                        if(flag){
                                            //PBProfileUpdate.setVisibility(View.GONE);
                                            //updateButton.setClickable(true);
                                            // When We implement image setup with url then this will be updated what with reader object we get from api response.
                                            Toast.makeText(UpdateProfile.this, msg, Toast.LENGTH_LONG).show();

                                            goBackToProfileFragment();
                                            // Update the currentUser's data in Resource manager
                                            // Return to Profile Fragment
                                        }else{
                                            PBProfileUpdate.setVisibility(View.GONE);
                                            updateButton.setClickable(true);
                                            Toast.makeText(UpdateProfile.this, msg, Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });


                            }
                        }
                    }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());

                }else{
                    Toast.makeText(UpdateProfile.this, "Please check the Verify details.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void goBackToProfileFragment() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }


    private void inflateData() {
        fn.setText(currentUserData.getFirst_name());
        ln.setText(currentUserData.getLast_name());
        email.setText(currentUserData.getEmail());
        phone.setText(currentUserData.getPhone_number());
        address.setText(currentUserData.getAddress());
        username.setText(currentUserData.getLast_name());


        // Setting adapter and setting value for Address
        ArrayAdapter<CharSequence> countryAdapter = ArrayAdapter.createFromResource(this,R.array.country_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySelector.setAdapter(countryAdapter);
        countrySelector.setSelection(countryAdapter.getPosition(currentUserData.getCountry()));


    }


    public void init(){
        fn =findViewById(R.id.profile_update_fn);
        ln =findViewById(R.id.profile_update_ln);
        email =findViewById(R.id.profile_update_email);
        phone =findViewById(R.id.profile_update_phone);
        address =findViewById(R.id.profile_update_address);
        username =findViewById(R.id.profile_update_username);
        countrySelector = (Spinner) findViewById(R.id.profile_update_country);
        verifyDetails =(CheckBox) findViewById(R.id.book_update_verify_checkbox);
        PBProfileUpdate = findViewById(R.id.PB_profile_update);
        updateButton = findViewById(R.id.book_update_save_btn);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void onValidationSucceeded() {
        verifyDetails.setChecked(true);
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        verifyDetails.setChecked(false);
        Log.e("ANON","Validation unsuccess");
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Log.e("ANON",message);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }




}