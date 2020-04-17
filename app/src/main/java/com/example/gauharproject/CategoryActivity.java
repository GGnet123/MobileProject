package com.example.gauharproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gauharproject.retrofit.CategoryContent;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity {
    CategoryViewAdapter categoryViewAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_content);

        Intent intent = getIntent();
        int id = intent.getIntExtra("category", 0);

        lv = findViewById(R.id.listCat);

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
        Call<List<CategoryContent>> call = jp.getContent(id);
        call.enqueue(new Callback<List<CategoryContent>>() {
            @Override
            public void onResponse(Call<List<CategoryContent>> call, Response<List<CategoryContent>> response) {
                categoryViewAdapter = new CategoryViewAdapter(CategoryActivity.this, response.body());
                lv.setAdapter(categoryViewAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryContent>> call, Throwable t) { }
        });
    }
}
