package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.CustOrderModel;
import com.example.nimto0.models.marriagedata.PaymentAndLanguageModel;
import com.example.nimto0.models.marriagedata.Plans;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PaymentActivity extends AppCompatActivity implements commonListener{

    AppCompatButton confirmPayment;
    ProgressBar progressBar;
    Spinner paymentSpinner, plansSpinner, languageSpinner;
    ArrayAdapter planAdapter, adapter;
    ImageButton viewPlans;
    List<Plans> plansList;
    int selectedThemeId;
    EditText orderNameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");

        selectedThemeId=(int)getIntent().getSerializableExtra(Constants.THEME_ID);

        init();
        onClicks();



    }

    private void onClicks() {

        viewPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Rect displayRectangle = new Rect();
                Window window = PaymentActivity.this.getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
                final AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.plan_display_popup, viewGroup, false);
                dialogView.setMinimumWidth((int)(displayRectangle.width() * 1f));
                dialogView.setMinimumHeight((int)(displayRectangle.height() * 1f));
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                // setup the table
                TextView textView =dialogView.findViewById(R.id.popup_display_TV);
                StringBuilder plan= new StringBuilder();
                for(int i=0;i<plansList.size();i++){
                    plan.append(plansList.get(i).toString()).append("\n");
                }

                textView.setText(plan.toString());



                Button buttonOk= dialogView.findViewById(R.id.buttonOk);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();



            }
        });



        confirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write your action here........
                String orderNameText = orderNameET.getText().toString();

                if (orderNameText.length()>=5 && orderNameText.length()<=20){
                    new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Congratulations,")
                            .setContentText("You are one step away from joy!")
                            .setConfirmText("Just do it!")
                            .setConfirmClickListener(sweetAlertDialog -> {
                                sweetAlertDialog.dismiss();
                                confirmPayment.setClickable(false);
                                progressBar.setVisibility(View.VISIBLE);
                                WebServices.verifyToken(new MainInterface.tokenListener() {
                                        @Override
                                        public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                            if(flag){
                                                if(returnWithNewRefresh){
                                                    Utils.setUserPreferences(PaymentActivity.this, accessToken, refreshToken);
                                                }
                                                CustOrderModel custOrderModel =new CustOrderModel(orderNameText, getLanguageCode(languageSpinner.getSelectedItem().toString()), "active", paymentSpinner.getSelectedItem().toString(), selectedThemeId,getPlanId(plansSpinner.getSelectedItem().toString()),ResourceManager.getUserProfile().getId());
                                                WebServices.createNewOrder(PaymentActivity.this, "Bearer "+accessToken,custOrderModel);

                                                Log.e("Maine", custOrderModel.toString());
                                            }else{
                                                progressBar.setVisibility(View.GONE);
                                                confirmPayment.setClickable(true);
                                            }


                                        }
                                    },ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());

                            })
                            .show();
                }else{
                    orderNameET.setError("Please provide characters between 5 to 20 for order name.");
                }

            }
        });

    }

    private String getLanguageCode(String languageNormalString) {
        List<PaymentAndLanguageModel> languageList= ResourceManager.getListOfLanguages();
        for(int i=0; i<languageList.size();i++){
            PaymentAndLanguageModel pl = languageList.get(i);
            if(pl.getV().equals(languageNormalString)){
                return pl.getK();
            }
        }
        return "";
    }

    private int getPlanId(String planText) {
        for(int i=0; i<plansList.size();i++){
            Plans plan = plansList.get(i);
            if(plan.getPlans().equals(planText)){
                return plan.getId();
            }
        }
        return 0;
    }


    private void init() {
        plansList =ResourceManager.getListOfPlans();

        progressBar = findViewById(R.id.order_payment_confirm_pb);
        confirmPayment = findViewById(R.id.payment_confirm_button);
        paymentSpinner = findViewById(R.id.payment_method_spinner);
        plansSpinner = findViewById(R.id.payment_invitation_plan_spinner);
        languageSpinner = findViewById(R.id.payment_invitation_language_spinner);
        viewPlans = findViewById(R.id.payment_invitation_plan_btn);
        orderNameET = findViewById(R.id.payment_order_name_confirm_et);

        planAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getArrayListOfPlans(plansList));
        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plansSpinner.setAdapter(planAdapter);

        languageSpinner.setAdapter(getSpinnerAdapter(getArrayListOfLangAndPayment(ResourceManager.getListOfLanguages())));
        paymentSpinner.setAdapter(getSpinnerAdapter(getArrayListOfLangAndPayment(ResourceManager.getListOfPayments())));



    }

    private ArrayList<String> getArrayListOfPlans(List<Plans> plansList) {
        ArrayList<String> alist = new ArrayList<>();
        for(int i=0; i<plansList.size(); i++){
            alist.add(plansList.get(i).getPlans());
        }
        return alist;
    }

    private ArrayList<String> getArrayListOfLangAndPayment(List<PaymentAndLanguageModel> plansList) {

        ArrayList<String> alist = new ArrayList<>();
        for(int i=0; i<plansList.size(); i++){
            alist.add(plansList.get(i).getV());
        }
        return alist;
    }

    private ArrayAdapter getSpinnerAdapter(List<String> alist) {

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, alist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return  adapter;

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
        if (flag){
            Intent in = new Intent(PaymentActivity.this, PaymentConfirmedActivity.class);
            startActivity(in);
            this.finish();
        }else{
            confirmPayment.setClickable(true);
            progressBar.setVisibility(View.GONE);
        }

    }
}


