package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.Community;
import com.example.android.findem.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommunityUserLoader {
    private static class UserParsing {
        public static final String FNAME = "fname";
        public static final String LNAME = "lname";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String IMAGE_PATH = "imagepath";
        public static final String DESCRIPTION = "description";
    }

    private static final String GET_USERS_URL = "https://findem-back.herokuapp.com/communityuserprofile/getusers";
    private static final String LOG_TAG = "CommunityUserLoader";

    public static ArrayList<User> getAllUsers(long cid) {
        Uri requesting = Uri.parse(GET_USERS_URL).buildUpon().appendQueryParameter("cid", String.valueOf(cid)).build();
        String response = Connection.getStream(requesting);

        if (response == null) {
            Log.e(LOG_TAG, "Error retrieving response for users in community");
            return null;
        }

        ArrayList<User> users = parseUsers(response);
        if (users == null) {
            Log.e(LOG_TAG, "Error parsing communities");
            return null;
        }

        return users;
    }

    private static ArrayList<User> parseUsers(String response) {
        JSONArray jsonArray;

        try {
            jsonArray = new JSONArray(response);

            ArrayList<User> users = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);

                users.add(
                        new User(
                                object.getString(CommunityUserLoader.UserParsing.FNAME),
                                object.getString(CommunityUserLoader.UserParsing.LNAME),
                                object.getString(CommunityUserLoader.UserParsing.USERNAME),
                                object.getString(CommunityUserLoader.UserParsing.PASSWORD),
                                object.getString(CommunityUserLoader.UserParsing.DESCRIPTION),
                                object.getString(CommunityUserLoader.UserParsing.IMAGE_PATH)
                        )
                );
            }

            return users;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing users!");
            return null;
        }
    }
}
