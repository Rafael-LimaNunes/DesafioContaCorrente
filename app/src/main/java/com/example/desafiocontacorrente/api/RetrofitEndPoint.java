package com.example.desafiocontacorrente.api;

import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.model.Status;
import com.example.desafiocontacorrente.model.Transfer;
import com.example.desafiocontacorrente.model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {
    @FormUrlEncoded
    @POST("./check-login")
    Call<Status> checkLogin(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("./get-user")
    Call<User> getUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("./get-bank-statement")
    Call<List<Statement>> getBankStatement(@Field("id") int id);

    @FormUrlEncoded
    @POST("./transfer")
    Call<Transfer> transfer(@Field("id_user_from")int idUserFrom, @Field("id_user_to") int idUserTo,@Field("value")double value);
}
