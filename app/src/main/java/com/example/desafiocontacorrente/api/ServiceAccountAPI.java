package com.example.desafiocontacorrente.api;

import com.example.desafiocontacorrente.model.Status;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface ServiceAccountAPI {

    @POST("./check-login")
    Call<Status> checkLogin(@Field("email")String email, @Field("password")String password);

}
