package com.pk.nimto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pk.nimto.R;
import com.pk.nimto.adapters.MyOrdersDetailsRVAdatpter;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.interfaces.onGettingInvitees;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MyOrderDetailActivity extends AppCompatActivity implements onGettingInvitees {

    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    MyOrdersDetailsRVAdatpter myOrdersDetailsRVAdatpter;
    String TAG="MAINE";
    ProgressBar progressBar;
    FloatingActionButton floatingActionButton;
    int orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Details");


        orderID = getIntent().getExtras().getInt(Constants.ORDER_ID);
        init();


        // TO solve issue: App opened when INTERNET connection is off. Then internet is ON. There is no way we could hit api unless
        // 1. app reload & 2. Swipe Refresh

        getData();

        swipeRefreshLayout.setOnRefreshListener(() -> {

            // Fetching data from api
            if (Utils.isConnected(this)){
                WebServices.AllInviteesCaller(MyOrderDetailActivity.this,orderID);

            }else{
                Utils.displayShortToast(this,"Please check your Internet connection.");
                //setting Refreshing to false
                swipeRefreshLayout.setRefreshing(false);
            }

        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "All the invitees will be downloaded soon.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }

    private void getData() {
        if(orderID == ResourceManager.getOrderIdOfInvitees()){
            inflateAllData();
        }else{
            progressBar.setVisibility(View.VISIBLE);
            WebServices.verifyToken(new MainInterface.tokenListener() {

                @Override
                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {

                    if(flag){

                        if(returnWithNewRefresh){
                            Utils.setUserPreferences(MyOrderDetailActivity.this,accessToken, refreshToken);
                        }
                        WebServices.AllInviteesCaller(MyOrderDetailActivity.this, orderID);
                        progressBar.setVisibility(View.VISIBLE);
                    }else{
                        onBackPressed();
                    }

                }
            }, ResourceManager.getUserToken().getAccess(),ResourceManager.getUserToken().getRefresh());

        }
    }

    private void init() {
        swipeRefreshLayout = ( SwipeRefreshLayout ) findViewById( R.id.swiperefreshlayout ) ;
        progressBar = findViewById(R.id.myorder_details_progressbar);
        recyclerView = findViewById(R.id.rv_myorders_invitees);
        floatingActionButton = findViewById(R.id.myorder_invitees_download_fab);
    }

    private void inflateAllData() {
        myOrdersDetailsRVAdatpter = new MyOrdersDetailsRVAdatpter(this);
        recyclerView.setAdapter(myOrdersDetailsRVAdatpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        progressBar.setVisibility(View.GONE);

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
        if(isSuccessful) {
            //Changing the list items when refresh
            inflateAllData();
            //setting Refreshing to false
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    public void onNameClick(CustomizedInviteeModel selectedInvitee) {
        Intent intent = new Intent(this, InviteeCreateUpdateActivity.class);
        intent.putExtra(Constants.ORDER_ID,selectedInvitee.getOrder());
        intent.putExtra(Constants.INTENT_FROM,1);
        intent.putExtra(Constants.TASK,Constants.VIEW);
        startActivity(intent);

        Toast.makeText(this, "Address: "+selectedInvitee.getAddress() , Toast.LENGTH_LONG).show();
    }

}