package com.example.desafiocontacorrente.presenter;

import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.TransferContract;
import com.example.desafiocontacorrente.model.Transfer;

public class TransferPresenter implements TransferContract.Presenter {
    TransferContract.View view;

    public TransferPresenter(TransferContract.View view) {
        this.view = view;
    }


    @Override
    public void transfer(int idUserTo,int  idUserFrom, double value) {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        serviceAccountAp.transfer(idUserTo, idUserFrom, value, new ServiceAccountAPI.CallBack<Transfer>() {
            @Override
            public void onLoaded(Transfer o) {
                 if(o.isStatus()){
                    view.transferSuccessfully();
                 }
            }

            @Override
            public void onFailed(String fail) {

            }

            @Override
            public void noConnection(String noConnection) {

            }
        });
    }


}
