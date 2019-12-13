package com.example.desafiocontacorrente.presenter;

import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.StatementContract;

import java.util.List;

public class StatementPresenter implements StatementContract.Presenter {
    StatementContract.View view;

    public StatementPresenter(StatementContract.View view) {
        this.view = view;
    }

    @Override
    public void getBankStatement(int id) {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        serviceAccountAp.getBankStatement(id, new ServiceAccountAPI.CallBack<List>() {
            @Override
            public void onLoaded(List list) {
               view.bindList(list);
            }

            @Override
            public void onFailed(String fail) {

            }

            @Override
            public void noConnection(String noConnection) {

            }
        });
    }
}
