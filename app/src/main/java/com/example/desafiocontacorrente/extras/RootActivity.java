package com.example.desafiocontacorrente.extras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.desafiocontacorrente.R;

public class RootActivity extends AppCompatActivity {


    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void showAlert(android.content.Context context){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.dialog_exit));
        builder.setMessage(R.string.exit_mensage);
        builder.setPositiveButton(R.string.dialog_yes, (dialog, which) -> finish());
        builder.setNegativeButton(R.string.dialog_no, (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
        }
}

