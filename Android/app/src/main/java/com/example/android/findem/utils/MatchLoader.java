package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MatchLoader {
    private MatchLoader() {
        throw new IllegalStateException("Utility class");
    }

    private static class MatchParsing {
        static final String UID = "uid";
        static final String FIRST_NAME = "fname";
        static final String DATE = "dt";
        static final String IMAGE_PATH = "imagepath";
        static final String DESCRIPTION = "description";
    }

    private static final String GET_URL = "https://findem-back.herokuapp.com/matches";
    private static final String LOG_TAG = "MatchLoader";

    public static List<Match> getMatchesOfUid(long id) {
        Uri requesting = Uri.parse(GET_URL).buildUpon().appendQueryParameter("uid", String.valueOf(id)).build();
        String response = Connection.getStream(requesting);

        if (response == null) {
            Log.e(LOG_TAG, "Error retrieving response for matches");
            return new ArrayList<>();
        }

        ArrayList<Match> matches = parseMatches(response);
        if (matches == null) {
            Log.e(LOG_TAG, "Error parsing matches");
            return new ArrayList<>();
        }

        return matches;
    }

    private static ArrayList<Match> parseMatches(String response) {
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            ArrayList<Match> matches = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);
                Log.e(LOG_TAG, object.toString());

                matches.add(
                        new Match(
                                object.getLong(MatchParsing.UID),
                                object.getString(MatchParsing.FIRST_NAME),
                                Timestamp.valueOf(object.getString(MatchParsing.DATE)),
                                object.getString(MatchParsing.IMAGE_PATH),
                                object.getString(MatchParsing.DESCRIPTION)
                        )
                );
            }

            return matches;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing matches!");
            return new ArrayList<>();
        }
    }
}
