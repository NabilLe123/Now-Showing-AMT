package com.nabil.amt;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nabil.amt.adapter.MovieAdapter;
import com.nabil.amt.adapter.NewVideoAdapter;
import com.nabil.amt.database.RoomDbInstance;
import com.nabil.amt.databinding.ActivityMainBinding;
import com.nabil.amt.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NewVideoAdapter newVideoAdapter;
    private MovieAdapter newReleaseAdapter;
    private MovieAdapter bestRatedMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBinding();
    }

    private void initDataBinding() {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityVM mainActivityVM = new MainActivityVM(this);
        activityMainBinding.setMainActivityVM(mainActivityVM);

        RoomDbInstance.getAppDb(this).movieDao().fetchAllMovies().observe(this, movies -> {
            Log.d("now_showing", "fetchAllMovies: " + movies.size());

            setNewVideoList(activityMainBinding.rvNewVideo, movies);
            setNewReleaseList(activityMainBinding.rvNewRelease, movies);
            setBestRatedList(activityMainBinding.rvBestRated, movies);
        });
        mainActivityVM.loadMovies();
    }

    private void setNewVideoList(RecyclerView rvNewVideo, List<Movie> movies) {
        List<Movie> movies1 = new ArrayList<>();
        for (Movie movie : movies) {
            if (!TextUtils.isEmpty(movie.getBackdropPath()))
                movies1.add(movie);
        }

        if (newVideoAdapter == null) {
            rvNewVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            newVideoAdapter = new NewVideoAdapter(movies1);
            rvNewVideo.setAdapter(newVideoAdapter);
        } else {
            newVideoAdapter.setMovies(movies1);
        }
    }

    private void setNewReleaseList(RecyclerView rvNewRelease, List<Movie> movies) {
        if (newReleaseAdapter == null) {
            rvNewRelease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            newReleaseAdapter = new MovieAdapter(movies);
            rvNewRelease.setAdapter(newReleaseAdapter);
        } else {
            newReleaseAdapter.setMovies(movies);
        }
    }

    private void setBestRatedList(RecyclerView rvBestRated, List<Movie> movies) {
        List<Movie> movies2 = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getVoteAverage() > 5)
                movies2.add(movie);
        }

        if (bestRatedMovieAdapter == null) {
            rvBestRated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            bestRatedMovieAdapter = new MovieAdapter(movies2);
            rvBestRated.setAdapter(bestRatedMovieAdapter);
        } else {
            bestRatedMovieAdapter.setMovies(movies2);
        }
    }
}