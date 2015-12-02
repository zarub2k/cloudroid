package com.cloudskol.cloudroid.spotify;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author tham
 */
public class MovieJsonParser {

    private static final String LOG_TAG = MovieJsonParser.class.getSimpleName();

    private static final MovieJsonParser INSTANCE = new MovieJsonParser();

    private MovieJsonParser() {}

    static synchronized MovieJsonParser getInstance() {
        return INSTANCE;
    }

    public Movie getMovie(String movieJsonString) {
        Movie movie = null;
        try {
            movie = getMovie(new JSONObject(movieJsonString));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error while parsing movie JSON", e);
            return null;
        }

        return movie;
    }

    private Movie getMovie(JSONObject movieJson) throws JSONException {
        Movie movie = new Movie(movieJson.getInt("id"), movieJson.getString("title"));
        movie.setOverview(movieJson.getString("overview"));
        movie.setPoster(movieJson.getString("poster_path"));
        return movie;
    }
}
