package vn.edu.huflit.themovieapp1;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class DetailsMovieActivity extends AppCompatActivity implements Serializable, MovieAdapter.Listener, CastAdapter.Listener, CrewAdapter.Listener {
    private static final String TAG = "SomeActivity";
    private TextView txtTitle, txtOverview, txtPopularity, txtVoteAverage, txtVoteCount;
    private Button addButton;
    private ImageView imageView;
    private Toolbar toolbar;

    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
        renderSimilarMovie();
        renderCastMovie();
        renderCrewMovie();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getIncomingIntent() {
            String id = getIntent().getStringExtra("id");
            MovieDetail movieDetail = api.getDetailMovie(id);

            String imageBg = movieDetail.backdrop_path;
            String image = movieDetail.poster_path;
            String name = movieDetail.title;
            Double popularity = movieDetail.popularity;
            Double vote_average = movieDetail.vote_average;
            Integer vote_count = movieDetail.vote_count;
            String overview = movieDetail.overview;

            setIncomingIntent(imageBg, image, name, popularity, vote_average, vote_count, overview, id);

    }


    public void renderSimilarMovie() {
        String id = getIntent().getStringExtra("id");
        List<MovieItem> similarMovie = api.getSimilarMovie(id);
        RecyclerView listMovieView = findViewById(R.id.SimilarMovie);
        MovieAdapter movieAdapter = new MovieAdapter(this, similarMovie, this, false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    public void renderCastMovie() {
        String id = getIntent().getStringExtra("id");
        Object[] creditMovie = api.getCredit(id, true);
        List<Cast> castMovie = (List<Cast>) creditMovie[0];
        RecyclerView listMovieView = findViewById(R.id.rvCastMovie);
        CastAdapter castAdapter = new CastAdapter(this, castMovie, this, false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(castAdapter);
    }

    public void renderCrewMovie() {
        String id = getIntent().getStringExtra("id");
        Object[] creditMovie = api.getCredit(id, true);
        List<Crew> crewMovie = (List<Crew>) creditMovie[1];
        RecyclerView listMovieView = findViewById(R.id.rvCrewMovie);
        CrewAdapter crewAdapter = new CrewAdapter(this, crewMovie, this, false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(crewAdapter);
    }


    private void setIncomingIntent(String imageBg, String image, String name, Double popularity, Double vote_average, Integer vote_count, String overview, String id) {
        String type = "movie";

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

        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavouriteHelper favouriteHelper = new FavouriteHelper(getBaseContext());
                favouriteHelper.insertFavorites(id, name, type , overview, image, vote_average);
            }
        });
    }

    @Override
    public void onClick(MovieItem item) {

    }

    @Override
    public void onClick(Cast item) {

    }

    @Override
    public void onClick(Crew item) {

    }
}
