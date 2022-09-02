package com.pk.nimto.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pk.nimto.MyOrderDetailActivity;
import com.pk.nimto.R;
import com.pk.nimto.models.CustomizedInviteeModel;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;

import java.util.List;

public class MyOrdersDetailsRVAdatpter extends RecyclerView.Adapter<MyOrdersDetailsRVAdatpter.ViewHolder>{

    private List<CustomizedInviteeModel> mData;
    private LayoutInflater mInflater;
    private MyOrderDetailActivity mContext;

    public MyOrdersDetailsRVAdatpter(MyOrderDetailActivity context) {
        this.mData = ResourceManager.getAllInviteesData();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row_myorders_details, parent, false);
        return new MyOrdersDetailsRVAdatpter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomizedInviteeModel invitees = mData.get(position);

        holder.sn.setText((position+1)+"");
        holder.name.setText(invitees.getName());
        holder.url.setText(invitees.getUrl());
        holder.status.setText(invitees.getStatus());


        // Temporary
        if(invitees.getWish().equals("")){
            holder.wishMessage.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
        }else{
            holder.wishMessage.setVisibility(View.VISIBLE);
            holder.wishMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.displayPopUp(invitees.getWish(),mContext);
                }
            });
        }

        /*
        // Parmanent
        if(invitees.isVisited()){
            holder.wishMessage.setVisibility(View.VISIBLE);
            if(invitees.getWish().equals("")){
                holder.wishMessage.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
            }else{
                holder.wishMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Utils.displayPopUp(invitees.getWish(),mContext);
                    }
                });
            }
        }

        */

        holder.url.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Log.e("MAINE","Inside DOUBLE CLICK");
                ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Invitee url", invitees.getUrl());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(mContext, "URL copied to clipboard", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickListener(invitees);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, invitees.getUrl());

                try {
                    mContext.startActivity(Intent.createChooser(intent, "Select an action"));
                } catch (android.content.ActivityNotFoundException ex) {
                    // (handle error)
                    Toast.makeText(mContext, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton share,wishMessage;
        TextView name,url,sn,status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.inviteeNameTV);
            url = itemView.findViewById(R.id.inviteeUrlTV);
            share=itemView.findViewById(R.id.shareBTN);
            sn=itemView.findViewById(R.id.sn);
            wishMessage=itemView.findViewById(R.id.wishMessage);
            status = itemView.findViewById(R.id.invitationStatusTV);
        }

        // allows clicks events to be caught

    }

    public void setClickListener(CustomizedInviteeModel dm) {
        ResourceManager.setSelectedInvitee(dm);
        mContext.onNameClick(dm);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface NameClickListener {
        void onNameClick(CustomizedInviteeModel dm);
    }
}
