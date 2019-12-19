package com.example.desafiocontacorrente.view;


import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.MainContract;
import com.example.desafiocontacorrente.extras.RootActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends RootActivity implements MainContract.View {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout contaner;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setListeners();
        setSupportActionBar(toolbar);
    }

    @Override
    public Toolbar getToolbar() {
        return this.toolbar;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return this.drawerLayout;
    }




    public void setTitleToolbar(String title) {
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
        contaner = findViewById(R.id.nav_host_fragment);
    }


    @Override
    public void setListeners() {
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
                        break;
                    }
                    default: return true;
                } return true;
            }
        });

    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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

    public void onBackPressed() {
        if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
            Fragment fragmentActual = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            if(!(fragmentActual instanceof HomeFragment)){
                changeFragment(new HomeFragment());
            }else{
                showExitAlert(this);
            }
        }else{
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Handle clicks on toolbar icons
     * if current fragment is HomeFragment, open DrawerLayout
     * of any  other fragment, call onBackPressed() returning to HomeFragment
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(fragmentManager.findFragmentById(R.id.nav_host_fragment) instanceof  HomeFragment){
                drawerLayout.openDrawer(GravityCompat.START);
            }else {
                onBackPressed();
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
