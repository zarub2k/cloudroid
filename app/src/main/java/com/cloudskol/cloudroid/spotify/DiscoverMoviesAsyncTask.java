package com.cloudskol.cloudroid.spotify;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.cloudskol.cloudroid.common.CloudroidException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tham
 *
 * Async task handler for fetching movies list from the remote Uri
 */
public class DiscoverMoviesAsyncTask extends AsyncTask<Uri, Void, List<Movie>> {

    private static final String LOG_TAG = DiscoverMoviesAsyncTask.class.getSimpleName();

    private ArrayAdapter<Movie> arrayAdapter_;

    public DiscoverMoviesAsyncTask(ArrayAdapter<Movie> arrayAdapter) {
        arrayAdapter_ = arrayAdapter;
    }

    @Override
    protected List<Movie> doInBackground(Uri... Uris) {
        if (Uris.length <= 0) {
            return null;
        }

        final String moviesJsonString;
        Uri discoverMoviesUri = Uris[0];
        final SpotifyUrlConnector spotifyUrlConnector = new SpotifyUrlConnector();
        try {
            moviesJsonString = spotifyUrlConnector.getJson(discoverMoviesUri.toString());
        } catch (CloudroidException e) {
            Log.e(LOG_TAG, "Error while fetching JSON", e);
            return null;
        }

        Log.v(LOG_TAG, moviesJsonString);

        return parseMoviesJson(moviesJsonString);
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return;
        }

        arrayAdapter_.clear();
        arrayAdapter_.addAll(movies);
    }

    private List<Movie> parseMoviesJson(String moviesJsonString) {
        try {
            final JSONObject moviesJson = new JSONObject(moviesJsonString);
            final JSONArray results = moviesJson.getJSONArray("results");

            Log.v(LOG_TAG,  "Size of the result array is : " + results.length());

            return getMovies(results);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing movies JSON", e);
        }

        return null;
    }

    private List<Movie> getMovies(JSONArray moviesJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<Movie>(moviesJsonArray.length());

        for (int i = 0; i < moviesJsonArray.length(); i++) {
            movies.add(getMovie(moviesJsonArray.getJSONObject(i)));
        }

        return movies;
    }

    private Movie getMovie(JSONObject movieJson) throws JSONException {
        Movie movie = new Movie(movieJson.getInt("id"), movieJson.getString("title"));
        movie.setOverview(movieJson.getString("overview"));
        movie.setPoster(movieJson.getString("poster_path"));
        return movie;
    }
}
