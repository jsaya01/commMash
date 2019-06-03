package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class UserLoader {

    private UserLoader() {
        throw new IllegalStateException("Utility class");
    }

    private static final String USER_BASE_URL = "https://findem-back.herokuapp.com/user";
    private static final String LOG_TAG = "UserLoader";

    private static class UserParsing {
        static final String UID = "uid";
        static final String FNAME = "fname";
        static final String LNAME = "lname";
        static final String USERNAME = "username";
        static final String PARSEWORD = "password";
        static final String DESCRIPTION = "description";
        static final String IMAGEPATH = "imagePath";
    }

    public static User getUserByUserName(String username) {
        Uri requesting;

        requesting = Uri.parse(USER_BASE_URL).buildUpon().appendQueryParameter(UserParsing.USERNAME, username).build();

        Log.d(LOG_TAG, "Uri is " + requesting.toString());
        URL url = Connection.getURL(requesting);

        if (url == null) {
            Log.d(LOG_TAG, "Url is null! Investigate now!");
            return null;
        }

        Log.d(LOG_TAG, "Url is " + url.toString());

        try {
            String response = Connection.getRequest(url);

            if (response == null) {
                return null;
            }

            Log.d(LOG_TAG, response);
            JSONObject json;
            json = new JSONObject(response);
            User u = new User(
                    json.getString(UserParsing.FNAME),
                    json.getString(UserParsing.LNAME),
                    json.getString(UserParsing.USERNAME),
                    json.getString(UserParsing.PARSEWORD),
                    json.getString(UserLoader.UserParsing.DESCRIPTION)
            );
            u.setUid(json.getLong(UserParsing.UID));
            return u;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing user!");
            return null;
        }
    }
}
