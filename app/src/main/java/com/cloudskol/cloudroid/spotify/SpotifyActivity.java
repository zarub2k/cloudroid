package com.cloudskol.cloudroid.spotify;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.cloudskol.cloudroid.R;
import com.cloudskol.cloudroid.common.CloudroidPropertyKeys;
import com.cloudskol.cloudroid.common.CloudroidPropertyReader;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author tham
 *
 * Activity class for spotify application
 */
public class SpotifyActivity extends AppCompatActivity {

    private static final String LOG_TAG = SpotifyActivity.class.getSimpleName();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == R.id.action_refresh) {
            final String path = CloudroidPropertyReader.getInstance(getBaseContext())
                    .getValue(CloudroidPropertyKeys.SPOTIFY_PATH);
            final SpotifyAsyncTask spotifyAsyncTask = new SpotifyAsyncTask();
            spotifyAsyncTask.execute(path);

            return true;
        }

        return super.onOptionsItemSelected(item);
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
