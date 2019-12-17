package com.example.desafiocontacorrente.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.extras.MySharedPreferences;
import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class StatementPresenter implements StatementContract.Presenter {
    StatementContract.View view;
    private String idUser;

    public StatementPresenter(StatementContract.View view) {
        this.view = view;
    }

    @Override
    public void getBankStatement() {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        SharedPreferences preferences = view.getContext().getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        idUser = preferences.getString("id","não encontrado");
        serviceAccountAp.getBankStatement(5, new ServiceAccountAPI.CallBack<List<Statement>>() {


            @Override
            public void onLoaded(List statements) {
                ArrayList<Statement> list = (ArrayList<Statement>) statements;
                Toast.makeText(view.getContext(),"extrato: "+ list.toString(),Toast.LENGTH_LONG).show();
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
