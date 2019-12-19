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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(String idFrom) {
        this.idFrom = idFrom;
    }

    public String getIdTo() {
        return idTo;
    }

    public void setIdTo(String idTo) {
        this.idTo = idTo;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
