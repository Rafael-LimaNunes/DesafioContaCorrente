package com.example.desafiocontacorrente.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.HomeContract;
import com.example.desafiocontacorrente.extras.MySharedPreferences;
import com.example.desafiocontacorrente.extras.RootFragment;
import com.example.desafiocontacorrente.model.User;
import com.example.desafiocontacorrente.presenter.HomePresenter;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class HomeFragment extends RootFragment implements HomeContract.View {
    private TextView tvName;
    private TextView tvBalance;
    private HomePresenter presenter;
    private Button btnStatement;
    private Button btnTransfer;
    private Button btnExit;
    private View view;
    private String email;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View container;
    private TextView tvDrawerName;
    private TextView tvDrawerEmail;
    private ImageView ivDrawerProfile;

    private DrawerLayout drawerLayout;
    private String id;
    private SharedPreferences preferences;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setProgress(true);
        preferences = getActivity().getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        email = preferences.getString("email","não encontrado");
        setTitle(getString(R.string.title_home));
        initializeViews();
        presenter = new HomePresenter(this);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        setListeners();
        presenter.getUser(email);
    }

    @Override
    public void noConnection(String noConnection) {
        Toast.makeText(view.getContext(), noConnection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserInformation(User user) {
       /* container.setVisibility(View.GONE);*/
        tvName.setText(user.getName());
        tvBalance.setText("R$: " + user.getBalance());

        tvDrawerName = getActivity().findViewById(R.id.tvDrawerName);
        tvDrawerName.setText(user.getName());

        tvDrawerEmail = getActivity().findViewById(R.id.tvDrawerEmail);
        tvDrawerEmail.setText(user.getEmail());

        ivDrawerProfile = getActivity().findViewById(R.id.ivDrawerProfile);
        Glide.with(getActivity())
                .load(user.getProfile())
                .into(ivDrawerProfile);

        MySharedPreferences.setPreferences(getContext(),"id",user.getId());
       /* container.setVisibility(View.VISIBLE);*/
        setProgress(false);
    }

    @Override
    public void initializeViews() {
        tvBalance = view.findViewById(R.id.tvBalance);
        tvName = view.findViewById(R.id.tvName);
        btnStatement = view.findViewById(R.id.btnStatement);
        btnTransfer = view.findViewById(R.id.btnTransfer);
        btnExit = view.findViewById(R.id.btnExit);
        container = view.findViewById(R.id.nav_host_fragment);

    }
    @Override
    public void setListeners() {
        btnStatement.setOnClickListener(v -> changeFragment(new BankStatementFragment()));

        btnTransfer.setOnClickListener(v -> changeFragment(new TransferFragment()));

        btnExit.setOnClickListener(v -> getActivity().finish());

        /*swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getUser(email);
                swipeRefreshLayout.isRefreshing();
            }
        });*/

    }

    @Override
    public void errorShowInformation(String fail) {
        Toast.makeText(view.getContext(), "OnFailed", Toast.LENGTH_LONG).show();
    }

}




