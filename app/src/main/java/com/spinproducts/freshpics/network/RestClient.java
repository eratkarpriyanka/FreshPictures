package com.spinproducts.freshpics.network;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    public RestClient(){

    }

    public static ISpinApi createService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ISpinApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ISpinApi apiInterface = retrofit.create(ISpinApi.class);
        return apiInterface;
    }
}
