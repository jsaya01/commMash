package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Connection {

    private static final String LOG_TAG = "Connection";

    private Connection() {
        throw new IllegalStateException("Utility class");
    }

    public static void postRequest(String route, JSONObject data) {
        try {
            URL url = new URL("https://findem-back.herokuapp.com" + route);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(data.toString());
            writer.flush();
            writer.close();
            out.close();

            Log.d(LOG_TAG, "RESPONSE CODE from postRequest " + conn.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static String getRequest(URL url) {
        Log.d(LOG_TAG, "URL " + url.toString());
        try {
            return getRequestHelper(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static String getRequestHelper(URL url) throws java.io.IOException {
        String jsonResponse = null;
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.d(LOG_TAG, "RESPONSE CODE " + urlConnection.getResponseCode());
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d(LOG_TAG, "CONNECTION WORKED");
                inputStream = urlConnection.getInputStream();
                jsonResponse = readStream(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return jsonResponse;
    }

    private static String readStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                builder.append(currentLine);
                currentLine = bufferedReader.readLine();
            }
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL getURL(Uri requesting) {
        try {
            return new URL(requesting.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("URL Failure", "URI: " + requesting.toString() +
                    " is not valid");
            return null;
        }
    }

    public static String getStream(Uri requesting) {
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