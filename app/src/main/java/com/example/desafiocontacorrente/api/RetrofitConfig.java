package com.example.desafiocontacorrente.api;

import com.example.desafiocontacorrente.api.ServiceAccountAPI;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl("https://www.yourgps.com.br/api/").addConverterFactory(JacksonConverterFactory.create()).build();

    }

    public ServiceAccountAPI getServiceAccountAPI() {
        return this.retrofit.create(ServiceAccountAPI.class);
    }
}
