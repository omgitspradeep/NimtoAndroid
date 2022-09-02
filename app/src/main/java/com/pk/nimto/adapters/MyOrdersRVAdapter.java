package com.pk.nimto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.nimto.MainActivity;
import com.pk.nimto.MyOrderDetailActivity;
import com.pk.nimto.MyOrderInviteesActivity;
import com.pk.nimto.MyOrderWishersActivity;
import com.pk.nimto.MyOrderedThemeBirthDayInformationActivity;
import com.pk.nimto.MyOrderedThemeMarriageInformationActivity;
import com.pk.nimto.R;
import com.pk.nimto.WebViewActivity;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.interfaces.commonListener;
import com.pk.nimto.models.CustOrderModel;
import com.pk.nimto.models.ThemeDetail;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyOrdersRVAdapter extends RecyclerView.Adapter<MyOrdersRVAdapter.ViewHolder> implements commonListener  {


    private List<CustOrderModel> mData;
    private LayoutInflater mInflater;
    private  Context mContext;
    private ProgressBar progressBar;
    private Intent intent;
    private  MainInterface.orderDeleteListner mListner;

    public MyOrdersRVAdapter(Context context, List<CustOrderModel> data, MainInterface.orderDeleteListner listener,ProgressBar progressBar) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.progressBar = progressBar;
        this.mContext= context;
        this.mListner= listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_myorders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustOrderModel customerOrder = mData.get(position);
        String orderName =customerOrder.getOrder_name();
        holder.sn.setText(position+1+".");
        holder.myOrderName.setText(orderName);

        holder.myOrderName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isConnected(mContext)){
                    Intent in = new Intent(mContext, MyOrderDetailActivity.class);
                    in.putExtra(Constants.ORDER_ID, customerOrder.getId());
                    mContext.startActivity(in);
                }else{
                    Utils.displayShortToast(mContext,"No Internet");
                }

            }
        });

        holder.viewAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(Constants.DATA,true);
                intent.putExtra(Constants.URL_LINK,Constants.viewAsApi+customerOrder.getId()+"/");
                intent.putExtra(Constants.WEBVIEW_TITLE,orderName+"|View As");
                mContext.startActivity(intent);


            }
        });

        holder.analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(Constants.DATA,true);
                intent.putExtra(Constants.URL_LINK,Constants.dashboardApi+customerOrder.getId()+"/");
                intent.putExtra(Constants.WEBVIEW_TITLE,orderName+"|Dashboard");
                mContext.startActivity(intent);
            }
        });

        holder.moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(mContext, holder.moreOptions);
                popup.inflate(R.menu.order_menu);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.order_more_menu_invitees:

                                intent = new Intent(mContext, MyOrderInviteesActivity.class);
                                goToNextPhase(customerOrder.getId(), intent);

                                return true;
                            case R.id.order_more_menu_wishers:
                                intent = new Intent(mContext, MyOrderWishersActivity.class);
                                goToNextPhase(customerOrder.getId(), intent);

                                return true;
                            case R.id. order_more_menu_delete:
                                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Are you sure?")
                                        .setContentText("Won't be able to recover this Order!")
                                        .setConfirmText("Yes,delete it!")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                // Do your action here
                                                sweetAlertDialog.dismissWithAnimation();
                                                progressBar.setVisibility(View.VISIBLE);
                                                WebServices.verifyToken(new MainInterface.tokenListener() {
                                                    @Override
                                                    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                                        if(flag){
                                                            if(returnWithNewRefresh){
                                                                Utils.setUserPreferences(mContext,accessToken, refreshToken);
                                                            }
                                                            WebServices.deleteOrder(new commonListener() {
                                                                @Override
                                                                public void onCommonResponse(boolean flag) {
                                                                    if(flag){
                                                                        deleteOrderFromResourceAndReload(customerOrder.getId());
                                                                        Log.e("Maine", "Order is deleted.");
                                                                    }else{
                                                                        Log.e("Maine", "Order is not deleted.");
                                                                    }

                                                                }
                                                            }, "Bearer " +accessToken, customerOrder.getId());

                                                        }else{
                                                            progressBar.setVisibility(View.GONE);
                                                        }

                                                    }
                                                },ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());

                                            }

                                        })
                                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();





                                return true;
                            case R.id.order_more_menu_edit:
                                ThemeDetail selectedTheme = ResourceManager.getThemeDetail(customerOrder.getSelected_theme());
                                progressBar.setVisibility(View.VISIBLE);


                                WebServices.verifyToken(new MainInterface.tokenListener() {
                                    @Override
                                    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                        if(flag){
                                            if(returnWithNewRefresh){
                                                Utils.setUserPreferences(mContext, accessToken, refreshToken);
                                            }

                                            if(selectedTheme.getTheme_type().equalsIgnoreCase(Constants.MARRIAGE)) {
                                                // Now call for all data
                                                WebServices.getMarriageAllData(MyOrdersRVAdapter.this,"Bearer "+accessToken, customerOrder.getId());

                                            }else if(selectedTheme.getTheme_type().equalsIgnoreCase(Constants.BIRTHDAY)){

                                                mContext.startActivity(new Intent(mContext, MyOrderedThemeBirthDayInformationActivity.class));
                                                progressBar.setVisibility(View.GONE);
                                            }else{
                                                mContext.startActivity(new Intent(mContext, MainActivity.class));
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }else{
                                            progressBar.setVisibility(View.GONE);
                                        }

                                    }
                                },ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());

                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show();
            }
        });

    }

    private void deleteOrderFromResourceAndReload(int id) {
        for (int i=0; i<=mData.size();i++){
            if(mData.get(i).getId()==id){
                mData.remove(i);
                break; // A unlabeled break is enough. You don't need a labeled break here.
            }
        }
        progressBar.setVisibility(View.GONE);
        mListner.onOrderDeleteResponse(true,mData);
    }



    private void goToNextPhase(int orderID, Intent intent) {
        if(Utils.isConnected(mContext)){
            intent.putExtra(Constants.ORDER_ID,orderID);
            mContext.startActivity(intent);

        }else{
            Utils.displayShortToast(mContext,"No Internet");
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onCommonResponse(boolean flag) {
        progressBar.setVisibility(View.GONE);
        if(flag){
            intent = new Intent(mContext, MyOrderedThemeMarriageInformationActivity.class);
            mContext.startActivity(intent);
        }else{
            Log.e("Maine", "Something went wrong try again.");
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myOrderName, sn;
        ImageButton viewAs, analytics, moreOptions;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.rv_myorders_card_row);
            sn = itemView.findViewById(R.id.sn_myorder);
            myOrderName = itemView.findViewById(R.id.name_myorder);
            viewAs = itemView.findViewById(R.id.viewas_myorder);

            analytics = itemView.findViewById(R.id.analytics_myorder);
            moreOptions = itemView.findViewById(R.id.more_options_myorder);
            //invitees = itemView.findViewById(R.id.invitees_myorder);
            //wishers = itemView.findViewById(R.id.wishers_myorder);
            //updateData = itemView.findViewById(R.id.information_myorder_edit);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
