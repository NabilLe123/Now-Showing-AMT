package com.nabil.amt;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;

import com.nabil.amt.api.ApiClient;
import com.nabil.amt.api.ApiInterface;
import com.nabil.amt.model.Movie;
import com.nabil.amt.model.MovieResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVM {
    private final Context context;
    MutableLiveData<List<Movie>> newVideosList = new MutableLiveData<>();
    MutableLiveData<List<Movie>> newReleasesList = new MutableLiveData<>();
    MutableLiveData<List<Movie>> bestRatedList = new MutableLiveData<>();

    public MainActivityVM(Context context) {
        this.context = context;
    }

    public ObservableBoolean isLoading = new ObservableBoolean(true);

    void loadMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiInterface.getMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null) {
                    Log.d("now_showing", ": " + movieResponse.getMovies().size());
                    handleMovies(movieResponse.getMovies());
                } else
                    Log.d("now_showing", ":null");

                loadingFinish();
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                loadingFinish();
            }
        });
    }

    private void handleMovies(List<Movie> movies) {
        newReleasesList.setValue(movies);

        List<Movie> movies1 = new ArrayList<>();
        for (Movie movie : movies) {
            if (!TextUtils.isEmpty(movie.getBackdropPath()))
                movies1.add(movie);
        }
        newVideosList.setValue(movies1);

        List<Movie> movies2 = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getVoteAverage() > 5)
                movies2.add(movie);
        }
        bestRatedList.setValue(movies2);
    }

    private void loadingFinish() {
        isLoading.set(false);
    }
}
