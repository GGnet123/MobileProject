package com.example.gauharproject.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    String name;
    String surname;
    String age;
    ArrayList<String> favourite;

    TextView profile_name;
    TextView profile_surname;
    TextView profile_age;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Bundle data = ((MainActivity) getActivity()).getData();
        if (data != null) {
            name = data.getString("name");
            surname = data.getString("surname");
            age = data.getString("age");
            favourite = data.getStringArrayList("favourite");

            profile_name = root.findViewById(R.id.profile_name);
            profile_surname = root.findViewById(R.id.profile_surname);
            profile_age = root.findViewById(R.id.profile_age);

            profile_name.setText(name);
            profile_surname.setText(surname);
            profile_age.setText(age);

        }
        return root;
    }
}