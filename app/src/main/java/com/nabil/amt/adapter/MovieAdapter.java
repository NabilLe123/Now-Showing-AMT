package com.nabil.amt.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nabil.amt.R;
import com.nabil.amt.databinding.AdapterMovieBinding;
import com.nabil.amt.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterMovieBinding adapterMovieBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_movie,
                        parent, false);
        return new MovieAdapter.ViewHolder(adapterMovieBinding);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.bindService(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterMovieBinding adapterMovieBinding;

        private ViewHolder(AdapterMovieBinding adapterMovieBinding) {
            super(adapterMovieBinding.itemMovie);
            this.adapterMovieBinding = adapterMovieBinding;
        }

        void bindService(Movie movie) {
            if (adapterMovieBinding.getMovieAdapterVM() == null) {
                adapterMovieBinding.setMovieAdapterVM(new MovieAdapterVM(movie));
            } else {
                adapterMovieBinding.getMovieAdapterVM().setMovie(movie);
            }
        }
    }
}
