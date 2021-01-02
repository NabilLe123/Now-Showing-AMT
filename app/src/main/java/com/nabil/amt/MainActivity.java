package com.nabil.amt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nabil.amt.adapter.MovieAdapter;
import com.nabil.amt.adapter.NewVideoAdapter;
import com.nabil.amt.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MainActivityVM mainActivityVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBinding();
    }

    private void initDataBinding() {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityVM = new MainActivityVM(this);
        activityMainBinding.setMainActivityVM(mainActivityVM);

        mainActivityVM.loadMovies();
        initNewVideoList(activityMainBinding.rvNewVideo);
        initNewReleaseList(activityMainBinding.rvNewRelease);
        initBestRatedList(activityMainBinding.rvBestRated);
    }

    private void initNewVideoList(RecyclerView rvNewVideo) {
        rvNewVideo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        NewVideoAdapter newVideoAdapter = new NewVideoAdapter(new ArrayList<>());
        rvNewVideo.setAdapter(newVideoAdapter);

        mainActivityVM.newVideosList.observe(this, newVideoAdapter::setMovies);
    }

    private void initNewReleaseList(RecyclerView rvNewRelease) {
        rvNewRelease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MovieAdapter movieAdapter = new MovieAdapter(new ArrayList<>());
        rvNewRelease.setAdapter(movieAdapter);

        mainActivityVM.newReleasesList.observe(this, movieAdapter::setMovies);
    }

    private void initBestRatedList(RecyclerView rvBestRated) {
        rvBestRated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MovieAdapter movieAdapter = new MovieAdapter(new ArrayList<>());
        rvBestRated.setAdapter(movieAdapter);

        mainActivityVM.bestRatedList.observe(this, movieAdapter::setMovies);
    }
}