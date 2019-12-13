package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.HomeContract;
import com.example.desafiocontacorrente.model.User;
import com.example.desafiocontacorrente.presenter.HomePresenter;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends RootFragment implements HomeContract.View {
    private TextView tvName;
    private TextView tvBalance;
    private HomePresenter presenter;
    private Button btnStatement;
    private Button btnTransfer;
    private Button btnExit;
    private View view;
    private String email;
    private Bundle bundle;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View container;
    private TextView tvDrawerName;
    private TextView tvDrawerEmail;
    private DrawerLayout drawerLayout;
    private String id;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setProgress(true);
        bundle = new Bundle();
        setTitle(getString(R.string.title_home));
        presenter = new HomePresenter(this);
        email = getActivity().getIntent().getExtras().getString("email");
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initializeViews();
        setListeners();
        presenter.getUser(email);
    }

    @Override
    public void noConnection(String noConnection) {
        Toast.makeText(view.getContext(), noConnection, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserInformation(User user) {
        container.setVisibility(View.GONE);
        tvName.setText(user.getName());
        tvBalance.setText(user.getBalance());
        bundle.putString("userId", user.getId());
        container.setVisibility(View.VISIBLE);
        setProgress(false);


        /*tvDrawerName.setText(user.getName());
        tvDrawerEmail.setText(user.getEmail());*/
    }

    @Override
    public void initializeViews() {
        tvBalance = view.findViewById(R.id.tvBalance);
        tvName = view.findViewById(R.id.tvName);
        btnStatement = view.findViewById(R.id.btnStatement);
        btnTransfer = view.findViewById(R.id.btnTransfer);
        btnExit = view.findViewById(R.id.btnExit);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        container = view.findViewById(R.id.nav_host_fragment);

    }
    @Override
    public void setListeners() {
        btnStatement.setOnClickListener(v ->
                changeFragment(new StatementFragment(),bundle));

        btnTransfer.setOnClickListener(v -> changeFragment(new TransferFragment(), bundle));

        btnExit.setOnClickListener(v -> getActivity().finish());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getUser(email);
                swipeRefreshLayout.isRefreshing();
            }
        });
    }

    @Override
    public void errorShowInformation(String fail) {
        Toast.makeText(view.getContext(), "OnFailed", Toast.LENGTH_LONG).show();
    }


    /**
     *
     * @param fragment
     */
    @Override
    public void changeFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (fragment == oldFragment) {
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




