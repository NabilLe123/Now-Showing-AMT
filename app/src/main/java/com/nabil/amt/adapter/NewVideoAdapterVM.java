package com.nabil.amt.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.nabil.amt.R;
import com.nabil.amt.api.ApiClient;
import com.nabil.amt.model.Movie;

public class NewVideoAdapterVM extends BaseObservable {
    private Movie movie;

    public NewVideoAdapterVM(Movie movie) {
        this.movie = movie;
    }

    @BindingAdapter("bind:newVideoImage")
    public static void loadImage(ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl))
            view.setImageResource(R.drawable.placeholder1);
        else
            Glide.with(view.getContext())
                    .load(ApiClient.backdropPath + imageUrl)
                    .placeholder(R.drawable.placeholder1).error(R.drawable.placeholder1)
                    .into(view);
    }

    public String newVideoImage() {
        return movie.getBackdropPath();
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