package com.nabil.amt.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.nabil.amt.R;
import com.nabil.amt.api.ApiClient;
import com.nabil.amt.model.Movie;

public class MovieAdapterVM extends BaseObservable {
    private Movie movie;

    public MovieAdapterVM(Movie movie) {
        this.movie = movie;
    }

    @BindingAdapter("bind:movieImage")
    public static void loadImage(ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl))
            view.setImageResource(R.drawable.placeholder2);
        else
            Glide.with(view.getContext())
                    .load(ApiClient.posterPath + imageUrl)
                    .placeholder(R.drawable.placeholder2).error(R.drawable.placeholder2)
                    .into(view);
    }

    public String movieImage() {
        return movie.getPosterPath();
    }

    public String getTvMovieName() {
        return movie.getOriginalTitle();
    }

    public String getTvMovieReleaseDate() {
        return movie.getReleaseDate();
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyChange();
    }
}
