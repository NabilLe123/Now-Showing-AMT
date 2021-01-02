package com.nabil.amt;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;

import com.nabil.amt.api.ApiClient;
import com.nabil.amt.api.ApiInterface;
import com.nabil.amt.database.RoomDbInstance;
import com.nabil.amt.model.Movie;
import com.nabil.amt.model.MovieResponse;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityVM {
    private final Context context;

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
                    Log.d("now_showing", "loadMovies: " + movieResponse.getMovies().size());
                    saveMovies(movieResponse.getMovies());
                } else
                    Log.d("now_showing", "loadMovies: null");

                loadingFinish();
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                loadingFinish();
            }
        });
    }

    private void saveMovies(List<Movie> movies) {
        new Thread(() -> RoomDbInstance.getAppDb(context).movieDao().insertMovies(movies)).start();
    }

    private void loadingFinish() {
        isLoading.set(false);
    }
}
