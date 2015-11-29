package com.cloudskol.cloudroid.spotify;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.cloudskol.cloudroid.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tham
 *
 * Activity class for spotify application
 */
public class SpotifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);
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

        renderMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spotify, menu);
        return true;
    }

    private void renderMovies() {

        String[] moviesArray = {
                "Golden eye", "The Shooter", "Tomorrow never dies"
        };

        final ArrayList<String> movies = new ArrayList<String>(Arrays.asList(moviesArray));

        final GridView spotifyGridView = (GridView) findViewById(R.id.gridView_spotify);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item_spotify, R.id.list_item_spotify_textview, movies);
        spotifyGridView.setAdapter(arrayAdapter);
    }
}
