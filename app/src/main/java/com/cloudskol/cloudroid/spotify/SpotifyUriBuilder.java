package com.cloudskol.cloudroid.spotify;

import android.net.Uri;
import android.util.Log;

import com.cloudskol.cloudroid.common.CloudroidPropertyKeys;
import com.cloudskol.cloudroid.common.CloudroidPropertyReader;

/**
 * @author tham
 */
public class SpotifyUriBuilder {

    private static final String LOG_TAG = SpotifyUriBuilder.class.getSimpleName();

    private CloudroidPropertyReader propertyReader_;

    public SpotifyUriBuilder(CloudroidPropertyReader propertyReader) {
        propertyReader_ = propertyReader;
    }

    public Uri discoverMoviesUri() {
        Uri discoverMoviesUri = Uri.parse(propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_DISCOVER_MOVIE))
                .buildUpon()
                .appendQueryParameter("api_key", propertyReader_.getValue(CloudroidPropertyKeys.SPOTIFY_API_KEY))
                .build();

        Log.v(LOG_TAG, "Discover movies Uri: " + discoverMoviesUri.toString());
        return discoverMoviesUri;
    }
}
