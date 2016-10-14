package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.util.Log;

import org.json.JSONArray;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by swethamuchukota on 10/10/16.
 */
public class MovieService {

    private static final String API_KEY = "AIzaSyBL2v6v6rDfzNQcgNJLJcS0WOrhWO1MLf4";

    public List<UserRecommendations> findMoviesList(JSONArray jsonObject) {

        String moviesUri = "http://" + CommonUtils.HOST_PORT + "/api/v1.0/movies";
        String recommendationsUri = "http://" + CommonUtils.HOST_PORT + "/api/v1.0/recommendations";

        try {
            CommonUtils.httpPostRequest(moviesUri,jsonObject);
            String json = CommonUtils.httpGetRequest(recommendationsUri);
            while(json == null || json.trim().isEmpty()) {
                Thread.sleep(5000); // 5 secs
                json = CommonUtils.httpGetRequest(recommendationsUri);
            }
            System.out.println(json);
            //JSONObject object = new JSONObject(json);
            //JSONArray array = object.getJSONArray("results");
            List<UserRecommendations> userRecommendationsList = CommonUtils.convertJsonArrayToList(json, UserRecommendations.class);
            Log.d("returned movies:", userRecommendationsList.toString());
            return userRecommendationsList;
        } catch (Exception ex) {
            Logger.getLogger(MovieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
