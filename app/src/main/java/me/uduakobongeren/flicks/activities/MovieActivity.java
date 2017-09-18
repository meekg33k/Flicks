package me.uduakobongeren.flicks.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import me.uduakobongeren.flicks.R;
import me.uduakobongeren.flicks.adapters.MovieAdapter;
import me.uduakobongeren.flicks.fragments.RetainedMovieFragment;
import me.uduakobongeren.flicks.models.Movie;
import me.uduakobongeren.flicks.models.MovieFactory;

public class MovieActivity extends AppCompatActivity {

    private ArrayList<Movie> movies;
    private ListView lvMovies;
    private MovieAdapter movieAdapter;
    private static final String RETAINED_MOVIE_FRAG = "RetainedMovieFrag";
    private RetainedMovieFragment mRetainedFragment;
    public static final int REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        final FragmentManager fm = getFragmentManager();
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        mRetainedFragment = (RetainedMovieFragment) fm.findFragmentByTag(RETAINED_MOVIE_FRAG);

        if (mRetainedFragment == null) {
            mRetainedFragment = new RetainedMovieFragment();
            fm.beginTransaction().add(mRetainedFragment, RETAINED_MOVIE_FRAG).commit();
            fetchMovieList();
        }
        else {
            movies = mRetainedFragment.getData();
            lvMovies.setAdapter(new MovieAdapter(getApplicationContext(), movies));
        }
    }


    public void fetchMovieList(){

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movieResults = response.getJSONArray("results");

                    movies = MovieFactory.fromJSONArray(movieResults);
                    mRetainedFragment.setData(movies);

                    movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                    lvMovies.setAdapter(movieAdapter);

                    setUpListener();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable e) {
                super.onFailure(statusCode, headers, errorResponse, e);
            }
        });
    }


    private void setUpListener(){
        lvMovies.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View item, int itemPos, long itemId) {
                    Movie movie = movies.get(itemPos);

                    Intent movieDetailIntent = new Intent(getApplicationContext(), MovieDetailsActivity.class);
                    movieDetailIntent.putExtra("title", movie.getTitle());
                    movieDetailIntent.putExtra("image_url", movie.getPosterPath());
                    movieDetailIntent.putExtra("overview", movie.getOverview());
                    movieDetailIntent.putExtra("rating", movie.getVoteRating());
                    movieDetailIntent.putExtra("date", movie.getReleaseDate());

                    startActivity(movieDetailIntent);
                }
            }
        );
    }
}
