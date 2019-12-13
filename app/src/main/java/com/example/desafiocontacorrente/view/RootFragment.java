package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.R;

public class RootFragment extends Fragment {

    private ActionBar actionBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBarDrawerToggle = ((MainActivity)getActivity()).getToggle();
        actionBarDrawerToggle.isDrawerIndicatorEnabled();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    protected void setBackButton(Boolean show){
        if(show){
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }

    }

     protected void setTitle(String title){
        if(getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).setTitle(title);

        }
    }
    protected void setProgress(Boolean visible) {
        if(getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).setProgress(visible);

        }

    }

    protected void changeFragment(RootFragment fragment){
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).changeFragment(fragment);
        }

    }
}

