package com.example.android.az.popularmoviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(@NonNull Context context, @NonNull List<Movie> objects) {
        super(context, 0, objects);
    }

    ViewHolder holder;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movies_list_item, parent, false);
            //Use Holder
            holder = new ViewHolder();
            //Find ImageView
            holder.posterImage = listItemView.findViewById(R.id.iv_movie_image);
            listItemView.setTag(holder);

        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        // Find the movie at the given position in the list of movies
        Movie currentMovie = getItem(position);
        if (currentMovie.getmPosterImageThumbnail() != null || !currentMovie.getmPosterImageThumbnail().isEmpty()) {
            // Build the image url
            String baseUrl = "http://image.tmdb.org/t/p/w185";
            String posterAddress = currentMovie.getmPosterImageThumbnail();
            String url = baseUrl + posterAddress;

            // Display the PosterImageThumbnail of the current movie in that ImageView
            Picasso.with(getContext()).load(url).into(holder.posterImage);
        }

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    static class ViewHolder {

        ImageView posterImage;


    }
}
