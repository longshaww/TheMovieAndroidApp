package vn.edu.huflit.themovieapp1;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class DetailsMovieActivity extends AppCompatActivity implements Serializable, MovieAdapter.Listener {
    private static final String TAG ="SomeActivity";
    private TextView txtTitle,txtOverview,txtPopularity,txtVoteAverage,txtVoteCount;
    private ImageView imageView;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
        renderSimilarMovie();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("media_type")) {
            String id = getIntent().getStringExtra("id");
            MovieDetail movieDetail = api.getDetailMovie(id);
            String imageBg = movieDetail.backdrop_path;
            String image = movieDetail.poster_path;
            String name = movieDetail.title;
            Double popularity = movieDetail.popularity;
            Double vote_average = movieDetail.vote_average;
            Integer vote_count = movieDetail.vote_count;
            String overview = movieDetail.overview;

            setIncomingIntent(imageBg, image, name, popularity, vote_average, vote_count, overview);
        }
    }

    public void renderSimilarMovie(){
        String id = getIntent().getStringExtra("id");
        List<MovieItem> similarMovie = api.getSimilarMovie(id);
        RecyclerView listMovieView = findViewById(R.id.SimilarMovie);
        MovieAdapter movieAdapter = new MovieAdapter(this,similarMovie,this,false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    private void setIncomingIntent(String imageBg, String image, String name, Double popularity, Double vote_average, Integer vote_count,  String overview) {
        imageView = findViewById(R.id.imgBackgroundDetail);
        ImageAPI.getCorner(imageBg, 5, imageView);

        imageView = findViewById(R.id.imgDetail);
        ImageAPI.getCorner(image, 5, imageView);

        txtTitle = findViewById(R.id.txtTitleDetail);
        txtTitle.setText(name);

        txtPopularity = findViewById(R.id.txtNumPopularity);
        txtPopularity.setText(popularity.toString());


        txtVoteAverage = findViewById(R.id.txtNumVoteAverage);
        txtVoteAverage.setText(vote_average.toString());

        txtVoteCount = findViewById(R.id.txtNumVoteCount);
        txtVoteCount.setText(vote_count.toString());

        txtOverview = findViewById(R.id.txtOverviewDetail);
        txtOverview.setText(overview);

    }

    @Override
    public void onClick(MovieItem item) {

    }
}
