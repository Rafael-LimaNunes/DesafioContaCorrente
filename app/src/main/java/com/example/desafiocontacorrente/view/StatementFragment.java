package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.model.Statement;
import com.example.desafiocontacorrente.presenter.StatementPresenter;

import java.util.List;

public class StatementFragment extends Fragment implements StatementContract.View {
    private List listStatements;
    private View view;
    private ArrayAdapter<Statement> adapter;

    private StatementPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transfer, container, false);
        presenter = new StatementPresenter(this);
        presenter.getBankStatement(5);
        return view;
    }



    @Override
    public void showList(List list) {

    }

    @Override
    public void initialize() {
        listStatements = view.findViewById(R.id.listStatement);
        adapter = new ArrayAdapter<Statement>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,listStatements);
    }
}