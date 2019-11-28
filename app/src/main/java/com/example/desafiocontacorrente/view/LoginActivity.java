package com.example.desafiocontacorrente.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.Contract;
import com.example.desafiocontacorrente.presenter.LoginPresenter;
import com.example.desafiocontacorrente.view.AccountActivity;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements Contract.View {
    @BindView(R.id.edEmail) private EditText edEmail;
    @BindView(R.id.edPassword) private EditText edPassword;
    @BindView(R.id.btnEnter)  Button btnEnter;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this,edEmail,edPassword);
    }


    @Override
    public void logInto() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void fail() {
        Snackbar.make(btnEnter,R.string.invalid,Snackbar.LENGTH_LONG);
    }

}
