package com.example.tmdbassignment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tmdbassignment.R;
import com.example.tmdbassignment.ui.ViewHolders.MovieDetailsViewHolder;
import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.repositery.MovieItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.tmdbassignment.ui.repositery.String_Constants.image_url;

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsViewHolder> implements Filterable {
       List<MovieDetails> movieList;
       List<MovieDetails> movieListAll;
       Context context;
       MovieItemClickListener movieItemClickListener;

    public MovieDetailsAdapter(List<MovieDetails> movieList, Context context,MovieItemClickListener movieItemClickListener) {
        this.movieList = movieList;
        this.context = context;
        this.movieItemClickListener = movieItemClickListener;
        this.movieListAll = new ArrayList<>(movieList);
    }


    public void setMovieList(List<MovieDetails> movieList) {
        this.movieList = movieList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_row_layout,parent,false);
        return new MovieDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieDetailsViewHolder holder, int position) {
       holder.movieName.setText(movieList.get(position).getOriginal_title());
       if(movieList.get(position).getOverview().length() > 200)
       {
           String rString = movieList.get(position).getOverview().substring(0,200).concat("...");
           holder.Description.setText(rString);
       }
       else
            holder.Description.setText(movieList.get(position).getOverview());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               movieItemClickListener.onMovieClick(view,movieList.get(position));
           }
       });
         String image = image_url.concat(movieList.get(position).getPoster_path());
        Picasso.get().load(image).into(holder.mPoster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MovieDetails> filteredList = new ArrayList<>();
            if(charSequence.toString().isEmpty()){
                filteredList.addAll(movieListAll);
            }
            else{
                for(MovieDetails movie :movieListAll){
                    if(movie.getOriginal_title().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(movie);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
           movieList = new ArrayList<>();
           movieList.addAll((Collection<? extends MovieDetails>) filterResults.values);
           notifyDataSetChanged();
        }
    };
}
