package com.example.transportenligne;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientAdd {
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public ClientAdd(String username, String email, String password) {
    }
}
