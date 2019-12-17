package com.example.desafiocontacorrente.contracts;

import android.content.Context;

import com.example.desafiocontacorrente.model.Statement;

import java.util.List;

public class StatementContract{

    public interface View{
        Context getContext();
        void showList(List list);
        void initializeViews();
        void bindEmpty();


    }

    public interface Presenter{
        void getBankStatement();

    }
}
