package com.nabil.amt.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String backdropPath = "https://image.tmdb.org/t/p/w780";//Base URL for the backdrop_path (large thumbnails)
    public static final String posterPath = "https://image.tmdb.org/t/p/w185";// Base URL for the poster_path

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
