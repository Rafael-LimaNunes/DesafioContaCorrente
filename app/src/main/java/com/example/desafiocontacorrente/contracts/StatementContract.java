package com.example.desafiocontacorrente.contracts;

import com.example.desafiocontacorrente.model.Statement;

import java.util.List;

public class StatementContract{

    public interface View{
        void showList(List list);
        void initializeViews();
        void setListener();
        void bindList(List<Statement> statements);
        void errorMessage();
        void bindEmpty();


    }

    public interface Presenter{
        void getBankStatement(int id);

    }
}
