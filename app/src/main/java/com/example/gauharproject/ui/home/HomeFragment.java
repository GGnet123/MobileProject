package com.example.gauharproject.ui.home;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.gauharproject.ImageAdapter;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayer2;
    Button btn1;
    Button btn2;
    int cnt = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        ViewPager viewPager = root.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(((MainActivity)getActivity()));
        viewPager.setAdapter(adapter);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        mediaPlayer = MediaPlayer.create((MainActivity)getContext(), R.raw.first);
        mediaPlayer2 = MediaPlayer.create((MainActivity)getContext(), R.raw.second);
        btn1 = root.findViewById(R.id.play_music);
        btn2 = root.findViewById(R.id.play_second);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer2.isPlaying()) {
                    mediaPlayer2.pause();
                } else {
                    mediaPlayer2.start();
                }
            }
        });

        return root;
    }
}