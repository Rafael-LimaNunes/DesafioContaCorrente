package com.example.desafiocontacorrente.api;

import android.content.Context;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.model.Status;
import com.example.desafiocontacorrente.model.Transfer;
import com.example.desafiocontacorrente.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.desafiocontacorrente.R.string.invalid;

public class ServiceAccountApImpl implements ServiceAccountAPI{
    private RetrofitEndPoint retrofitEndPoint;


    public ServiceAccountApImpl(){
    retrofitEndPoint = new RetrofitConfig().getClient().create(RetrofitEndPoint.class);
}

    @Override
    public void checkLogin(String email, String password, CallBack callBack) {
        Call<Status> call = retrofitEndPoint.checkLogin(email,password);
        call.enqueue(new Callback<Status>(){
            public void onResponse(Call<Status> call, Response<Status> response) {
                Status status = response.body();
                if(response.code() ==200) {
                    callBack.onLoaded(status);
                }else{
                    callBack.onFailed(String.valueOf(invalid));
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable throwable) {
                callBack.noConnection(String.valueOf(R.string.noConnection));

            }
        });

    }

    @Override
    public void getUserData(String email, CallBack callBack) {
        Call<User> call = retrofitEndPoint.getUser(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    callBack.onLoaded(user);

                }else{
                    callBack.onFailed("Error Loading user");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                   callBack.noConnection(throwable.getMessage());
            }
        });

    }

    @Override
    public void getBankStatement(String id, CallBack callback) {
        Call<List<Statement>> call = retrofitEndPoint.getBankStatement(id);
        call.enqueue(new Callback<List<Statement>>() {
            @Override
            public void onResponse(Call<List<Statement>> call, Response<List<Statement>> response) {
                if (response.code()==200){
                    List result = new ArrayList();
                    result.add(response.body());
                    callback.onLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Statement>> call, Throwable t) {
                callback.onFailed("Bacon");
            }
        });
    }

    @Override
    public void transfer(int idUserFrom, int idUserTo, double valueTransfer, CallBack callBack) {
            Call<Transfer> call = retrofitEndPoint.transfer(idUserFrom, idUserTo,valueTransfer);
            call.enqueue(new Callback<Transfer>() {
                @Override
                public void onResponse(Call<Transfer> call, Response<Transfer> response) {
                    if(response.code()==200){
                        Transfer transfer = response.body();
                        callBack.onLoaded(transfer);
                    }
                }

                @Override
                public void onFailure(Call<Transfer> call, Throwable t) {
                    callBack.onFailed("Se conexão para Extrato");
                }
            });
        }




}
