package com.example.tmdbassignment.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Float4;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tmdbassignment.R;
import com.squareup.picasso.Picasso;

import static com.example.tmdbassignment.ui.repositery.String_Constants.image_url;

public class Movie_InfoActivity extends AppCompatActivity {


    ImageView pimage ;
    TextView mText;
    TextView mRelease;
    TextView mDesc;
    String poster,mName,mRel,description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie__info);
        pimage = findViewById(R.id.poster_image);
        mText = findViewById(R.id.mName_id);
        mRelease = findViewById(R.id.ReleaseDate_id);
        mDesc = findViewById(R.id.desc);

        Intent intent = getIntent();
        if(intent != null){
             poster = intent.getStringExtra("POSTER");
             mName = intent.getStringExtra("M_NAME");
             mRel = intent.getStringExtra("M_RELEASE");
             description = intent.getStringExtra("DESC");
            String image = image_url.concat(poster);
            Picasso.get().load(image).into(pimage);
            mText.setText(mName);
            mRelease.setText(mRel);
            mDesc.setText(description);
        }

    }
}