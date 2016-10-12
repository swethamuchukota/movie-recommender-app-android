package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by rwatsh on 10/11/16.
 */

public class UserRecommendations implements Serializable {
    private String id;
    private int movieId;
    private int rating;
    private int userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRecommendations{" +
                "id='" + id + '\'' +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", userId=" + userId +
                '}';
    }
}
