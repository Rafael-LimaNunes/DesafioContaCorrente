package com.example.desafiocontacorrente.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.model.Statement;

import java.util.List;

public class StatementPresenter implements StatementContract.Presenter {
    private StatementContract.View view;

    public StatementPresenter(StatementContract.View view) {
        this.view = view;
    }

    @Override
    public void getBankStatement() {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        SharedPreferences preferences = view.getContext().getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        String idUser = preferences.getString("id", "não encontrado");
        serviceAccountAp.getBankStatement(idUser, new ServiceAccountAPI.CallBack<List<Statement>>() {
            @Override
            public void onLoaded(List statements) {
                view.showList(statements);
            }

            @Override
            public void onFailed(String fail) {
               view.showErrorMessage(fail);
            }

            @Override
            public void noConnection(String noConnection) {
                view.showErrorMessage(noConnection);

            }
        });
    }
}
