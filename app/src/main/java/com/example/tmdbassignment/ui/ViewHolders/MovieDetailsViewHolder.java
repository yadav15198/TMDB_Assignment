package com.example.tmdbassignment.ui.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbassignment.R;

public class MovieDetailsViewHolder extends RecyclerView.ViewHolder {
    public TextView movieName;
     public TextView Description;
    public ImageView mPoster;

    public MovieDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        movieName = itemView.findViewById(R.id.movieName);
        Description = itemView.findViewById(R.id.movieDesc);
        mPoster = itemView.findViewById(R.id.movieImage);

    }
}
