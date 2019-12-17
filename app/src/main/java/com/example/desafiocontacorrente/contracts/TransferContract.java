package com.example.desafiocontacorrente.contracts;

import android.content.Context;

public class TransferContract {

    public interface View {
        Context getContext();
        void initializeViews();
        void setListeners();
        void transferSuccessfully();
        void showDialog(String nameFrom, String nameTO,String value);
        void displayErrorMessage(String invalid);
    }

    public interface Presenter{
        void transfer(double value);
        void confirmData(String emailTo, String value);
    }
}
