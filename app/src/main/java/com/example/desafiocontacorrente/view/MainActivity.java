package com.example.desafiocontacorrente.view;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.MainContract;
import com.example.desafiocontacorrente.extras.RootActivity;
import com.example.desafiocontacorrente.model.User;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends RootActivity implements MainContract.View {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private HomeFragment homeFragment;
    private ProgressBar progressBar;
    private FrameLayout contaner;
    private ActionBarDrawerToggle toggle;
    private TextView navHeaderName;
    private TextView  navHeaderEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();
        setListeners();
        setSupportActionBar(toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        homeFragment = new HomeFragment();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
     *Se o NavigationDrawer tiver aberto e o botão de voltar for pressionado
     * fechar o NavigationDrawer primero. Se o botão de voltar for pressionado novamente
     * então fecha a activity
     */

    /*
     * Muda o título da actionBar
     * Quando muda o de fragment
     * */
    void setTitle(String title) {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(title);
    }

    @Override
    public Context getContext() {
        return null;
    }

    public void initializeViews(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        progressBar = findViewById(R.id.progressBar);
        contaner = findViewById(R.id.nav_host_fragment);
        mAppBarConfiguration = new AppBarConfiguration.Builder(

                R.id.nav_home, R.id.nav_statement, R.id.nav_transfer)
                .setDrawerLayout(drawerLayout)
                .build();
    }
    @Override
    public void setProgress(Boolean visible) {
       /* if(visible){
            progressBar.setVisibility(View.VISIBLE);
            contaner.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            contaner.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void setListeners() {
    toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer, R.string.close_drawer);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
    navigationView.setNavigationItemSelectedListener(item -> {
        switch (item.getItemId()) {
            case R.id.nav_statement: {
                changeFragment(new BankStatementFragment());
                break;
            }
            case R.id.nav_transfer: {
                changeFragment(new TransferFragment());
                break;
            }
            case R.id.nav_exit: {
                finish();
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;


    });

    }

    @Override
    public void initializeNavHeader(User user) {
        navHeaderName.setText(user.getName());
        navHeaderEmail.setText(user.getEmail());
    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment oldFragment =   fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (fragment == oldFragment) {
            Snackbar.make(toolbar, "Já esta nessa tela", Snackbar.LENGTH_LONG).show();
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void lockDL(boolean lock) {
        if (lock) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }


    @Override
    public ActionBarDrawerToggle getToggle() {
        return toggle;
    }


}
