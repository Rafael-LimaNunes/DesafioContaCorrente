package com.example.desafiocontacorrente.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

public class Statement {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @SerializedName("id") String id;
    @SerializedName("id_from") String idFrom;
    @SerializedName("id_to") String idTo;
    @SerializedName("value") String value;
    @SerializedName("data") String date;

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
