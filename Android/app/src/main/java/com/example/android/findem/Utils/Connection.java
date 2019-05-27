package com.example.android.findem.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Connection {
    protected static String getRequest(URL url) {
        System.out.println("URL " + url.toString());
        try {
            String jsonResponse = null;
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                System.out.println("RESPONSE CODE " + urlConnection.getResponseCode());
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("CONNECTION WORKED");
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static String getRequest(URL url, Object body) {
        System.out.println("URL " + url.toString());
        try {
            String jsonResponse = null;
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                System.out.println("RESPONSE CODE " + urlConnection.getResponseCode());
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("CONNECTION WORKED");
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readStream(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
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


    // creates the URL needed for getting the county URL
    // takes in a String that is the county name and tries to return a correct URL
    // if something fails then a log error message is printed with the attempted county
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

}