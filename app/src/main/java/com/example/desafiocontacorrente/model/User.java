package com.example.desafiocontacorrente.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id") String id;
    @SerializedName("name") String name;
    @SerializedName("email") String email;
    @SerializedName("profile") String profile;
    @SerializedName("balance") String balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "nome: " + name + "\nemail: " + email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
