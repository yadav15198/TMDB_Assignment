package com.example.tmdbassignment.ui.viewModel;


import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.models.MovieRequest;
import com.example.tmdbassignment.ui.repositery.MovieRepositery;
import com.example.tmdbassignment.ui.repositery.MovieService;
import com.example.tmdbassignment.ui.repositery.String_Constants;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifTexImage2D;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingViewModel extends ViewModel {

    public MutableLiveData<MovieRequest> details;
    public NowPlayingViewModel() {
        details = new MutableLiveData<MovieRequest>();
    }



//    public NowPlayingViewModel(Application application) {
//        super(application);
//    }

//    public NowPlayingViewModel(MutableLiveData<List<MovieDetails>> details) {
////        super();
//        this.details = details;
//    }

    public LiveData<MovieRequest> getdetails() {
        return details;
    }


    public void makeApiCallforNPM() {
        MovieService service = MovieRepositery.getInstance().create(MovieService.class);
        Call<MovieRequest> call = service.getNowPlayingMovies(String_Constants.api_key);
        call.enqueue(new Callback<MovieRequest>() {
            @Override
            public void onResponse(Call<MovieRequest> call, Response<MovieRequest> response) {
                details.postValue((MovieRequest)response.body());
            }

            @Override
            public void onFailure(Call<MovieRequest> call, Throwable t) {
                details.postValue(null);
            }
        });

    }

}