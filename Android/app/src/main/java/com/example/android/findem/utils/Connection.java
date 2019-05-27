package com.example.android.findem.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.nio.charset.Charset;

public class Connection {

    public static void postRequest(String route, JSONObject data){
        try {
            URL url = new URL("https://findem-back.herokuapp.com" + route);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStream out = new BufferedOutputStream(conn.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data.toString());
            writer.flush();
            writer.close();
            out.close();

            System.out.println("RESPONSE CODE from postRequest " + conn.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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


    // downloads the image from a specified URL
    // if a failure has occurred, a log error message is printed with the url
    public static Bitmap downloadImage(String url) {
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", "Could not access the specified URL: " + url);
            e.printStackTrace();
        }
        return image;
    }

}