package com.example.desafiocontacorrente.contracts;
public class LoginContract {

    public interface View{
        void logInto();
        void invalid(String fail);
        void noConnection(String noConnection);
        void initializeViews();
        void setListeners();

    }

    public interface Presenter{

        void authenticate(String email, String password);


    }

}
