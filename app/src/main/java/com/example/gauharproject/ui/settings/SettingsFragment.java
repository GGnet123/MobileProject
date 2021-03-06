package com.example.gauharproject.ui.settings;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gauharproject.LoginActivity;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;
import com.example.gauharproject.db.UserDb;

public class SettingsFragment extends Fragment {

    private SettingsViewModel sendViewModel;
    private LinearLayout layout;
    private String[] colors = {"#FAEBD7","#00FFFF","#7FFF00","#D2691E","#6495ED","#556B2F","#FFFAF0", "#FFB6C1"};
    private int i = 0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        final Button exitBtn = root.findViewById(R.id.exitBtn);

        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        layout = root.findViewById(R.id.settings_background);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                layout.setBackgroundColor(Color.parseColor(colors[i]));
                if (i < 7){
                    i++;
                } else { i = 0; }
                return true;
            }
        });

        return root;
    }
}