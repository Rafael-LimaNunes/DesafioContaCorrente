package com.example.desafiocontacorrente.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.api.ServiceAccountAPI;
import com.example.desafiocontacorrente.api.ServiceAccountApImpl;
import com.example.desafiocontacorrente.contracts.TransferContract;
import com.example.desafiocontacorrente.extras.Connection;
import com.example.desafiocontacorrente.extras.MySharedPreferences;
import com.example.desafiocontacorrente.model.Transfer;
import com.example.desafiocontacorrente.model.User;

public class TransferPresenter implements TransferContract.Presenter {
    TransferContract.View view;
    private String emailFrom;
    private User userFrom = null;
    private User userTo = null;

    public TransferPresenter(TransferContract.View view) {
        this.view = view;
        emailFrom = MySharedPreferences.getPreferences(view.getContext(),"email");
    }


    @Override
    public void transfer(double value) {
        if(Double.parseDouble(userFrom.getBalance()) > value) {
            ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
            if(! new Connection().isConnected(view.getContext())) {
                view.displayErrorMessage(String.valueOf(R.string.noConnection));
            }else{
                serviceAccountAp.transfer(Integer.parseInt(userFrom.getId()), Integer.parseInt(userTo.getId()), value, new ServiceAccountAPI.CallBack<Transfer>() {
                    @Override
                    public void onLoaded(Transfer transfer) {
                        if(transfer.isStatus()){
                            view.showDialog(userFrom.getName(),userTo.getName(),String.valueOf(value));
                        }else{
                            view.displayErrorMessage(String.valueOf(R.string.error_onTransfer));
                        }
                    }

                    @Override
                    public void onFailed(String fail) {
                        view.displayErrorMessage(String.valueOf(R.string.error_onTransfer));
                    }

                    @Override
                    public void noConnection(String noConnection) {
                        view.displayErrorMessage(R.string.noConnection + noConnection);
                    }
                });
            }
        }

    }

    @Override
    public void confirmData(String emailTo, String value) {
        ServiceAccountApImpl serviceAccountAp = new ServiceAccountApImpl();
        if(emailTo.isEmpty()|| value.isEmpty()){
            view.displayErrorMessage(String.valueOf(R.string.empty_fields));
        }else {
            if(!new Connection().isConnected(view.getContext())){
                view.displayErrorMessage(String.valueOf(R.string.noConnection));
            }else {
                serviceAccountAp.getUserData(emailFrom, new ServiceAccountAPI.CallBack<User>() {
                    @Override
                    public void onLoaded(User user) {
                        userFrom = user;
                    }

                    @Override
                    public void onFailed(String fail) {
                        view.displayErrorMessage(String.valueOf(R.string.error_update));
                    }

                    @Override
                    public void noConnection(String noConnection) {
                        view.displayErrorMessage(R.string.noConnection + noConnection);
                    }
                });
            }
            if(!new Connection().isConnected(view.getContext())){
                view.displayErrorMessage(String.valueOf(R.string.noConnection));
            }else {
                serviceAccountAp.getUserData(emailTo, new ServiceAccountAPI.CallBack<User>() {
                    @Override
                    public void onLoaded(User user) {
                        userTo = user;
                        transfer(Double.parseDouble(value));
                    }

                    @Override
                    public void onFailed(String fail) {
                        view.displayErrorMessage(String.valueOf(R.string.error_update));
                    }

                    @Override
                    public void noConnection(String noConnection) {
                        view.displayErrorMessage(R.string.noConnection + noConnection);
                    }
                });
            }

            }

        }

}




