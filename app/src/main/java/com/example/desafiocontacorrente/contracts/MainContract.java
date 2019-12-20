package com.example.desafiocontacorrente.contracts;

import android.content.Context;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainContract {

    public interface View{
        Context getContext();
        Toolbar getToolbar();
        DrawerLayout getDrawerLayout();
        void initializeViews();
        void setListeners();
        void changeFragment(Fragment fragment);
        void lockDL(boolean lock);
        ActionBarDrawerToggle getToggle();


    }

}
