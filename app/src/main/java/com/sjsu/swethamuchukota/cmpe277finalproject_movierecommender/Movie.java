package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

/**
 * Created by Ashutosh on 10/12/2016.
 */

public class Movie {
    private String movieName;
    private String genre;
    private float rating;
    public Movie() {
    }

    public Movie(String name, String genre, float rating) {
        this.movieName = name;
        this.genre = genre;
        this.rating = rating;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
