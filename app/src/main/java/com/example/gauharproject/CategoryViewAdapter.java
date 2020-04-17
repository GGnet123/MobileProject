package com.example.gauharproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gauharproject.retrofit.CategoryContent;
import com.example.gauharproject.retrofit.NetworkClient;
import com.example.gauharproject.retrofit.Note;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryViewAdapter extends BaseAdapter {

    private Activity activity;
    private List<CategoryContent> data;
    private static LayoutInflater inflater=null;

    public CategoryViewAdapter(Activity a, List<CategoryContent> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.category_activity_item, null);
        ImageView img = vi.findViewById(R.id.itemImg);
        TextView title = vi.findViewById(R.id.itemTitle);
        TextView desc = vi.findViewById(R.id.itemDesc);
        CategoryContent item = data.get(position);
        title.setText(item.getTitle());
        desc.setText(item.getShort_description());
        Picasso.get().load(NetworkClient.BASE_URL + item.getImage()).into(img);
        return vi;
    }
}
