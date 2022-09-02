package com.example.nimto0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.models.SendData.SendLoginData;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.MyPreferences;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;

public class LoginActivity extends AppCompatActivity implements MainInterface.LoginListener  {

    EditText usernameET, passwordET;
    AppCompatButton pushLogin;
    ProgressBar loginProgress;
    String userName,userPass;
    private static final String TAG = "Maine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        pushLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isConnected(LoginActivity.this)){
                    userName = usernameET.getText().toString();
                    userPass= passwordET.getText().toString();

                    if(validate(userName,userPass)){
                        loginProgress.setVisibility(View.VISIBLE);
                        pushLogin.setClickable(false);
                        WebServices.LoginCaller(LoginActivity.this,new SendLoginData(userName,userPass));
                    }

                }else {
                    Utils.displayShortToast(LoginActivity.this,"No Internet.");
                }
            }
        });

    }



    private void init() {
        usernameET = findViewById(R.id.input_email);
        passwordET = findViewById(R.id.input_password);
        pushLogin = findViewById(R.id.btn_login);
        loginProgress = findViewById(R.id.login_progress);

        usernameET.setText("s@gmail.com");
    }

    public boolean validate(String userName,String userPass) {
        boolean valid = true;
        if (userName.isEmpty()) {
            usernameET.setError("Username field is empty");
            valid = false;
        } else {
            usernameET.setError(null);
        }

        if (userPass.isEmpty()) {
            passwordET.setError("Password Field is empty");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        return valid;
    }


    @Override
    public void onLoginResponse(boolean flag, String msg) {
        if(flag){
            Utils.displayShortToast(this, msg);

            // Saving access and refresh token.
            MyPreferences.setBooleanPrefrences(Constants.IS_USER_LOGGED_IN, true,this);
            Utils.setUserPreferences(this,ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());



            // Directing to Main Activity where all the themes will be displayed.
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);

        }else{
            loginProgress.setVisibility(View.GONE);
            pushLogin.setClickable(true);
            Utils.displayShortToast(this,"Check your credentials and Try again");
        }

    }
}