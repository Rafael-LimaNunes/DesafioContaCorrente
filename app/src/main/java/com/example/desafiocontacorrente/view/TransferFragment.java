package com.example.desafiocontacorrente.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.contracts.TransferContract;
import com.example.desafiocontacorrente.extras.RootFragment;
import com.example.desafiocontacorrente.presenter.TransferPresenter;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class TransferFragment extends RootFragment implements TransferContract.View {
    private EditText edEmailTrasnfer;
    private EditText edValueTransfer;
    private Button btnValueTransfer;
    private TransferPresenter presenter;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        presenter = new TransferPresenter(this);
         view = inflater.inflate(R.layout.fragment_transfer, container, false);
        ((MainActivity) Objects.requireNonNull(getActivity())).lockDL(true);

        ((MainActivity) getActivity()).getToolbar().setNavigationOnClickListener(v -> getActivity().onBackPressed());

         initializeViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setListeners();
        setBackButton(true);
        ((MainActivity) Objects.requireNonNull(getActivity())).lockDL(true);
    }

    @Override
    public void initializeViews() {
        edEmailTrasnfer = view.findViewById(R.id.edEmailTransfer);
        edValueTransfer = view.findViewById(R.id.edValueTransfer);
        btnValueTransfer = view.findViewById(R.id.btnTransferValure);
    }

    @Override
    public void setListeners() {
        btnValueTransfer.setOnClickListener(v -> presenter.confirmData(edEmailTrasnfer.getText().toString(),edValueTransfer.getText().toString()));
    }

    public void showDialog(String nameFrom, String nameTO,String value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(Objects.requireNonNull(getActivity()).getString(R.string.transfer_title));
        builder.setMessage("Valor enviado: R$ "+ value + "\nDe: " + nameFrom + "\nPara: " + nameTO);
        builder.setNeutralButton("Voltar", (dialog, which) -> changeFragment(new HomeFragment()));
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void displayErrorMessage(String error) {
        Snackbar.make(btnValueTransfer,R.string.error_transfer, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }


}