package com.example.desafiocontacorrente.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitConfig {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.yourgps.com.br/api/";


    static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
