package com.example.desafiocontacorrente.contracts;

public class TransferContract {

    public interface View {
        void initializeViews();
        void setListeners();
        void transferSuccessfully();


    }

    public interface Presenter{
        void transfer(int idUserTo,int idUserFrom, double value);

    }
}
