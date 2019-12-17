package com.example.desafiocontacorrente.extras;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection {
    private NetworkInfo getNetWorkInfo(Context context){
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connection.getActiveNetworkInfo();
    }

    public boolean isConnected(Context context){
        NetworkInfo info = getNetWorkInfo(context);
        return info != null && info.isConnected();
    }
}
