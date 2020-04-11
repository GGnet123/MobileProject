package com.example.gauharproject.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @POST("mobile/login")
    public Call<Post> login(
            @Body Post post
    );

    @POST("mobile/register")
    public Call<Post> register(
            @Body Post post
    );

    @POST("mobile/edit-profile")
    public Call<User> editProfile(
            @Header("token") int token,
            @Body User user
    );
}