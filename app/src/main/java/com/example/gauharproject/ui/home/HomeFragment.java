package com.example.gauharproject.ui.home;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    private ImageView img1;
    private ImageView img2;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        ViewPager viewPager = root.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(((MainActivity)getActivity()));
        viewPager.setAdapter(adapter);

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
                                Uri.parse("https://www.rbc.ru/trends/education/5e727a799a79475f87486511"));
                startActivity(viewIntent);
            }
        });

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}