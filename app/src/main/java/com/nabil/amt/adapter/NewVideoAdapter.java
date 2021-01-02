package com.nabil.amt.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nabil.amt.R;
import com.nabil.amt.databinding.AdapterNewVideoBinding;
import com.nabil.amt.model.Movie;

import java.util.List;

public class NewVideoAdapter extends RecyclerView.Adapter<NewVideoAdapter.ViewHolder> {
    private List<Movie> movies;

    public NewVideoAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterNewVideoBinding adapterNewVideoBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_new_video,
                        parent, false);
        return new ViewHolder(adapterNewVideoBinding);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindService(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AdapterNewVideoBinding adapterNewVideoBinding;

        private ViewHolder(AdapterNewVideoBinding adapterNewVideoBinding) {
            super(adapterNewVideoBinding.itemNewVideo);
            this.adapterNewVideoBinding = adapterNewVideoBinding;
        }

        void bindService(Movie movie) {
            if (adapterNewVideoBinding.getNewVideoAdapterVM() == null) {
                adapterNewVideoBinding.setNewVideoAdapterVM(new NewVideoAdapterVM(movie));
            } else {
                adapterNewVideoBinding.getNewVideoAdapterVM().setMovie(movie);
            }
        }
    }
}
