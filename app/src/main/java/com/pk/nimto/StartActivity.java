package com.pk.nimto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.pk.nimto.R;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.models.Token;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.MyPreferences;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;

public class StartActivity extends AppCompatActivity implements MainInterface.LoginListener, MainInterface.tokenListener {

    LinearLayout noNetLayout;
    ImageButton refreshButton;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();

        if(!Utils.isConnected(this)){
            progressBar.setVisibility(View.GONE);
            noNetLayout.setVisibility(View.VISIBLE);
            Utils.displayShortToast(this,"Please check your internet Settings.");
        }else{
            getData();
        }

    }

    private void gotoLogin() {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private void getData() {
        // Check Whether user is logged in
        if(MyPreferences.getBooleanPrefrences(Constants.IS_USER_LOGGED_IN,this)){

            // Get refresh_token from SavedPreferences.And hit api to get new "refresh" and "access" token
            WebServices.verifyToken(StartActivity.this,MyPreferences.getStringPrefrences(Constants.ACCESS_TOKEN, this), MyPreferences.getStringPrefrences(Constants.REFRESH_TOKEN, this));

            progressBar.setVisibility(View.VISIBLE);
        }else{
            gotoLogin();
        }


    }

    private void init() {
        noNetLayout = findViewById(R.id.no_net_layout);
        refreshButton = findViewById(R.id.refresh_internet);
        progressBar = findViewById(R.id.progressbar);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isConnected(StartActivity.this)){
                    getData();
                    noNetLayout.setVisibility(View.GONE);
                }
            }
        });
    }






    @Override
    public void onLoginResponse(boolean flag, String msg) {
        Utils.displayShortToast(this, msg);


        Intent in;
        if(flag){
            // We need to set token because login with Jwt token does not returns tokens
            ResourceManager.setUserToken(new Token(MyPreferences.getStringPrefrences(Constants.ACCESS_TOKEN,this), MyPreferences.getStringPrefrences(Constants.REFRESH_TOKEN,this)));
            in = new Intent(this, MainActivity.class);
        }else{
            // It should not appear if everything is alright beacuse we just got the access_token and it must work.
            in = new Intent(this, LoginActivity.class);
        }
        startActivity(in);
    }




    @Override
    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean isRefreshTokenNew) {
        if(flag){

            if(isRefreshTokenNew){
                Utils.setUserPreferences(this,accessToken, refreshToken);
            }

            //  Now, request for user profile, all themes, and user orders using "access_token".
            WebServices.loginWithJWT(StartActivity.this, accessToken);



        }else{
            Utils.goBackToLoginWhenRefreshExpires(this);
        }

    }

}

/*
How login works?
1. Check whether user is logged in?
    a) if yes, get refresh_token from SavedPreferences. And check whether refresh_token is still valid.
        i) if valid, you will get access token. Now, request for user profile, all themes, and user orders.
        ii)if invalid, direct user to login page.

    b) if no, direct user to login page.

2. How to hit api?
    First verify access_token,
    a) if valid, Hit api with that token.
    b) if invalid, request another access_token using refresh_token.
        i) if refresh_token is valid, you will get access_token. Then hit api with it. This will add 2 more days because we have set "ROTATE_REFRESH_TOKEN=True" in backend.
        ii) There is no chance for invalid, if it was invalid then it would not have pass through login in the first place.
 */