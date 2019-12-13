package com.example.desafiocontacorrente.contracts;

import android.content.Context;

public class LoginContract {

    public interface View{
        void logInto();
        void invalid(String fail);
        void initializeViews();
        void setListeners();
        Context getContext();
        void noConnection(String noConnection);
    }

    public interface Presenter{

        void authenticate(String email, String password);


    }

}
