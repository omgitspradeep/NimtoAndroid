package com.example.nimto0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nimto0.interfaces.commonListener;
import com.example.nimto0.models.ThemeDetail;
import com.example.nimto0.utils.Constants;
import com.example.nimto0.utils.ResourceManager;
import com.example.nimto0.utils.WebServices;

public class ThemeDetailActivity extends AppCompatActivity {

    TextView theme_name, theme_color, theme_desc, theme_price, theme_category;
    ImageView theme_image;
    Button  viewAs, addToCart;
    ThemeDetail selectedTheme;
    int selectedThemeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Theme in Detail");


        // PARMANENT

        selectedThemeId=(int)getIntent().getSerializableExtra("selectedObjectPosition");
        selectedTheme = ResourceManager.getPaginatedThemes().getListOfThemes().get(selectedThemeId);

        init();


    }

    public void init(){
        theme_image = findViewById(R.id.theme_detail_image);
        theme_name = findViewById(R.id.theme_detail_name);
        theme_desc = findViewById(R.id.theme_detail_desc);
        theme_color = findViewById(R.id.theme_color);
        theme_category = findViewById(R.id.theme_category);
        theme_price = findViewById(R.id.theme_price);

        viewAs = findViewById(R.id.theme_viewas_btn);
        addToCart = findViewById(R.id.theme_add_to_cart_btn);





        //Glide.with(this).load(selectedTheme.getTheme_image()).into(theme_image);

        try{
            if(!selectedTheme.getTheme_image().equals("")){
                Glide.with(this)
                        .load(selectedTheme.getTheme_image())
                        .centerCrop()
                        .placeholder(R.drawable.book_loading)
                        .into(theme_image);
            }

        }catch(Exception e){
            Log.e("ERROR",e.toString());
            theme_image.setImageResource(R.drawable.book_not_avl);
        }


        theme_name.setText(selectedTheme.getTheme_name());
        theme_desc.setText(selectedTheme.getDescription());
        theme_color.setText(selectedTheme.getTheme_color());
        theme_price.setText(String.valueOf(selectedTheme.getTheme_price()));
        theme_category.setText(selectedTheme.getTheme_type());


        viewAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sample/theme_id/theme_name
                String urlLink = Constants.baseURL+"/sampleapi/"+selectedTheme.getId()+"/"+selectedTheme.getTheme_name();

                Intent in = new Intent(ThemeDetailActivity.this, WebViewActivity.class);
                in.putExtra(Constants.URL_LINK, urlLink );
                in.putExtra(Constants.WEBVIEW_TITLE,"Theme Sample|"+selectedTheme.getTheme_name());
                startActivity(in);
            }
        });

       addToCart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addToCart.setClickable(false);
               viewAs.setClickable(false);

               WebServices.getPlanLangAndPayments(new commonListener() {
                   @Override
                   public void onCommonResponse(boolean flag) {
                       if(flag){
                           Intent in = new Intent(ThemeDetailActivity.this, PaymentActivity.class);
                           in.putExtra(Constants.THEME_ID, selectedTheme.getId());
                           startActivity(in);
                       }else{
                           addToCart.setClickable(true);
                           viewAs.setClickable(true);
                       }

                   }
               });


           }
       });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}