package com.example.desafiocontacorrente.contracts;

import android.content.Context;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.model.User;

public class MainContract {

    public interface View{
        Context getContext();
        void initializeViews();
        void setProgress(Boolean visible);
        void setListeners();
        void changeFragment(Fragment fragment);
        void lockDL(boolean lock);
        ActionBarDrawerToggle getToggle();


    }

    public interface Presenter{

    }
}
