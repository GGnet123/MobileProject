package com.example.gauharproject.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    ImageView img1;
    ImageView img2;
    int cnt = 1;
    ConstraintLayout constraintLayout;
    private String[] colors = {"#FAEBD7","#00FFFF","#7FFF00","#D2691E","#6495ED","#556B2F","#FFFAF0", "#FFB6C1"};
    private int i = 0;

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

        img1 = root.findViewById(R.id.blogImg);
        img2 = root.findViewById(R.id.blogImg2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.lifehack.org/articles/lifestyle/100-life-hacks-that-make-life-easier.html"));
                startActivity(viewIntent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.rbc.ru/trends/education/5e727a799a79475f8748651"));
                startActivity(viewIntent);
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

        constraintLayout = root.findViewById(R.id.home_page);
        constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                constraintLayout.setBackgroundColor(Color.parseColor(colors[i]));
                if (i < 7){
                    i++;
                } else { i = 0; }
                return true;
            }
        });

        return root;
    }
}