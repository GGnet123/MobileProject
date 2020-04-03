package com.example.gauharproject.retrofit;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("success")
    private boolean success;
    ////login
    @SerializedName("user")
    private User user;

    private String login;
    private String password;

    public Post(String login, String password){
        this.login = login;
        this.password = password;
    }
    public User getUser(){
        return user;
    }
    public boolean getSuccess(){
        return success;
    }
}
