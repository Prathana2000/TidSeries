package org.classapp.tidseries1;

public class Movie {

    private String title, poster, overview, trailer;
    private Double rating;

    public Movie(String title, String poster, String overview, String trailer, Double rating) {
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.trailer = trailer;
        this.rating = rating;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPoster() {
        return this.poster;
    }

    public String getOverview() {
        return this.overview;
    }

    public String getTrailer() {
        return this.trailer;
    }

    public Double getRating() {
        return this.rating;
    }
}
