package me.uduakobongeren.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Uduak Obong-Eren
 * @since 9/15/17.
 *
 * Movie POJO
 */

public class Movie {

    private String backdropPath;
    private String overview;
    private String posterPath;
    private String releaseDate;
    private String title;
    private String voteRating;


    public Movie(JSONObject movieObject) throws JSONException{
        this.backdropPath = movieObject.getString("backdrop_path");
        this.overview = movieObject.getString("overview");
        this.posterPath = movieObject.getString("poster_path");
        this.releaseDate = movieObject.getString("release_date");
        this.title = movieObject.getString("original_title");
        this.voteRating = movieObject.getString("vote_average");
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getVoteRating() {
        return voteRating;
    }


}
