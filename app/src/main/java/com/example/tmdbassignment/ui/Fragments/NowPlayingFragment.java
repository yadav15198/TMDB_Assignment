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
import com.example.tmdbassignment.ui.viewModel.NowPlayingViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class NowPlayingFragment extends Fragment implements MovieItemClickListener {

    private NowPlayingViewModel nowPlayingViewModel ;
    private MovieRequest mList;
    private RecyclerView recyclerView;
    private  MovieDetailsAdapter adapter;
    private SearchView searchView;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        nowPlayingViewModel = new ViewModelProvider(getActivity()).get(NowPlayingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.movieList_nowShowing);
        searchView = view.findViewById(R.id.search_NS);
        progressBar = view.findViewById(R.id.progress_bar);
        adapter = new MovieDetailsAdapter(new ArrayList<>(),getActivity(),NowPlayingFragment.this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //adapter = new MovieDetailsAdapter(Arrays.asList(mList.getResults()),getActivity(),NowPlayingFragment.this);
                adapter.getFilter().filter(s);
                return false;
            }
        });
        recyclerView.setHasFixedSize(true);
        mList = new MovieRequest();
        LinearLayoutManager layoutManager = new GridLayoutManager(view.getContext(),1);

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        nowPlayingViewModel.getdetails().observe(getActivity(), new Observer<MovieRequest>() {
            @Override
            public void onChanged(MovieRequest movieDetails) {
                if(movieDetails != null){
                    mList = movieDetails;
                    adapter = new MovieDetailsAdapter(Arrays.asList(mList.getResults()),getActivity(),NowPlayingFragment.this);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(view.INVISIBLE);
                    adapter.setMovieList(Arrays.asList(mList.getResults()));
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getContext(),"MovieList Not found",Toast.LENGTH_LONG).show();
                }
            }
        });
        nowPlayingViewModel.makeApiCallforNPM();

            return view;

    }


    @Override
    public void onMovieClick(View view,MovieDetails movieDetails) {
        Intent intent = new Intent(getActivity(), Movie_InfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("POSTER",movieDetails.getPoster_path());
        bundle.putString("M_NAME",movieDetails.getOriginal_title());
        bundle.putString("M_RELEASE",movieDetails.getRelease_date());
        bundle.putString("DESC",movieDetails.getOverview());
        intent.putExtras(bundle);
        startActivity(intent);
//        String name = movieDetails.getRelease_date();
//       Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
    }

}
