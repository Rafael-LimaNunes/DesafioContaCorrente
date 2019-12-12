package com.example.desafiocontacorrente.view;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.desafiocontacorrente.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Bundle bundle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private HomeFragment homeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeViews();
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
     * Muda o título da actionBar
     * Quando muda o de fragment
     * */
    void setTitle(String title) {
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(title);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_statement: {
                homeFragment.changeFragment(new StatementFragment(),bundle);
                break;
            }
            case R.id.nav_transfer: {
                homeFragment.changeFragment(new TransferFragment(),bundle);
                break;
            }
            case R.id.nav_exit: {
                this.finish();
            }
        }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;


    }


    void initializeViews(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_statement, R.id.nav_transfer)
                .setDrawerLayout(drawerLayout)
                .build();
    }
}
