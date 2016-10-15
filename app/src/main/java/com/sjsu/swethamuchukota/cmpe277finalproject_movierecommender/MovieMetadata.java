package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import java.io.Serializable;

/**
 * Created by rwatsh on 10/13/16.
 */

public class MovieMetadata implements Serializable {
    private int id; // same as imdbid in links collection
    private String title;
    private String director;
    private String writer;
    private String cined;
    private String prod;
    private String type;
    private String year;
    private String countries;
    private String dur; // duration of the movie
    private String mpaa;
    private String rate; // out of 10
    private String cov; // cover image of the movie - generally jpg format.
    private String gen; // type of movie
    private String plot;
    private String plotout;
    private String cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCined() {
        return cined;
    }

    public void setCined(String cined) {
        this.cined = cined;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getMpaa() {
        return mpaa;
    }

    public void setMpaa(String mpaa) {
        this.mpaa = mpaa;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCov() {
        return cov;
    }

    public void setCov(String cov) {
        this.cov = cov;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPlotout() {
        return plotout;
    }

    public void setPlotout(String plotout) {
        this.plotout = plotout;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Override
    public String toString() {
        return "MovieMetadata{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", cined='" + cined + '\'' +
                ", prod='" + prod + '\'' +
                ", type='" + type + '\'' +
                ", year='" + year + '\'' +
                ", countries='" + countries + '\'' +
                ", dur='" + dur + '\'' +
                ", mpaa='" + mpaa + '\'' +
                ", rate='" + rate + '\'' +
                ", cov='" + cov + '\'' +
                ", gen='" + gen + '\'' +
                ", plot='" + plot + '\'' +
                ", plotout='" + plotout + '\'' +
                ", cast='" + cast + '\'' +
                '}';
    }
}
