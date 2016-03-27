package com.company.uo.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Utku on 26.03.2016.
 */
public class MovieAdapter extends BaseAdapter {
    private ArrayList<Movie> movlist;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Context context, ArrayList<Movie> movieList){
        movlist = movieList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return movlist.size();
    }

    @Override
    public Object getItem(int position) {
        return movlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_listview, null);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Movie movie = movlist.get(position);
        holder = new ViewHolder();
        holder.textView = (TextView) convertView.findViewById(R.id.response);
        holder.imageView = (ImageView) convertView.findViewById(R.id.poster);
        convertView.setTag(holder);
        if (holder.textView != null) {
            holder.textView.setText(movie.returnresponse());
        }
        if (holder.imageView != null) {
           holder.imageView.setImageBitmap(movie.returnposter());
        }


        return convertView;

    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
