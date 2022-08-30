package com.example.nimto0.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nimto0.InviteeCreateUpdateActivity;
import com.example.nimto0.MyOrderInviteesActivity;
import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.models.CustomizedInviteeModel;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.WebServices;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyInviteesRVAdapter  extends RecyclerView.Adapter<MyInviteesRVAdapter.ViewHolder>{

    private List<CustomizedInviteeModel> mData;
    private LayoutInflater mInflater;
    private MyOrderInviteesActivity mContext;
    private ProgressBar progressBar;
    public MyInviteesRVAdapter(MyOrderInviteesActivity context, List<CustomizedInviteeModel> data, ProgressBar progressBar) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.progressBar = progressBar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_myinvitees, parent, false);
        return new MyInviteesRVAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomizedInviteeModel data = mData.get(position);
        holder.sn.setText(position+1+".");
        holder.myGuestName.setText(data.getName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResourceManager.setSelectedInvitee(data);
                Intent in =  new Intent(mContext, InviteeCreateUpdateActivity.class);
                in.putExtra(Constants.ORDER_ID,data.getOrder());
                in.putExtra(Constants.INTENT_FROM,2);
                in.putExtra(Constants.TASK,Constants.UPDATE);
                mContext.startActivity(in);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this Invitee!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {

                                progressBar.setVisibility(View.VISIBLE);
                                WebServices.verifyToken(new MainInterface.tokenListener() {
                                    @Override
                                    public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        if(flag){
                                            WebServices.deletingInvitee(mContext,accessToken,data.getId(), data.getOrder());
                                        }else{
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                },ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());


                            }
                            // Do your action here
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();






            }
        });





    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myGuestName, sn;
        ImageButton edit, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sn = itemView.findViewById(R.id.sn_myorder_invitees);
            myGuestName = itemView.findViewById(R.id.name_myorder_guest_name);
            edit = itemView.findViewById(R.id.viewas_myorder_invitee_edit);
            delete = itemView.findViewById(R.id.viewas_myorder_invitee_delete);


        }
    }
}
