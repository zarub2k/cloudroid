package com.cloudskol.cloudroid.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.cloudskol.cloudroid.R;
import com.cloudskol.cloudroid.common.CloudroidPropertyReader;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int movieId = getIntent().getIntExtra(Intent.EXTRA_TEXT, -1);
        renderMovieDetails(movieId);

//        TextView textView = (TextView) findViewById(R.id.movie_details_textview);
//        textView.setText(movieTitle);
    }

    private void renderMovieDetails(int movieId) {
        final CloudroidPropertyReader cloudroidPropertyReader = CloudroidPropertyReader
                .getInstance(getBaseContext());
        final SpotifyUriBuilder spotifyUriBuilder = new SpotifyUriBuilder(cloudroidPropertyReader);

        MovieDetailsAsyncTask movieDetailsTask = new MovieDetailsAsyncTask(this);
        movieDetailsTask.execute(spotifyUriBuilder.getMovieDetails(movieId));
    }
}
