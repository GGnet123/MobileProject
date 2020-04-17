package com.example.gauharproject.ui.favourite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gauharproject.CategoryViewAdapter;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;
import com.example.gauharproject.retrofit.CategoryContent;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel favouriteViewModel;
    private Bundle data;
    private ListView lv;
    private CategoryViewAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favouriteViewModel =
                ViewModelProviders.of(this).get(FavouriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favourite, container, false);

        data = ((MainActivity)getActivity()).getData();
        lv = root.findViewById(R.id.fav_list);

        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
        Call<List<CategoryContent>> call = jp.getFavourite(((MainActivity)getActivity()).getToken());
        call.enqueue(new Callback<List<CategoryContent>>() {
            @Override
            public void onResponse(Call<List<CategoryContent>> call, Response<List<CategoryContent>> response) {
                adapter = new CategoryViewAdapter((MainActivity)getContext(),response.body(),1,true);
                lv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CategoryContent>> call, Throwable t) {

            }
        });

        final TextView textView = root.findViewById(R.id.text_favourite);
        favouriteViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}