package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.extras.CustomAdapter;
import com.example.desafiocontacorrente.extras.RootFragment;
import com.example.desafiocontacorrente.presenter.StatementPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class BankStatementFragment extends RootFragment implements StatementContract.View {

    private ListView lvStatement;
    private View view;
    private CustomAdapter adapter;
    private StatementPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayoutStatement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bank_statement, container, false);
        initializeViews();
        setListeners();
        setTitle(getString(R.string.menu_statement));
        ((MainActivity)getActivity()).lockDL(true);

        ((MainActivity) getActivity()).getToolbar().setNavigationOnClickListener(v ->{
            getActivity().onBackPressed();
        });

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
        ((MainActivity)getActivity()).lockDL(true);
    }
    @Override
    public void showList(List list) {
        adapter = new CustomAdapter(list,getActivity());
        lvStatement.setAdapter(adapter);
    }

    @Override
    public void initializeViews() {
        lvStatement = view.findViewById(R.id.lvStatement);
        swipeRefreshLayoutStatement = view.findViewById(R.id.swipeRefreshStatement);

        ((MainActivity)getActivity()).lockDL(true);
    }

    @Override
    public void bindEmpty() {
    }

    @Override
    public void showErrorMessage(String error) {
        Snackbar.make(lvStatement,error,Snackbar.LENGTH_LONG).show();


    }
}

