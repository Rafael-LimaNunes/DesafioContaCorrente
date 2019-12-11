package com.example.desafiocontacorrente.contracts;

import java.util.List;

public class StatementContract{

    public interface View{
        void showList(List list);
        void initialize();

    }

    public interface Presenter{
        void getBankStatement(int id);

    }
}
