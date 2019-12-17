package com.example.desafiocontacorrente.api;


public interface ServiceAccountAPI {

    interface CallBack<T>{
        void onLoaded(T o);
        void onFailed(String fail);
        void noConnection(String noConnection);
    }
    void checkLogin(String login, String password, CallBack callBack);
    void getUserData(String email, CallBack callBack);
    void getBankStatement(String id, CallBack callback);
    void transfer(int idUserFrom, int idUserTo, double valueTransfer, CallBack callBack);

}
