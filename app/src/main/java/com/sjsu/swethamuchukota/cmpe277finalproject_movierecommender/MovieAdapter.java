package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ashutosh on 10/12/2016.
 */

public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private LayoutInflater mInflater;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.movies = movies;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_listitem_layout, null);
            holder = new ViewHolder();
            holder.movieName = (TextView) convertView.findViewById(R.id.firstLine);
            holder.genre = (TextView) convertView.findViewById(R.id.secondLine);
            holder.rating = (RatingBar) convertView.findViewById(R.id.ratingBar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.movieName.setText(movies.get(position).getMovieName());
        holder.genre.setText(movies.get(position).getGenre());
        holder.rating.setRating(movies.get(position).getRating());
        return convertView;
    }

    public class ViewHolder {
        TextView movieName;
        TextView genre;
        RatingBar rating;
    }
}
