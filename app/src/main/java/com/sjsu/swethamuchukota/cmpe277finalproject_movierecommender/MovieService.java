package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by swethamuchukota on 10/10/16.
 */
public class MovieService {

    private static final String API_KEY = "AIzaSyBL2v6v6rDfzNQcgNJLJcS0WOrhWO1MLf4";

    public JSONArray findMoviesList(JSONObject jsonObject) {

        String urlString = makeUrl(jsonObject);
        try {
            String json = getJSON(urlString,jsonObject);

            System.out.println(json);
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");
            Log.d("returned movies:",array + "");
            return array;
        } catch (JSONException ex) {
            Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   //https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=your_api_key
    private String makeUrl(JSONObject jsonObject) {
        StringBuilder urlString = new StringBuilder("https://maps.googleapis.com/maps/api/place/search/json?");

       // urlString.append("&radius=" + (3 * 1609.34) /* 3 miles */);
       // urlString.append("&sensor=false&key=" + API_KEY);

        return urlString.toString();
    }

    protected String getJSON(String url,JSONObject jsonObject) {
        StringBuilder content = new StringBuilder();

        try {

            URL object=new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            //URLConnection urlConnection = new URL(url).openConnection();
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json; charset=UTF-8");
            con.setRequestMethod("POST");

            /*OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonObject.toString());
            wr.flush();*/

            OutputStream os = con.getOutputStream();
            os.write(jsonObject.toString().getBytes("UTF-8"));
            os.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()), 8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }

            bufferedReader.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
        return content.toString();
    }
}
