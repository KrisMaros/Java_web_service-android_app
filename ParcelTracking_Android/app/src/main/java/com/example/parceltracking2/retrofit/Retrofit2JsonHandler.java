package com.example.parceltracking2.retrofit;

import com.example.parceltracking2.dto.CustomerDTO;
import com.example.parceltracking2.dto.DriverDTO;
import com.example.parceltracking2.dto.ParcelDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface Retrofit2JsonHandler {

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:26893/Parcel_Tracking-war/webresources/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @GET("parcel")
    Call<List<ParcelDTO>> getParcel();

    @GET("customer")
    Call<List<CustomerDTO>> getCustomer();

    @PUT("parcel")
    Call<ParcelDTO> bookParcelReturn(@Body ParcelDTO parcDTO);

    @PUT("parcel_driver")
    Call<ParcelDTO> updateParcelStatus(@Body ParcelDTO parcDTO);

    @PUT("customer")
    Call<CustomerDTO> loginCustomer(@Body CustomerDTO custDTO);

    @PUT("driver")
    Call<DriverDTO> loginDriver(@Body DriverDTO drivDTO);
}