package com.example.tmdbassignment.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.models.MovieRequest;
import com.example.tmdbassignment.ui.repositery.MovieRepositery;
import com.example.tmdbassignment.ui.repositery.MovieService;
import com.example.tmdbassignment.ui.repositery.String_Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedViewModel extends ViewModel {

    private MutableLiveData<MovieRequest> mdetails;

    public TopRatedViewModel() {
        mdetails = new MutableLiveData<MovieRequest>();
    }

    public LiveData<MovieRequest> getMdetails() {
        return mdetails;
    }

    public void makeApiCallforTRP(){
        MovieService mservice = MovieRepositery.getInstance().create(MovieService.class);
        Call<MovieRequest> call = mservice.getTopRatedMovies(String_Constants.api_key);
        call.enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                mdetails.postValue((MovieRequest) response.body());
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                mdetails.postValue(null);
            }
        });
    }
}