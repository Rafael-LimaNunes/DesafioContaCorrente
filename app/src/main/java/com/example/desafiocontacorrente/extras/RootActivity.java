package com.example.desafiocontacorrente.extras;

import androidx.appcompat.app.AppCompatActivity;

public class RootActivity extends AppCompatActivity {

    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count != 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

}

