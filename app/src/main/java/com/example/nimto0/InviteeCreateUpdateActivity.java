package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.CustomizedInviteeModel;
import com.example.nimto0.models.SendData.InviteeModel;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;

public class InviteeCreateUpdateActivity extends AppCompatActivity implements commonListener {

    Button createUpdate;
    EditText fullname, address, message;
    TextView donation, wish;
    ProgressBar progressBar;

    RadioGroup genderGroup, invitationStatusGroup;
    RadioButton radioButton,radioButtonMale,radioButtonFemale,radioButtonReception, radioButtonMarriage;
    boolean hereWeAreForCreatingInvitee= false;
    CustomizedInviteeModel selectedInvitee= null;
    int orderID = 0;
    int userCameFromActivity=0;   // MyOrderDetailActivity:1, MyOrderInviteesActivity/MyInviteesRVAdapter :2 ,

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitee_create_update);
        String action = getIntent().getExtras().getString(Constants.TASK);
        orderID = getIntent().getExtras().getInt(Constants.ORDER_ID);
        userCameFromActivity = getIntent().getExtras().getInt(Constants.INTENT_FROM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(action+" Invitee ");  // EG: Create invitee - View Invitee - Update Invitee


        init();
        if(action.equalsIgnoreCase(Constants.CREATE)){
            createUpdate.setText("Create Invitee");
            hereWeAreForCreatingInvitee=true;
            donation.setVisibility(View.GONE);

        }else if(action.equalsIgnoreCase(Constants.UPDATE) ){
            createUpdate.setText("Update Invitee");
            inflateData();
        }else{
            // This is for VIewing Invitees detail. When invitee name is clicked in MyOrdersDetailsRVAdapter/ MyOrderDetailActivity
            inflateData();
            createUpdate.setVisibility(View.GONE);
            fullname.setEnabled(false);
            address.setEnabled(false);
            message.setEnabled(false);
            genderGroup.setEnabled(false);
            invitationStatusGroup.setEnabled(false);
        }
    }

    private void inflateData() {
        selectedInvitee = ResourceManager.getSelectedInvitee();
        fullname.setText(selectedInvitee.getName());
        address.setText(selectedInvitee.getAddress());
        message.setText(selectedInvitee.getInvitee_message());
        wish.setText(selectedInvitee.getWish());

        if(selectedInvitee.getGender().equalsIgnoreCase(Constants.Mr)){
            radioButtonMale.setChecked(true);
        }else{
            radioButtonFemale.setChecked(true);
        }

        if(selectedInvitee.getInviteStatus().equalsIgnoreCase(Constants.WEDDING)){
            radioButtonMarriage.setChecked(true);
        }else{
            radioButtonReception.setChecked(true);
        }

    }

    private void init() {
        genderGroup = findViewById(R.id.radio_group_gender);
        invitationStatusGroup = findViewById(R.id.radio_group_status);
        radioButtonMale = findViewById(R.id.radio_btn_gender_male);
        radioButtonFemale = findViewById(R.id.radio_btn_gender_female);
        radioButtonReception = findViewById(R.id.radio_btn_sts_reception);
        radioButtonMarriage = findViewById(R.id.radio_btn_sts_wedding);

        createUpdate = findViewById(R.id.invitee_add_modify_btn);
        fullname = findViewById(R.id.invitee_cu_guest_name);
        address = findViewById(R.id.invitee_cu_guest_address);
        message = findViewById(R.id.invitee_cu_guest_message);
        donation = findViewById(R.id.invitee_cu_guest_donation);
        wish = findViewById(R.id.invitee_cu_guest_wish);
        progressBar = findViewById(R.id.invitee_CU_pB);



        createUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedGender = getMeTextOfSelectedRadioButton(genderGroup.getCheckedRadioButtonId(), genderGroup);
                String selectedStatus = getMeTextOfSelectedRadioButton(invitationStatusGroup.getCheckedRadioButtonId(), invitationStatusGroup);

                String fn = fullname.getText().toString();
                String addrs = address.getText().toString();
                String msz = message.getText().toString();

                // Check if access token is still valid
                progressBar.setVisibility(View.VISIBLE);






                WebServices.verifyToken(new MainInterface.tokenListener() {
                    @Override
                    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                        if(flag){
                            if(returnWithNewRefresh){
                                Utils.setUserPreferences(InviteeCreateUpdateActivity.this, accessToken, refreshToken);
                            }

                            if(hereWeAreForCreatingInvitee){
                                InviteeModel inviteeModel = new InviteeModel(fn,orderID, selectedGender, addrs, selectedStatus, msz);

                                WebServices.createNewInvitee(InviteeCreateUpdateActivity.this,accessToken, inviteeModel, inviteeModel.getOrder());
                            }else{
                                InviteeModel inviteeModel = selectedInvitee.getInviteeObjectForUpdate();
                                inviteeModel.setName(fn);
                                inviteeModel.setAddress(addrs);
                                inviteeModel.setInvitee_message(msz);
                                inviteeModel.setGender(selectedGender);
                                inviteeModel.setInviteStatus(selectedStatus);
                                WebServices.updatingInvitees(InviteeCreateUpdateActivity.this,accessToken, inviteeModel);
                            }

                        }else{
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());


                Toast.makeText(InviteeCreateUpdateActivity.this, selectedGender+"\n"+selectedStatus, Toast.LENGTH_LONG).show();

            }
        });
    }

    private String getMeTextOfSelectedRadioButton(int checkedRadioButtonId, RadioGroup radioGroup) {
        if(checkedRadioButtonId != -1){
            radioButton = (RadioButton)radioGroup.findViewById(checkedRadioButtonId);
            return radioButton.getText().toString();
        }else{
            Utils.displayLongToast(this, R.string.no_radio_selected);
            return "Nope ";
        }

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
    public void onCommonResponse(boolean flag) {
        progressBar.setVisibility(View.GONE);
        if(flag){
            // Put here bombardment of crackers.
            if(hereWeAreForCreatingInvitee){
                Utils.displayShortToast(this, "New invitee is created");
            }else{
                Utils.displayShortToast(this, "Invitee is Updated.");
            }
            goBackToInflateData();

        }else{
            Utils.displayShortToast(this, "Something went wrong. Please try again.");
        }


    }

    private void goBackToInflateData() {
        Intent intent =null;
        if(userCameFromActivity == 1){
            intent= new Intent(this, MyOrderDetailActivity.class);

        }else if (userCameFromActivity ==2){
            intent= new Intent(this, MyOrderInviteesActivity.class);
        }else{
            onBackPressed();
        }

        assert intent != null;
        intent.putExtra(Constants.ORDER_ID, ResourceManager.getOrderIdOfInvitees());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        onBackPressed();
    }
}