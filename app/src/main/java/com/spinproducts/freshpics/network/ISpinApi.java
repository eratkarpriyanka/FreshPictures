package com.spinproducts.freshpics.network;

import com.spinproducts.freshpics.models.SpinApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ISpinApi {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("n30si/")
    Call<SpinApiResponse> getPictureList();
}
