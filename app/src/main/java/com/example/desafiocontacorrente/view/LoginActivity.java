package com.example.desafiocontacorrente.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.LoginContract;
import com.example.desafiocontacorrente.extras.MySharedPreferences;
import com.example.desafiocontacorrente.presenter.LoginPresenter;
import com.google.android.material.snackbar.Snackbar;
import java.util.Objects;

/**
 * @author Rafael Lima Nunues de Oliveira
 * @version 1.0
 * @since 1.5
 * @see LoginPresenter
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private EditText edEmail;
    private EditText edPassword ;
    private Button btnEnter ;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        Objects.requireNonNull(getSupportActionBar()).hide();
        initializeViews();
        setListeners();

    }

    @Override
    public void logInto() {
        Intent intent = new Intent(this, MainActivity.class);
        MySharedPreferences.setPreferences(getContext(),"email",edEmail.getText().toString());
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void initializeViews() {
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnEnter = findViewById(R.id.btnEnter);
    }

    @Override
    public void setListeners() {
        btnEnter.setOnClickListener(v -> presenter.authenticate(edEmail.getText().toString(),edPassword.getText().toString()));

    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void noConnection(String noConnection) {
        Toast.makeText(this,"No connection",Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorMessage(String error) {
            Snackbar.make(btnEnter,error,Snackbar.LENGTH_LONG).show();
        }


}
