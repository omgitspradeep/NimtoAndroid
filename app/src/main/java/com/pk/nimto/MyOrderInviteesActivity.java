package com.pk.nimto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.pk.nimto.R;
import com.pk.nimto.adapters.MyInviteesRVAdapter;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.interfaces.onGettingInvitees;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyOrderInviteesActivity extends AppCompatActivity implements onGettingInvitees {

    RecyclerView recyclerView;
    MyInviteesRVAdapter adapter;
    FloatingActionButton fab;
    ProgressBar progressBar;
    int orderID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_invitees);
        orderID = getIntent().getExtras().getInt(Constants.ORDER_ID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Guests");

        ini();
        getData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(MyOrderInviteesActivity.this, InviteeCreateUpdateActivity.class);
                in.putExtra(Constants.ORDER_ID,ResourceManager.getOrderIdOfInvitees());
                in.putExtra(Constants.INTENT_FROM,2);
                in.putExtra(Constants.TASK,Constants.CREATE);
                startActivity(in);
            }
        });

    }

    private void getData() {

        if(orderID == ResourceManager.getOrderIdOfInvitees()){
            inflateData(ResourceManager.getAllInviteesData());
        }else{
            progressBar.setVisibility(View.VISIBLE);
            WebServices.verifyToken(new MainInterface.tokenListener() {

                @Override
                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {

                    if(flag){

                        if(returnWithNewRefresh){
                            Utils.setUserPreferences(MyOrderInviteesActivity.this,accessToken, refreshToken);
                        }
                        WebServices.AllInviteesCaller(MyOrderInviteesActivity.this, orderID);
                        progressBar.setVisibility(View.VISIBLE);
                    }else{
                        onBackPressed();
                    }

                }
            }, ResourceManager.getUserToken().getAccess(),ResourceManager.getUserToken().getRefresh());

        }



    }

    private void inflateData(ArrayList<CustomizedInviteeModel> listOfInvitees) {

        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyInviteesRVAdapter(this, listOfInvitees, progressBar);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    private void ini() {
        fab = findViewById(R.id.myorder_invitees_create_fab);
        recyclerView = findViewById(R.id.rv_myorders_invitees);
        progressBar = findViewById(R.id.myorder_invitees_progressbar);

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
    public void isInviteesReceived(boolean isSuccessful, ArrayList<CustomizedInviteeModel> listOfInvitees) {
        if(isSuccessful){
            inflateData(listOfInvitees);
            progressBar.setVisibility(View.GONE);
        }else{

            onBackPressed();
        }
    }
}