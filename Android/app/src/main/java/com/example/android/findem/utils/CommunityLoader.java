package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.Community;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private static final String GET_URL = "https://findem-back.herokuapp.com/communityuserprofile/getcomms";
    private static final String GET_ALL_URL = "https://findem-back.herokuapp.com/community/all";
    private static final String LOG_TAG = "CommunityLoader";

    public static ArrayList<Community> getAllCommunities() {
        Uri requesting = Uri.parse(GET_ALL_URL).buildUpon().build();
        String response = getStream(requesting);

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

    public static ArrayList<Community> getCommunitiesOfUid(int id) {
        Uri requesting = Uri.parse(GET_URL).buildUpon().appendQueryParameter("uid", String.valueOf(id)).build();
        String response = getStream(requesting);

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

    private static String getStream(Uri requesting) {
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