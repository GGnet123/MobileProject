package com.example.gauharproject.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("success")
    private boolean success;
    ////login
    @SerializedName("token")
    private String token;

    private String login;
    private String password;

    public Post(String login, String password){
        this.login = login;
        this.password = password;
    }
    public String getToken(){
        return token;
    }
    public boolean getSuccess(){
        return success;
    }
}
