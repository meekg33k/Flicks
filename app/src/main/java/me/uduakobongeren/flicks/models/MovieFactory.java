package me.uduakobongeren.flicks.models;


import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * @author Uduak Obong-Eren
 * @since 9/13/17.
 *
 * Creates a Movie Model and keeps a list of all models
 */

public class MovieFactory {

    private static ArrayList<Movie> movies = new ArrayList<>();

    @Nullable
    public static Movie createMovie(JSONObject movieObject){
        try {
            Movie movie = new  Movie(movieObject);
            movies.add(movie);
            return movie;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray moviesArray){
        for (int i = 0; i < moviesArray.length(); i++){
            try {
                createMovie(moviesArray.getJSONObject(i));
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        return movies;
    }
}
