package me.uduakobongeren.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.uduakobongeren.flicks.R;
import me.uduakobongeren.flicks.models.Movie;
import me.uduakobongeren.flicks.utils.RoundedCornerImageTransform;

/**
 * @author Uduak Obong-Eren
 * @since 9/15/17.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context appContext;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        appContext = context;
    }

    private static class ViewModel {
        ImageView movieImageView;
        TextView movieTitle;
        TextView movieDesc;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewModel viewModel;
        Movie movie = getItem(position);
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (convertView == null) {
            viewModel = new ViewModel();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);

            viewModel.movieTitle = convertView.findViewById(R.id.movieTitle);
            viewModel.movieDesc = convertView.findViewById(R.id.movieDescription);
            viewModel.movieImageView = convertView.findViewById(R.id.movieImage);

            convertView.setTag(viewModel);
        }
        else {
            viewModel = (ViewModel) convertView.getTag();
        }

        if (movie != null){
            viewModel.movieTitle.setText(movie.getTitle());
            viewModel.movieDesc.setText(movie.getOverview());

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .placeholder(R.drawable.movie_img).transform(new RoundedCornerImageTransform(50, 0))
                        .into(viewModel.movieImageView);

            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Picasso.with(getContext()).load(movie.getBackdropPath())
                        .placeholder(R.drawable.movie_img).transform(new RoundedCornerImageTransform(50, 0))
                        .fit().centerCrop()
                        .into(viewModel.movieImageView);
            }
        }
        return convertView;
    }

}
