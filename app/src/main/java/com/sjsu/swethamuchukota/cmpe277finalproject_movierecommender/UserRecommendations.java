package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by rwatsh on 10/11/16.
 */

public class UserRecommendations implements Serializable {
    private String title;
    private int rating;
    private String genre;
    private int movieId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "UserRecommendations{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}
