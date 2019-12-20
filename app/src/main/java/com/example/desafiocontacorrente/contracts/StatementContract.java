package com.example.desafiocontacorrente.contracts;

import android.content.Context;

import java.util.List;

public class StatementContract{

    public interface View{
        Context getContext();
        void showList(List list);
        void initializeViews();
        void showErrorMessage(String error);
    }

    public interface Presenter{
        void getBankStatement();

    }
}
