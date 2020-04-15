package com.example.gauharproject.ui.notes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gauharproject.ListViewAdapter;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;
import com.example.gauharproject.retrofit.JSONPlaceHolderApi;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Note;
import com.example.gauharproject.retrofit.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotesFragment extends Fragment{

    private NotesViewModel notesViewModel;
    public HashMap<String, String> map;
    public ArrayList<HashMap<String,String>> notes = new ArrayList<>();
    public ListViewAdapter adapter;
    public ListView lv;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notesViewModel =
                ViewModelProviders.of(this).get(NotesViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_notes, container, false);
        Button add = root.findViewById(R.id.note_add_btn);
        CheckBox done = root.findViewById(R.id.note_done);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote(root);
            }
        });
        getNotes(root);
        final TextView textView = root.findViewById(R.id.text_notes);
        notesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void getNotes(final View root){
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);

        Call<List<Note>> call = jp.getNotes(((MainActivity)getActivity()).getToken());
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                List<Note> notesList = response.body();
                if(notesList.size()>0){
                    Log.d("asdasd",notesList.get(0).isDone()+"");
                    adapter = new ListViewAdapter(getActivity(), notesList);
                    lv = root.findViewById(R.id.notes_list);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }

    public void addNote(final View view){
        Retrofit retrofit = NetworkClient.getRetrofitClient();
        JSONPlaceHolderApi jp = retrofit.create(JSONPlaceHolderApi.class);
        EditText note_text = view.findViewById(R.id.edit_text_notes);
        Note note = new Note(0, note_text.getText().toString(), 0);
        Call<Note> call = jp.addNote(((MainActivity)getActivity()).getToken(), note);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                if (response.isSuccessful()){
                    getNotes(view);
                }
            }
            @Override
            public void onFailure(Call<Note> call, Throwable t) { }
        });
    }
}