package com.pk.nimto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.pk.nimto.R;
import com.pk.nimto.interfaces.MainInterface;
import com.pk.nimto.utils.Constants;
import com.pk.nimto.utils.ResourceManager;
import com.pk.nimto.utils.Utils;
import com.pk.nimto.utils.WebServices;

import java.util.HashMap;
import java.util.Map;

public class WebViewActivity extends AppCompatActivity {

    ProgressBar progressBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url = getIntent().getExtras().getString(Constants.URL_LINK);
        boolean isData = getIntent().getExtras().getBoolean(Constants.DATA);
        String title = getIntent().getExtras().getString(Constants.WEBVIEW_TITLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        init();

        if(isData){

            WebServices.verifyToken(new MainInterface.tokenListener() {
                @Override
                public void onTokenResponse(boolean flag, String accessToken, String refreshToken, boolean returnWithNewRefresh) {

                    if(flag){
                        if(returnWithNewRefresh){
                            Utils.setUserPreferences(WebViewActivity.this,accessToken, refreshToken);
                        }
                    }

                    webView.setWebViewClient(new WebViewClient());
                    webView.loadUrl(url, getHeaderMap());


                }
            }, ResourceManager.getUserToken().getAccess(), ResourceManager.getUserToken().getRefresh());


        }else{
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        }


    }


    private Map<String, String> getHeaderMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("Authorization", "Bearer "+ResourceManager.getUserToken().getAccess());
        return map;
    }


    private void init() {
        webView = findViewById(R.id.viewas_webview);
        progressBar = findViewById(R.id.viewas_webview_PB);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }







    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url, getHeaderMap());
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }








}



