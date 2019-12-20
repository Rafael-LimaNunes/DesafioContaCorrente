package com.example.desafiocontacorrente.extras;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.view.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class RootFragment extends Fragment {

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        actionBarDrawerToggle = ((MainActivity)getActivity()).getToggle();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    protected void setBackButton(Boolean show){
        if(show){
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }else{
            actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_toc_black_24dp);
        }

    }

     protected void setTitle(String title){
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).setTitleToolbar(title);

        }
    }

    protected void changeFragment(Fragment fragment){
        if(getActivity() instanceof MainActivity)
            ((MainActivity) getActivity()).changeFragment(fragment);
    }

    @Override
    public Context getContext() {
        return Objects.requireNonNull(getActivity()).getApplicationContext();
    }


    public void noConnection(View view){
        Snackbar.make(view,R.string.noConnection,Snackbar.LENGTH_LONG).show();
    }
}

