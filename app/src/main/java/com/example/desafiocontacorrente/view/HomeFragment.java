package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.HomeContract;
import com.example.desafiocontacorrente.model.User;
import com.example.desafiocontacorrente.presenter.HomePresenter;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment implements HomeContract.View {
    private TextView tvName;
    private TextView tvBalance;
    private HomePresenter presenter;
    private Button btnStatement;
    private Button btnTransfer;
    private Button btnExit;
    private View view;
    private TextView tvDrawerName;
    private TextView tvDrawerEmail;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        presenter = new HomePresenter(this);
        String email = getActivity().getIntent().getExtras().getString("email");
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initializeViews();
        setListeners();
        presenter.getUser("rafael.nunes@evosystems.com.br");
    }

    @Override
    public void noConnection(String noConnection) {
        Toast.makeText(view.getContext(), noConnection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserInformation(User user) {
        tvName.setText(user.getName());
        tvBalance.setText(user.getBalance());
        tvDrawerName.setText(user.getName());
        tvDrawerEmail.setText(user.getEmail());
        Toast.makeText(view.getContext(), "Bem Vindo!!!" + user.getName() + ": "+ user.getBalance(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void initializeViews() {
        tvBalance = view.findViewById(R.id.tvBalance);
        tvName = view.findViewById(R.id.tvName);
        btnStatement = view.findViewById(R.id.btnStatement);
        btnTransfer = view.findViewById(R.id.btnTransfer);
        btnExit = view.findViewById(R.id.btnExit);
        tvDrawerName = view.findViewById(R.id.tvDrawerName);
        tvDrawerEmail = view.findViewById(R.id.tvDrawerEmail);

    }
    @Override
    public void setListeners() {
        btnStatement.setOnClickListener(v -> changeFragment(new StatementFragment()));

        btnTransfer.setOnClickListener(v -> changeFragment(new TransferFragment()));

        btnExit.setOnClickListener(v -> getActivity().finish());
    }

    @Override
    public void errorShowInformation(String fail) {
        Toast.makeText(view.getContext(), "OnFailed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (fragment != oldFragment) {
            Snackbar.make(tvBalance, "Já esta nessa tela", Snackbar.LENGTH_LONG).show();
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}




