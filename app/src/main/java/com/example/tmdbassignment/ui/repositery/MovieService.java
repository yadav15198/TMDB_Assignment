package com.example.tmdbassignment.ui.repositery;

import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.models.MovieRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movie/now_playing")
    Call<MovieRequest> getNowPlayingMovies(@Query("api_key") String key);
    @GET("movie/top_rated")
    Call<MovieRequest>getTopRatedMovies(@Query("api_key") String key);

}
