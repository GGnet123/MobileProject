package com.example.gauharproject.retrofit;

import java.util.List;

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

    @POST("mobile/add-note")
    public Call<Note> addNote(
            @Header("token") int token,
            @Body Note note
    );

    @POST("mobile/note-done")
    public Call<Note> doneNote(
            @Header("token") int token,
            @Body Note note
    );

    @POST("mobile/remove-fav")
    public Call<Note> removeFav(
            @Header("token") int token,
            @Body Note note
    );

    @POST("mobile/delete-note")
    public Call<Note> deleteNote(
            @Body Note note
    );

    @GET("mobile/get-notes")
    public Call<List<Note>> getNotes(
            @Header("token") int token
    );

    @GET("mobile/get-favourite")
    public Call<List<CategoryContent>> getFavourite(
            @Header("token") int token
    );

    @GET("mobile/get-category-item")
    public Call<List<CategoryContent>> getContent(
            @Query("id") int id
    );
    @POST("mobile/like")
    public Call<Note> like(
            @Header("token") int token,
            @Body Note like
    );
}