package com.example.desafiocontacorrente.contracts;

public class TransferContract {

    public interface View {
        void initializeViews();
        void setListeners();
        void transferSuccessfully();
        void showErrorMessage();
        void showDialog(String nameFrom, String nameTO,String value);
        void noConnectiion();


    }

    public interface Presenter{
        void transfer(int idUserTo,int idUserFrom, double value);

    }
}
