package com.example.desafiocontacorrente.presenter;

import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.HomeContract;
import com.example.desafiocontacorrente.model.User;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }


    @Override
    public void getUser(String email) {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        serviceAccountAp.getUserData(email, new ServiceAccountAPI.CallBack<User>() {
            @Override
            public void onLoaded(User o) {
                view.showUserInformation(o);
            }
            @Override
            public void onFailed(String fail) {
                view.errorShowInformation(fail);
            }


            @Override
            public void noConnection(String noConnection) {
                view.noConnection(noConnection);

            }
        });
    }
}
