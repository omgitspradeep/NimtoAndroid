package com.pk.nimto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pk.nimto.R;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.interfaces.commonListener;
import com.pk.nimto.models.SendData.ChangePasswordModel;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.MyPreferences;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class ChangePasswordActivity extends AppCompatActivity implements Validator.ValidationListener, commonListener {


    TextInputEditText currentPassword;

    @Password
    @Pattern(regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Strong password should contain \n1 digit, 1 lowercase, 1 uppercase, 1 special character and be atleast 8 characters.")
    TextInputEditText newPassword, confirmPassword;
    Button changePassword;
    ProgressBar progressBar;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();

    }

    private  void init(){
        validator = new Validator(this);
        validator.setValidationListener(this);

        currentPassword = findViewById(R.id.current_password_);
        newPassword = findViewById(R.id.new_password_);
        progressBar = findViewById(R.id.PB_change_password);
        confirmPassword = findViewById(R.id.confirm_new_password_);
        changePassword = findViewById(R.id.change_password);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                changePassword.setClickable(false);
                validator.validate();
            }
        });
    }

    public void onCancelClicked(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onValidationSucceeded() {
        String np = newPassword.getText().toString();
        String cp =confirmPassword.getText().toString();

        if(!np.equals(cp)){
            confirmPassword.setError("Password do not match.");
            changePassword.setClickable(true);
            progressBar.setVisibility(View.GONE);

        }else{



            //Check if token is valid
            WebServices.verifyToken(new MainInterface.tokenListener() {
                @Override
                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean isRefreshTokenNew) {
                    if(flag){
                        if(isRefreshTokenNew){
                            // Saving new access_token and refresh_token
                            MyPreferences.setStringPrefrences(Constants.ACCESS_TOKEN,accessToken,ChangePasswordActivity.this);
                            MyPreferences.setStringPrefrences(Constants.REFRESH_TOKEN,refreshToken,ChangePasswordActivity.this);
                        }
                        //Hit Change password api
                        WebServices.ChangePasswordCaller(ChangePasswordActivity.this, new ChangePasswordModel(cp,np));
                    }else{
                        Utils.goBackToLoginWhenRefreshExpires(ChangePasswordActivity.this);
                    }

                }
            }, ResourceManager.getUserToken().getAccess(),ResourceManager.getUserToken().getRefresh());

        }



    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Log.e("ANON","Validation unsuccess");
        progressBar.setVisibility(View.GONE);
        changePassword.setClickable(true);

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Log.e("ANON",message);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onCommonResponse(boolean flag) {
        if(flag){
            Utils.displayShortToast(this, "Password Changed successfully.");
            onBackPressed();
        }else{
            Utils.displayShortToast(this, "Password Changed unSuccessful.");
        }
    }
}