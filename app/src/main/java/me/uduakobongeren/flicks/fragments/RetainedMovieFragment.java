package me.uduakobongeren.flicks.fragments;

import android.app.Fragment;
import android.os.Bundle;

import java.util.ArrayList;

import me.uduakobongeren.flicks.models.Movie;

/**
 * @author Uduak Obong-Eren
 * @since 9/16/17.
 */

public class RetainedMovieFragment extends Fragment {

    private ArrayList<Movie> data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(ArrayList<Movie>  data) {
        this.data = data;
    }

    public ArrayList<Movie> getData() {
        return data;
    }
}
