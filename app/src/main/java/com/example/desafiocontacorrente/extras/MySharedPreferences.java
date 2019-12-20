package com.example.desafiocontacorrente.extras;


import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    public static void setPreferences(Context context,String key,String value){
       SharedPreferences preferences = context.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreferences(Context context,String key){
        android.content.SharedPreferences preferences = context.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        return preferences.getString(key,"não encontrado");

    }
}


