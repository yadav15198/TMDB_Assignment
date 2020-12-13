package com.example.tmdbassignment.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tmdbassignment.R;
import com.example.tmdbassignment.ui.Fragments.NowPlayingFragment;
import com.example.tmdbassignment.ui.Fragments.TopRatedFragment;
import com.example.tmdbassignment.ui.adapter.MovieDetailsAdapter;
import com.example.tmdbassignment.ui.models.MovieDetails;
import com.example.tmdbassignment.ui.viewModel.NowPlayingViewModel;
import com.example.tmdbassignment.ui.viewModel.TopRatedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment selectedFragment;
    BottomNavigationView navView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater minflater = getMenuInflater();
        minflater.inflate(R.menu.dark_mode_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.day_mode_item){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        if(item.getItemId() == R.id.night_mode_item){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
//        reset();
        return true;
    }

    private void reset() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        selectedFragment = new NowPlayingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        selectedFragment = new NowPlayingFragment();
                        break;
                    case R.id.navigation_notifications:
                        selectedFragment = new TopRatedFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();
                return true;
            }
        });



    }


}