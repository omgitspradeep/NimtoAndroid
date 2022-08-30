package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nimto0.adapters.MyWishersRVAdapter;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.interfaces.onGettingInvitees;
import com.example.nimto0.models.CustomizedInviteeModel;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;

import java.util.ArrayList;
import java.util.List;

public class MyOrderWishersActivity extends AppCompatActivity implements onGettingInvitees {

    RecyclerView recyclerView;
    MyWishersRVAdapter adapter;
    ProgressBar progressBar;
    int orderID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_wishers);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Wishers");

        orderID = getIntent().getExtras().getInt(Constants.ORDER_ID);


        ini();
        getData();

    }

    private void ini() {
        recyclerView = findViewById(R.id.rv_myorders_wishers);
        progressBar = findViewById(R.id.myorder_wishers_progressbar);

    }

    private void getData() {

        if(orderID == ResourceManager.getOrderIdOfInvitees()){
            inflateData(getWishedInvitees(ResourceManager.getAllInviteesData()));
        }else{

            progressBar.setVisibility(View.VISIBLE);
            WebServices.verifyToken(new MainInterface.tokenListener() {

                @Override
                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {

                    if(flag){

                        if(returnWithNewRefresh){
                            Utils.setUserPreferences(MyOrderWishersActivity.this,accessToken, refreshToken);
                        }
                        WebServices.AllInviteesCaller(MyOrderWishersActivity.this, orderID);
                        progressBar.setVisibility(View.VISIBLE);
                    }else{
                        onBackPressed();
                    }

                }
            }, ResourceManager.getUserToken().getAccess(),ResourceManager.getUserToken().getRefresh());

        }
    }

    private List<CustomizedInviteeModel> getWishedInvitees(List<CustomizedInviteeModel> allInvitees) {
        List<CustomizedInviteeModel> wishedInvitees = new ArrayList<>();
        for (int i=0 ; i< allInvitees.size();i++){
            if(!allInvitees.get(i).getWish().equalsIgnoreCase("")){
                // Wish is not empty
                wishedInvitees.add(allInvitees.get(i));
            }
        }
        return  wishedInvitees;

    }

    private void inflateData(List<CustomizedInviteeModel> wishedInvitees) {

        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyWishersRVAdapter(this, wishedInvitees);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
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
            inflateData(getWishedInvitees(listOfInvitees));
            Log.e("Maine","Wisher is updated");
        }else{
            progressBar.setVisibility(View.GONE);
            Utils.displayShortToast(this, "Something went wrong. Try again.");
        }
    }
}