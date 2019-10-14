package com.parth.retrofitexample.service;

import com.parth.retrofitexample.Info;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {

    @GET("countries")
    Call<Info> getResult();

}
