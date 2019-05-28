package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import com.example.android.findem.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class UserLoader {

    private static final String USER_BASE_URL = "https://findem-back.herokuapp.com/user";
    private static final String LOG_TAG = "UserLoader";

    private static class UserParsing {
        static final String FNAME = "fname";
        static final String LNAME = "lname";
        static final String USERNAME = "username";
        static final String PASSWORD = "password";
        static final String DESCRIPTION = "description";
    }

    public static User getUserByUserName(String username) {
        Uri requesting;

        requesting = Uri.parse(USER_BASE_URL).buildUpon().appendQueryParameter("username", username).build();

        Log.d(LOG_TAG, "Uri is " + requesting.toString());
        URL url = Connection.getURL(requesting);

        if (url == null) {
            Log.d(LOG_TAG, "Url is null! Investigate now!");
            return null;
        }

        Log.d(LOG_TAG, "Url is " + url.toString());

        try {
            String response = Connection.getRequest(url);
            Log.d(LOG_TAG, response);
            JSONObject json;
            json = new JSONObject(response);
            return new User(
                    json.getString(UserParsing.FNAME),
                    json.getString(UserParsing.LNAME),
                    json.getString(UserParsing.USERNAME),
                    json.getString(UserParsing.PASSWORD),
                    json.getString(UserLoader.UserParsing.DESCRIPTION));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing user!");
            return null;
        }
    }
}