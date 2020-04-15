package com.example.gauharproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<Note> data;
    private static LayoutInflater inflater=null;

    TextView note;
    CheckBox done;
    public ListViewAdapter(Activity a, List<Note> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_item, null);
        final View vi2 = vi;
        note = (TextView)vi.findViewById(R.id.note_text);
        done = (CheckBox)vi.findViewById(R.id.note_done);

        Note item;
        item = data.get(position);

        note.setText(item.getNote());
        done.setChecked(item.isDone()==1);
        final int id = item.getId();
        if (item.isDone()==1)
        {
            note.setPaintFlags(note.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        final int isDone = item.isDone();
        Button delete = vi.findViewById(R.id.delete_note);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(id, vi2);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDone(id, isDone == 1?0:1, vi2);
            }
        });
        return vi;
    }

    public void deleteNote(int id, final View view){
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
        Note note = new Note(id,"", 0);
        Call<Note> call = jp.deleteNote(note);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }

    public void toggleDone(int id, int done, final View vi){
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
        Note note = new Note(id,"", done);
        Call<Note> call = jp.doneNote(((MainActivity)activity).getToken(), note);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()){
                    TextView note = (TextView)vi.findViewById(R.id.note_text);
                    note.setPaintFlags(note.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }
}
