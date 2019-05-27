package com.example.android.findem.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.Models.Community;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.net.URL;
import java.util.ArrayList;

public class CommunityLoader {
    private static class CommunityProfileParsing {
        public static final String CID = "cid";
        public static final String UID = "uid";
        public static final String DESCRIPTION = "description";
    }

    private static class CommunityParsing {
        public static final String CID = "cid";
        public static final String NAME = "name";
        public static final String IMAGE_PATH = "imagepath";
        public static final String DESCRIPTION = "description";
    }

    private static final String COMMUNITY_BASE_URL = "https://findem-back.herokuapp.com/cm.community";
    private static final String COMMUNITY_PROFILE_BASE_URL = "https://findem-back.herokuapp.com/communityUserProfile";
    private static final String LOG_TAG = "CommunityLoader";

    public static ArrayList<Community> getAllCommunities(int id) {
        String response = getCidResponse(String.valueOf(id));

        if (response == null) {
            Log.e(LOG_TAG, "Error retrieving cid response for communities");
            return null;
        }

        ArrayList<Integer> cids = parseCids(response);
        if (cids == null) {
            Log.e(LOG_TAG, "Error parsing cids");
            return null;
        }

        response = getCommunitiesWithCidsResponse(cids);

        if (response == null) {
            Log.e(LOG_TAG, "Error retrieving response for communities");
            return null;
        }

        ArrayList<Community> communities = parseCommunities(response);
        if (communities == null) {
            Log.e(LOG_TAG, "Error parsing communities");
            return null;
        }

        return communities;
    }

    private static ArrayList<Integer> parseCids(String response) {
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            ArrayList<Integer> cids = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);

                cids.add(object.getInt(CommunityProfileParsing.CID));
            }

            return cids;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Could not parse response into JSONArray");
            return null;
        }
    }

    private static ArrayList<Community> parseCommunities(String response) {
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            ArrayList<Community> communities = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);

                communities.add(
                        new Community(
                                object.getString(CommunityParsing.NAME),
                                object.getString(CommunityParsing.IMAGE_PATH),
                                object.getString(CommunityParsing.DESCRIPTION)
                        )
                );
            }

            return communities;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing communities!");
            return null;
        }
    }

    // URL string is what will be appended, needs to have ? and other format stuff
    private static String getCidResponse(String id) {
        Uri requesting;

        requesting = Uri.parse(COMMUNITY_PROFILE_BASE_URL).buildUpon().appendQueryParameter("uid", id).build();

        Log.d(LOG_TAG, "Uri is " + requesting.toString());
        URL url = Connection.getURL(requesting);

        if (url == null) {
            Log.d(LOG_TAG, "Url is null! Investigate now!");
            return null;
        }

        Log.d(LOG_TAG, "Url is " + url.toString());
        return Connection.getRequest(url);
    }

    private static String getCommunitiesWithCidsResponse(ArrayList<Integer> cids) {
        Uri requesting;

        requesting = Uri.parse(COMMUNITY_BASE_URL).buildUpon().build();

        Log.d(LOG_TAG, "Uri is " + requesting.toString());
        URL url = Connection.getURL(requesting);

        if (url == null) {
            Log.d(LOG_TAG, "Url is null! Investigate now!");
            return null;
        }

        Log.d(LOG_TAG, "Url is " + url.toString());
        return Connection.getRequest(url);
    }
}