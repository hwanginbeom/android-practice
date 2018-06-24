package com.example.user.project_0611;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class LinkAdapter extends BaseAdapter {

    private Context context;
    private List<Restaurant> restaurantList;

    public LinkAdapter(Context context, List<Restaurant> restaurantList){
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return restaurantList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.listitem, null);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView rating = (TextView) v.findViewById(R.id.rating);
        TextView tag = (TextView) v.findViewById(R.id.tag);
        TextView link = (TextView) v.findViewById(R.id.link);
        TextView review1 = (TextView) v.findViewById(R.id.review1);
        TextView review2 = (TextView) v.findViewById(R.id.review2);
        ImageView img = (ImageView) v.findViewById(R.id.image);
        String url = restaurantList.get(i).getResImage();
        Picasso.with(v.getContext()).load(url).into(img);
        title.setText(restaurantList.get(i).getResTitle());
        rating.setText(restaurantList.get(i).getResRating()+"점");
        tag.setText(restaurantList.get(i).getResTag());
        review1.setText(restaurantList.get(i).getResReview1());
        review2.setText(restaurantList.get(i).getResReview2());

        v.setTag(restaurantList.get(i).getResTitle());
        return v;
    }
}
