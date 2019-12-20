package com.example.desafiocontacorrente.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Statement {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @SerializedName("id")
    protected String id;
    @SerializedName("id_from")
    private String idFrom;
    @SerializedName("id_to")
    private String idTo;
    @SerializedName("value")
    private String value;
    @SerializedName("data")
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    @NotNull
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
