package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.extras.CustomAdapter;
import com.example.desafiocontacorrente.extras.RootFragment;
import com.example.desafiocontacorrente.presenter.StatementPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;


public class BankStatementFragment extends RootFragment implements StatementContract.View {

    private View view;
    private StatementPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayoutStatement;
    private RecyclerView rvStatement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bank_statement, container, false);
        initializeViews();
        setListeners();
        setTitle(getString(R.string.menu_statement));
        ((MainActivity) Objects.requireNonNull(getActivity())).lockDL(true);

        ((MainActivity) getActivity()).getToolbar().setNavigationOnClickListener(v -> getActivity().onBackPressed());

        presenter = new StatementPresenter(this);
        return view;
    }

    private void setListeners() {

        swipeRefreshLayoutStatement.setOnRefreshListener(() -> {
            presenter.getBankStatement();
            swipeRefreshLayoutStatement.setRefreshing(false);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBankStatement();
        setBackButton(true);
        ((MainActivity) Objects.requireNonNull(getActivity())).lockDL(true);
    }
    @Override
    public void showList(List list) {

        CustomAdapter adapter = new CustomAdapter(list, getActivity());
        rvStatement.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvStatement.setLayoutManager(layout);
    }

    @Override
    public void initializeViews() {
        rvStatement = view.findViewById(R.id.rvStatement);
        swipeRefreshLayoutStatement = view.findViewById(R.id.swipeRefreshStatement);
        ((MainActivity) Objects.requireNonNull(getActivity())).lockDL(true);
    }

    @Override
    public void showErrorMessage(String error) {
        Snackbar.make(rvStatement,error,Snackbar.LENGTH_LONG).show();


    }
}

