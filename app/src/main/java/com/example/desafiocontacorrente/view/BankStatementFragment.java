package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.extras.RootFragment;
import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.presenter.StatementPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class BankStatementFragment extends RootFragment implements StatementContract.View {

    private TextView empty;
    private ListView lvStatement;
    private View view;
    private ArrayAdapter<Statement> adapter;
    private StatementPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bank_statement, container, false);
        initializeViews();
        setTitle(getString(R.string.menu_statement));
        presenter = new StatementPresenter(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBankStatement();
        /*setBackButton(true);*/
    }
    @Override
    public void showList(List list) {
        Toast.makeText(view.getContext(),"extrato: "+ list.toString(), Toast.LENGTH_LONG).show();
        adapter = new ArrayAdapter<Statement>(view.getContext(), android.R.layout.simple_list_item_1,list);
        lvStatement.setAdapter(adapter);
    }

    @Override
    public void initializeViews() {
        lvStatement = view.findViewById(R.id.lvStatement);
    }


    @Override
    public void bindEmpty() {
    }

    @Override
    public void showErrorMessage(String error) {
        Snackbar.make(lvStatement,error,Snackbar.LENGTH_LONG).show();

    }
}

