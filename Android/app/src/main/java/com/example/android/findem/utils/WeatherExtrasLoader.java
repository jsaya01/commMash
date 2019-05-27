package com.example.android.findem.utils;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class WeatherExtrasLoader {
    /*
    private static class JsonParsing {
        public static final String HOUR = "hour";

        public static final String SWELL_URL = "http://api.spitcast.com/api/county/swell";

        public static final String TIDE_URL = "http://api.spitcast.com/api/county/tide";
        public static final String TIDE_FT = "tide";

        public static final String WIND_URL = "http://api.spitcast.com/api/county/wind";
        public static final String WIND_MPH = "speed_mph";
        public static final String WIND_DIRECTION_DEGREES = "direction_degrees";
        public static final String WIND_DIRECTION_COMPASS = "direction_text";
    }

    private static final String LOG_TAG = "WeatherExtrasLoader";
    private static String county;

    public static CountyWeatherExtras getCountyWeatherExtras(String countyName) {
        countyName = countyName.replace(" ", "-");
        county = countyName.toLowerCase();

        CountyWeatherExtras extras = new CountyWeatherExtras();
        extras.setSwellDay(getSwellDay());
        extras.setTideDay(getTideDay());
        extras.setWindDay(getWindDay());

        return extras;
    }

    private static ArrayList<Swell> getSwellDay() {
        try {
            JSONArray jsonArray = new JSONArray(getResponse(county, JsonParsing.SWELL_URL));

            ArrayList<Swell> swells = new ArrayList<>();

            return swells;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Swell Exception: ", "Exception: " + e);
            return null;
        }
    }

    private static ArrayList<Tide> getTideDay() {
        try {
            JSONArray jsonArray = new JSONArray(getResponse(county, JsonParsing.TIDE_URL));

            ArrayList<Tide> tides = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);

                tides.add(new Tide(object.getString(JsonParsing.HOUR), object.getDouble(JsonParsing.TIDE_FT)));
            }

            return tides;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Tide Exception: " + e);
            return null;
        }
    }

    private static ArrayList<Wind> getWindDay() {
        try {
            JSONArray jsonArray = new JSONArray(getResponse(county, JsonParsing.WIND_URL));

            ArrayList<Wind> winds = new ArrayList<>();
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject object = jsonArray.getJSONObject(x);

                winds.add(new Wind(object.getString(JsonParsing.WIND_DIRECTION_DEGREES),
                        object.getString(JsonParsing.WIND_DIRECTION_COMPASS),
                        object.getString(JsonParsing.HOUR),
                        object.getDouble(JsonParsing.WIND_MPH)));
            }

            return winds;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Wind Exception: ", "Exception: " + e);
            return null;
        }
    }

    private static String getResponse(String countyName, String URL) {
        Uri requesting;

        requesting = Uri.parse(URL).buildUpon().appendPath(countyName).build();

        Log.d(LOG_TAG, "Uri is " + requesting.toString());
        URL countyRequest = Connection.getURL(requesting);
        Log.d(LOG_TAG, "Url is " + countyRequest.toString());
        return Connection.urlRequest(countyRequest);
    }
    */
}
