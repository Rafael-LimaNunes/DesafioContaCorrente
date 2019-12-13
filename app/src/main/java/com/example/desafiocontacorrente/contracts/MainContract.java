package com.example.desafiocontacorrente.contracts;

import android.content.Context;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

public class MainContract {

    public interface View{
        Context getContext();
        void initializeViews();
        void setProgress(Boolean visible);
        void setListeners();
        void initializeNavHeader();
        void changeFragment(Fragment fragment);
        void displayErrorMessage();
        void lockDL();
        ActionBarDrawerToggle getToggle();


    }

    public interface Presenter{

    }
}
