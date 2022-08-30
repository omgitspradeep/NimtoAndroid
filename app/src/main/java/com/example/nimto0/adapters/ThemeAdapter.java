package com.example.nimto0.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nimto0.ThemeDetailActivity;
import com.example.nimto0.R;
import com.example.nimto0.models.ThemeDetail;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {

    ArrayList<ThemeDetail> allThemes;
    LayoutInflater layoutInflater;
    Context context;
    ThemeDetail theme;
    int calledFragment;
    public ThemeAdapter(ArrayList<ThemeDetail> listOfThemes, Context ctx) {
        this.allThemes = listOfThemes;
        this.layoutInflater = LayoutInflater.from(ctx);
        this.context = ctx;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_grid_adapter_layout,parent,false);
        return new ViewHolder((view));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        theme =allThemes.get(position);
        //Glide.with(context).load(R.drawable.kim).into(holder.imageView);
        //  fitCenter() or centerCrop() is used to fit image into imageview


        Glide.with(context)
                .load(theme.getTheme_image())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(holder.imageView);


        //Puts string image into Imageview.
        //Picasso.get().load(book.getImage()).into(holder.imageView);
        holder.title.setText(theme.getTheme_name());
        holder.details.setText(theme.getDescription());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResourceManager.setSelectedTheme(theme);
                Intent intent= new Intent(context, ThemeDetailActivity.class);
                intent.putExtra("selectedObjectPosition",holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allThemes.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, details;
        ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.theme_name);
        details = itemView.findViewById(R.id.theme_details);
        imageView = itemView.findViewById(R.id.theme_image);
    }
}

}


 /*  if(calledFragment==Constants.bookDetailCallFromMyBooks){
           ResourceManager.setSelectedBook(book);
           }
           Intent intent= new Intent(context, BookDetailActivity.class);
        intent.putExtra("selectedObjectPosition",position);
        intent.putExtra("calledFrom", calledFragment);
        context.startActivity(intent);*/