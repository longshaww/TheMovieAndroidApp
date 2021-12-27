package vn.edu.huflit.themovieapp1;

import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import java.util.ArrayList;
import java.util.List;

public class DetailsTVActivity extends AppCompatActivity implements TVAdapter.Listener, CastAdapter.Listener, CrewAdapter.Listener {
    private static final String TAG = "SomeActivity";
    private TextView txtTitle, txtOverview, txtPopularity, txtVoteAverage, txtVoteCount;
    private ImageView imageView;
    private Toolbar toolbar;
    private Button addButton;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
        renderSimilarTV();
        renderCastTV();
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

            TVDetail tvDetail = api.getDetailTV(id);
            String imageBg = tvDetail.backdrop_path;
            String image = tvDetail.poster_path;
            String name = tvDetail.name;
            Double popularity = tvDetail.popularity;
            Double vote_average = tvDetail.vote_average;
            Integer vote_count = tvDetail.vote_count;
            Integer number_of_session = tvDetail.number_of_session;
            Integer number_of_episodes = tvDetail.number_of_episodes;
            String overview = tvDetail.overview;

            setIncomingIntent(imageBg, image, name, popularity, vote_average, vote_count, number_of_session, number_of_episodes, overview,id);
    }

    public void renderSimilarTV(){
        String id = getIntent().getStringExtra("id");
        List<TVItem> listTV = api.getSimilarTV(id);
        RecyclerView listTVView = findViewById(R.id.SimilarMovie);
        TVAdapter tvAdapter = new TVAdapter(this, listTV, this);
        LinearLayoutManager layoutTV = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listTVView.setLayoutManager(layoutTV);
        listTVView.setAdapter(tvAdapter);
    }

    public void renderCastTV(){
        String id = getIntent().getStringExtra("id");
        Object[] creditTV = api.getCredit(id, false);
        List<Cast> castTV = (List<Cast>) creditTV[0];
        RecyclerView listMovieView = findViewById(R.id.rvCastMovie);
        CastAdapter castAdapter = new CastAdapter(this, castTV,this,false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(castAdapter);
    }

    public void renderCrewMovie(){
        String id = getIntent().getStringExtra("id");
        Object[] creditMovie = api.getCredit(id, false);
        List<Crew> crewMovie = (List<Crew>) creditMovie[1];
        RecyclerView listMovieView = findViewById(R.id.rvCrewMovie);
        CrewAdapter crewAdapter = new CrewAdapter(this,crewMovie, this,false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(crewAdapter);
    }

    private void setIncomingIntent(String imageBg, String image, String name, Double popularity, Double vote_average, Integer vote_count, Integer number_of_session, Integer number_of_episodes, String overview,String id) {
        String type = "tv";
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

        txtPopularity = findViewById(R.id.txtNumSession);
        txtPopularity.setText(number_of_session.toString());

        txtPopularity = findViewById(R.id.txtNumEpisodes);
        txtPopularity.setText(number_of_episodes.toString());

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
    public void onClick(TVItem item) {

    }

    @Override
    public void onClick(Cast item) {

    }

    @Override
    public void onClick(Crew item) {

    }
}
