package com.example.desafiocontacorrente.presenter;

import com.example.desafiocontacorrente.api.ServiceAccountAPI.CallBack;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.LoginContract;
import com.example.desafiocontacorrente.model.Status;

/**
 * @author Rafael Lima Nunes de Oliveira
 */
public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;


    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    /**
     *
     * @param email
     * @param password
     */
    @Override
    public void authenticate(String email, String password) {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        serviceAccountAp.checkLogin(email, password, new CallBack<Status>() {
            @Override
            public void onLoaded(Status o) {
                if (o.getStatus()) {
                    view.logInto();
                }
            }

            @Override
            public void onFailed(String fail) {
                view.invalid(fail);

            }

            @Override
            public void noConnection(String noConnection) {
                view.noConnection(noConnection);
            }
        });
    }

}