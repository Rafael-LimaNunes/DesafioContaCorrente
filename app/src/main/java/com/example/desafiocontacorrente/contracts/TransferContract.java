package com.example.desafiocontacorrente.contracts;

public class TransferContract {

    public interface View {
        void initializeViews();
        void setListeners();
        void transferSuccessfully();


    }

    public interface Presenter{
        void transfer(int idUserFrom, double value);

    }
}
