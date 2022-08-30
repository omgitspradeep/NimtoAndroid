package com.example.nimto0.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nimto0.MyOrderWishersActivity;
import com.example.nimto0.R;
import com.example.nimto0.interfaces.MainInterface;
import com.example.nimto0.models.CustomizedInviteeModel;
import com.example.nimto0.models.SendData.WisherObjectU;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.Utils;
import com.example.nimto0.utils.WebServices;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MyWishersRVAdapter  extends RecyclerView.Adapter<MyWishersRVAdapter.ViewHolder>{

    private List<CustomizedInviteeModel> mData;
    private LayoutInflater mInflater;
    private MyOrderWishersActivity mContext;
    CustomizedInviteeModel customizedInviteeModel =null;
    public MyWishersRVAdapter(MyOrderWishersActivity context, List<CustomizedInviteeModel> data) {
        mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_mywishers, parent, false);
        return new MyWishersRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        customizedInviteeModel = mData.get(position);
        holder.sn.setText(position+1+".");
        holder.myGuestName.setText(customizedInviteeModel.getName());
        String wish = customizedInviteeModel.getWish();
        holder.posted.setText(getFormattedDate(customizedInviteeModel.getWishPosted()));
        holder.wishes.setText(wish);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a popup dialog where wish can be edited and Button for updation.


                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                final EditText edittext = new EditText(mContext);
                alert.setTitle("Update the Wish");
                edittext.setText(wish);

                alert.setView(edittext);

                alert.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                        String updatedText = edittext.getText().toString();
                        if(!updatedText.equals("")){
                            WebServices.verifyToken(new MainInterface.tokenListener() {
                                @Override
                                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                    if(flag){
                                        mContext.getProgressBar().setVisibility(View.VISIBLE);
                                        if(returnWithNewRefresh){
                                            Utils.setUserPreferences(mContext,accessToken,refreshToken);
                                        }
                                        WisherObjectU newWishObject = customizedInviteeModel.getWisherObjectForUpdate();
                                        newWishObject.setWishes(updatedText);
                                        WebServices.updatingWisher(mContext,accessToken, newWishObject);
                                    }
                                }
                            }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());
                        }
                        else{
                            edittext.setError("Wish cannot be null");
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                alert.show();
            }
        });



        holder.delete.setOnClickListener(v -> new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this Wish!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        WebServices.verifyToken(new MainInterface.tokenListener() {
                            @Override
                            public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {
                                if(flag){
                                    if(returnWithNewRefresh){
                                        Utils.setUserPreferences(mContext,accessToken,refreshToken);
                                    }
                                    mContext.getProgressBar().setVisibility(View.VISIBLE);
                                    WebServices.deletingWisher(mContext,accessToken,customizedInviteeModel.getWishId(), customizedInviteeModel.getOrder());
                                }
                            }
                        }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());

                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show());


    }

    private String getFormattedDate(String oldDate) {
        return oldDate.substring(11,19)+" ("+oldDate.substring(0,10)+")";
    }

    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myGuestName, sn, wishes, posted;
        ImageButton edit, delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sn = itemView.findViewById(R.id.sn_myorder_wisher);
            myGuestName = itemView.findViewById(R.id.name_myorder_wisher);
            wishes = itemView.findViewById(R.id.wishes_myorder_wisher);
            posted = itemView.findViewById(R.id.wishes_myorder_posted);
            edit = itemView.findViewById(R.id.viewas_myorder_wisher_edit);
            delete = itemView.findViewById(R.id.viewas_myorder_wisher_delete);
        }
    }
}
