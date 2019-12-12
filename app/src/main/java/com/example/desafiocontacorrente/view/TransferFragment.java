package com.example.desafiocontacorrente.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.TransferContract;
import com.example.desafiocontacorrente.presenter.TransferPresenter;

public class TransferFragment extends Fragment implements TransferContract.View {
    EditText edEmailTrasnfer;
    EditText edValueTransfer;
    Button btnValueTransfer;
    TransferPresenter presenter;
    View view;
    String id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        presenter = new TransferPresenter(this);
         id = getArguments().getString("userId");
         view = inflater.inflate(R.layout.fragment_statement, container, false);
        return view;
    }

    @Override
    public void initializeViews() {
        edEmailTrasnfer = view.findViewById(R.id.edEmailTransfer);
        edValueTransfer = view.findViewById(R.id.edValueTransfer);
        btnValueTransfer = view.findViewById(R.id.btnTransferValure);
    }

    @Override
    public void setListeners() {
        btnValueTransfer.setOnClickListener(v -> presenter.transfer(Integer.parseInt(id),Integer.parseInt(edEmailTrasnfer.getText().toString()),Double.parseDouble(edValueTransfer.getText().toString())));
    }

    @Override
    public void transferSuccessfully() {
        Toast.makeText(this.getContext(),"Transfer Successfully", Toast.LENGTH_LONG).show();
    }
}