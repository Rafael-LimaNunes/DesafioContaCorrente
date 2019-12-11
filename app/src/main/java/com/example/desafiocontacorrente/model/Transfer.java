package com.example.desafiocontacorrente.model;

import com.google.gson.annotations.SerializedName;

public class Transfer {
    @SerializedName("status") boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "status=" + status +
                '}';
    }
}
