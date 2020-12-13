package com.example.tmdbassignment.ui.models;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

public class MovieDetails {
    @SerializedName("original_title")
      private String original_title;
    @SerializedName("overview")
      private String overview;
    @SerializedName("poster_path")
        private   String poster_path;
    @SerializedName("release_date")
        private  String release_date;

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
