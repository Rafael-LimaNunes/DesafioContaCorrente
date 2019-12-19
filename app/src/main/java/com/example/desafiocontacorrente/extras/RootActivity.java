package com.example.desafiocontacorrente.extras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.desafiocontacorrente.R;

public class RootActivity extends AppCompatActivity {

    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count != 0) {
            getSupportFragmentManager().popBackStack();
        }
    }


    public void showExitAlert(android.content.Context context){
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

