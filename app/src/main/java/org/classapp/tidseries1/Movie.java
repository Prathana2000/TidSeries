package org.classapp.tidseries1;

public class Movie {

    private String title , poster , overview;
    private Double rating;

    public Movie(String title, String poster , String overview , Double rating){
        this.title = title;
        this.poster = poster;
        this.overview = overview;
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

    public Double getRating() {
        return this.rating;
    }
}
