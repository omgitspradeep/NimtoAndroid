package com.pk.nimto.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;


    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getClientInvitee(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    public static Retrofit getTodoClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.tempBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
