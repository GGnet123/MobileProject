package com.example.gauharproject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gauharproject.retrofit.CategoryContent;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Note;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<CategoryContent> data;
    private static LayoutInflater inflater=null;
    private int token;
    private boolean inFav;
    public CategoryViewAdapter(Activity a, List<CategoryContent> d, int user_id, boolean isFav) {
        inFav = isFav;
        token = user_id;
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.category_activity_item, null);

        ImageView img = vi.findViewById(R.id.itemImg);
        TextView title = vi.findViewById(R.id.itemTitle);
        TextView desc = vi.findViewById(R.id.itemDesc);

        final CategoryContent item = data.get(position);

        title.setText(item.getTitle());
        desc.setText(item.getShort_description());

        Picasso.get().load(NetworkClient.BASE_URL + item.getImage()).into(img);

        ImageView like = vi.findViewById(R.id.category_like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = NetworkClient.getRetrofitClient();
                JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
                Note like = new Note(item.getId(), "", 1);
                Call<Note> call = jp.like(token, like);
                Toast.makeText(((CategoryActivity)activity).getApplicationContext(), "Added to favourite", Toast.LENGTH_SHORT).show();
                call.enqueue(new Callback<Note>() {
                    @Override
                    public void onResponse(Call<Note> call, Response<Note> response) {
                    }

                    @Override
                    public void onFailure(Call<Note> call, Throwable t) {
                    }
                });
            }
        });

        if (inFav){
            like.setVisibility(View.GONE);
        }
        return vi;
    }


}
