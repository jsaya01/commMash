package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.Match;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;

public class MatchLoader {
    private static class MatchParsing {
        public static final String UID = "uid";
        public static final String FIRST_NAME = "fname";
        public static final String DATE = "dt";
        public static final String IMAGE_PATH = "imagepath";
        public static final String DESCRIPTION = "description";
    }

    private static final String GET_URL = "https://findem-back.herokuapp.com/matches";
    private static final String LOG_TAG = "MatchLoader";

    public static ArrayList<Match> getMatchesOfUid(long id) {
        Uri requesting = Uri.parse(GET_URL).buildUpon().appendQueryParameter("uid", String.valueOf(id)).build();
        String response = Connection.getStream(requesting);

        if (response == null) {
            Log.e(LOG_TAG, "Error retrieving response for matches");
            return null;
        }

        ArrayList<Match> matches = parseMatches(response);
        if (matches == null) {
            Log.e(LOG_TAG, "Error parsing matches");
            return null;
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

                String newTime = object.getString(MatchParsing.DATE).substring(0,10) +
                        " " + object.getString(MatchParsing.DATE).substring(11, 23);
                Log.e(LOG_TAG, newTime);

                matches.add(
                        new Match(
                                object.getLong(MatchParsing.UID),
                                object.getString(MatchParsing.FIRST_NAME),
                                Timestamp.valueOf(newTime),
                                object.getString(MatchParsing.IMAGE_PATH),
                                object.getString(MatchParsing.DESCRIPTION)
                        )
                );
            }

            return matches;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing matches!");
            return null;
        }
    }
}
