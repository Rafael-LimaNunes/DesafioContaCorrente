package com.example.desafiocontacorrente.presenter;

import android.widget.EditText;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.Contract;
import com.example.desafiocontacorrente.api.RetrofitConfig;
import com.example.desafiocontacorrente.model.Status;


import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements Contract.Presenter {

    Contract.View view;
    EditText email;
    EditText password;

    public LoginPresenter(Contract.View view, EditText email, EditText password) {
        this.view = view;
        this.email = email;
        this.password = password;
    }

    @Override
    @OnClick(R.id.btnEnter)
    public void authenticate() {
        Call<Status> call = new RetrofitConfig().getServiceAccountAPI().checkLogin(email.getText().toString(),password.getText().toString());
        call.enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                        view.logInto();
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                view.fail();
            }
        });



    }
}
