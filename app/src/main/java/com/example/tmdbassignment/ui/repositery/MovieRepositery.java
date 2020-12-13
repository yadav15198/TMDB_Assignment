package com.example.tmdbassignment.ui.repositery;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepositery  {
    private static Retrofit retrofit;
    private static MovieService movieService;
    public static Retrofit getInstance() {
    if(retrofit == null)
    {
             retrofit = new Retrofit.Builder()
                         .client(providesHttpClient())
                        .baseUrl(String_Constants.tmdb_url)
                        .addConverterFactory(GsonConverterFactory.create()).build();

    }
    return retrofit;
    }
    public  static  MovieService getMovieService(){
        if(movieService == null)
        {
            movieService = getInstance().create(MovieService.class);
        }
        return movieService;
    }
    public static OkHttpClient providesHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(providesHttpLoggingInterceptor())
                .build();
    }

    public static HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
