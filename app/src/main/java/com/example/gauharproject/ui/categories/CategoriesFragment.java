package com.example.gauharproject.ui.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gauharproject.CategoryActivity;
import com.example.gauharproject.MainActivity;
import com.example.gauharproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private CategoriesViewModel categoriesViewModel;
    private String [] categories = { "Meditation essentials", "Falling sleep & waking up" , "Sport", "Beuty & Health", "Perfomance mindset" ,
                                       "Work & Productivity", "Kids & Parenting" , "Cooking" , "Travel" , "Fashion"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoriesViewModel =
                ViewModelProviders.of(this).get(CategoriesViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_categories, container, false);
        final TextView textView = root.findViewById(R.id.text_categories);
        categoriesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView cat1 = root.findViewById(R.id.cat1);
        final TextView cat2 = root.findViewById(R.id.cat2);
        final TextView cat3 = root.findViewById(R.id.cat3);
        final TextView cat4 = root.findViewById(R.id.cat4);
        final TextView cat5 = root.findViewById(R.id.cat5);
        final TextView cat6 = root.findViewById(R.id.cat6);

        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 1);
                startActivity(intent);
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 2);
                startActivity(intent);
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 3);
                startActivity(intent);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 4);
                startActivity(intent);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 5);
                startActivity(intent);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity)getContext(), CategoryActivity.class);
                intent.putExtra("category", 6);
                startActivity(intent);
            }
        });

        return root;

    }
}