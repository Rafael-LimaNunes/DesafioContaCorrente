package com.example.desafiocontacorrente.contracts;

import com.example.desafiocontacorrente.model.User;

public class HomeContract {

    public interface View {

        void noConnection(String noConnection);
        void showUserInformation(User user);
        void initializeViews();
        void setListeners();
        void errorShowInformation(String fail);
    }

    public interface Presenter{

        void getUser(String email);


    }



}
