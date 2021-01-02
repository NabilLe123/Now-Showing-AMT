package com.nabil.amt.api;

import com.nabil.amt.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("3/movie/upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-US&page=1&region=IN|US&with_release_type=2|3")
    Call<MovieResponse> getMovies();
}
