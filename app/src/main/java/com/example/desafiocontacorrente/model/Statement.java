package com.example.desafiocontacorrente.model;

import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("id") int id;
    @SerializedName("id_from") int idFrom;
    @SerializedName("id_to") int idTo;
    @SerializedName("value") int value;
    @SerializedName("data") int date;

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", idFrom=" + idFrom +
                ", idTo=" + idTo +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
