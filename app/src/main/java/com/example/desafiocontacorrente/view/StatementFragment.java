package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.StatementContract;
import com.example.desafiocontacorrente.presenter.StatementPresenter;

import java.util.List;

public class StatementFragment extends Fragment implements StatementContract.View {

    /*widget*/
    private ListView lvStatement;
    private View view;
    private ArrayAdapter adapter;
    private String id;
    private StatementPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transfer, container, false);
        presenter = new StatementPresenter(this);
        initializeViews();
        id = getArguments().getString("userId");
        Toast.makeText(getContext(),"Statement Fragment",Toast.LENGTH_LONG).show();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getBankStatement(Integer.parseInt(id));
        String[] list = {"Rafael","Henrique","Alexsader","Bruno","Lamarques"};
        adapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        Toast.makeText(getActivity(),list.toString(),Toast.LENGTH_LONG).show();
        lvStatement.setAdapter(adapter);

    }

    @Override
    public void showList(List list) {


    }

    @Override
    public void initializeViews() {
        lvStatement = view.findViewById(R.id.lvStatement);

    }
}