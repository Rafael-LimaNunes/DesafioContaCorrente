package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.presenter.StatementPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class StatementFragment extends RootFragment implements StatementContract.View {

    /*widget*/
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lvStatement;
    private View view;
    private ArrayAdapter adapter;
    private String id;
    private TextView empty;
    private StatementPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statement, container, false);
        presenter = new StatementPresenter(this);
        id = getArguments().getString("userId");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(getString(R.string.menu_statement));
        setBackButton(true);
        initializeViews();
        presenter.getBankStatement(Integer.parseInt(id));

    }

    @Override
    public void showList(List list) {


    }

    @Override
    public void initializeViews() {
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        empty = view.findViewById(R.id.empty);
        lvStatement = view.findViewById(R.id.lvStatement);

    }

    @Override
    public void setListener() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getBankStatement(Integer.parseInt(id));
            swipeRefreshLayout.isRefreshing();
        });

    }

    @Override
    public void bindList(List<Statement> statements) {
        empty.setVisibility(View.GONE);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, statements);
        lvStatement.setAdapter(adapter);
        setProgress(false);
    }


    @Override
    public void errorMessage() {
        Snackbar.make(lvStatement,"Errro",Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void bindEmpty() {
        lvStatement.setVisibility(View.GONE);
        empty.setVisibility(View.VISIBLE);
    }


}