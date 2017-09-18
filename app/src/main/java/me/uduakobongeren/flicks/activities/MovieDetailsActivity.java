package me.uduakobongeren.flicks.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.uduakobongeren.flicks.R;

/**
 * @author Uduak Obong-Eren
 * @since 9/17/17.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String movieTitle = extras.getString("title");
            String movieOverview = extras.getString("overview");
            String imageURL = extras.getString("image_url");
            String date = extras.getString("date");

            TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
            tvTitle.setText(movieTitle);

            TextView tvOverview = (TextView) findViewById(R.id.tvOverview);
            tvOverview.setText(movieOverview);

            TextView tvReleaseDate = (TextView) findViewById(R.id.tvDate);
            tvReleaseDate.setText(date);

            ImageView ivPosterImage = (ImageView) findViewById(R.id.ivPoster);
            Picasso.with(this).load(imageURL).into(ivPosterImage);

        }

    }
}
