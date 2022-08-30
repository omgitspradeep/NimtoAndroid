package com.example.nimto0.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.nimto0.ChangePasswordActivity;
import com.example.nimto0.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Utils {




    public static void displayShortToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void displayLongToast(Context context, int message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
        }
        else
            return false;
    }


    public static SweetAlertDialog sweetAlertDialog(Context context){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        return  pDialog;
    }

    public static void displayPopUp(String wish, Context mContext) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
        dialog.setMessage(wish);
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



    public static void goBackToLoginWhenRefreshExpires(Activity activity){
        MyPreferences.setBooleanPrefrences(Constants.IS_USER_LOGGED_IN,false,activity);
        Utils.displayShortToast(activity, "Refresh Token expired. Login with your credentials");
        Intent in = new Intent(activity, LoginActivity.class);
        activity.startActivity(in);

    }

    public static void setUserPreferences(Context mContext, String accessToken, String refreshToken) {
        // Saving new access_token and refresh_token
        MyPreferences.setStringPrefrences(Constants.ACCESS_TOKEN,accessToken,mContext);
        MyPreferences.setStringPrefrences(Constants.REFRESH_TOKEN,refreshToken,mContext);
    }



    public static void imageSelection(Activity context, int PICK_IMAGE) {
        final CharSequence[] options = {"Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Upload Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    context.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }



    }

























    //GSON-> Converts java objects into JSON
    /*
    * Using Retrofit.
    * 1. Add dependencies
    * 2. Make model object i.e. POJO
    * 3. Make ApiClient class, to return Retrofit instance
    * 4. Make ApiInterface class, to call HTTP endpoints.
    * 5. Call Interface from anywhere.
    * */


   /*     private void refreshToken() {
        Call<Token> call =apiInterface.refreshToken(ResourceManager.getMyToken());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                assert response.body() != null;
                ResourceManager.getMyToken().setAccess(response.body().getAccess());
                Log.d(TAG,"Refresh successful" );
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e(TAG, "SORRY - Refresh");
            }
        });
    }


    private void getTokens() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Token> call =apiInterface.getToken(new LoginModel("Ravi","1abc@def"));
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                assert response.body() != null;
                ResourceManager.setMyToken(response.body());
                Log.d(TAG,"Token successful" );
                refreshToken();
                Log.d(TAG, response.body().getAccess());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e(TAG, "SORRY ");
            }
        });
    }*/



