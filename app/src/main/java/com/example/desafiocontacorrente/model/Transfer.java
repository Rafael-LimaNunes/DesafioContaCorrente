package com.example.desafiocontacorrente.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Transfer {
    @SerializedName("status") boolean status;

    public boolean isStatus() {
        return status;
    }

    @NotNull
    @Override
    public String toString() {
        return "Transfer{" +
                "status=" + status +
                '}';
    }
}
