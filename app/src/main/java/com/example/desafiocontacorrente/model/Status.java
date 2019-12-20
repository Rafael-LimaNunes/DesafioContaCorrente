package com.example.desafiocontacorrente.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Status implements Serializable {
    @SerializedName("status")boolean status;

    public boolean getStatus() {
        return status;
    }

}
