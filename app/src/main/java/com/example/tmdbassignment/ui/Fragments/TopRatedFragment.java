package com.example.tmdbassignment.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbassignment.R;
import com.example.tmdbassignment.ui.Activity.Movie_InfoActivity;
import com.example.tmdbassignment.ui.adapter.MovieDetailsAdapter;
import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.models.MovieRequest;
import com.example.tmdbassignment.ui.repositery.MovieItemClickListener;
import com.example.tmdbassignment.ui.viewModel.TopRatedViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class TopRatedFragment extends Fragment implements MovieItemClickListener {

    private TopRatedViewModel topRatedViewModel;
    private MovieRequest mList;
    private RecyclerView recyclerView;
    private MovieDetailsAdapter adapter;
    private SearchView searchView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        topRatedViewModel= new  ViewModelProvider(getActivity()).get(TopRatedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);
        mList = new MovieRequest();
        recyclerView = view.findViewById(R.id.movieList_topRated);
        progressBar = view.findViewById(R.id.progress_bar);
        adapter = new MovieDetailsAdapter(new ArrayList<>(),getActivity(),TopRatedFragment.this);

        searchView = view.findViewById(R.id.search_TR);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        LinearLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        topRatedViewModel.getMdetails().observe(getActivity(), new Observer<MovieRequest>() {
            @Override
            public void onChanged(MovieRequest movieDetails) {
                if(movieDetails != null){
                    mList = movieDetails;
                    adapter = new MovieDetailsAdapter(Arrays.asList(mList.getResults()),getActivity(),TopRatedFragment.this);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(view.INVISIBLE);
                    adapter.setMovieList(Arrays.asList(mList.getResults()));
                }
                else{
                    Toast.makeText(getContext(),"MovieList Not found",Toast.LENGTH_LONG).show();
                }
            }
        });
        topRatedViewModel.makeApiCallforTRP();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // topRatedViewModel = new ViewModelProvider(new MainActivity()).get(TopRatedViewModel.class);
    }

    @Override
    public void onMovieClick(View view, MovieDetails movieDetails) {
        Intent intent = new Intent(getActivity(), Movie_InfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("POSTER",movieDetails.getPoster_path());
        bundle.putString("M_NAME",movieDetails.getOriginal_title());
        bundle.putString("M_RELEASE",movieDetails.getRelease_date());
        bundle.putString("DESC",movieDetails.getOverview());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}