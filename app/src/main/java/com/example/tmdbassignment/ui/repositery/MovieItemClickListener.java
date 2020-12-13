package com.example.tmdbassignment.ui.repositery;

import android.view.View;

import com.example.tmdbassignment.ui.models.MovieDetails;

public interface MovieItemClickListener {
    public void onMovieClick(View view , MovieDetails movieDetails);
}
