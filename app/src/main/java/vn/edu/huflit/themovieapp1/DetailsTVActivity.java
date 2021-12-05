package vn.edu.huflit.themovieapp1;

import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class DetailsTVActivity extends AppCompatActivity implements Serializable {
    private static final String TAG = "SomeActivity";
    private TextView txtTitle, txtOverview, txtPopularity, txtVoteAverage, txtVoteCount;
    private ImageView imageView;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: started");
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("media_type")) {
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

            setIncomingIntent(imageBg, image, name, popularity, vote_average, vote_count, number_of_session, number_of_episodes, overview);
        }
    }

    private void setIncomingIntent(String imageBg, String image, String name, Double popularity, Double vote_average, Integer vote_count, Integer number_of_session, Integer number_of_episodes, String overview) {
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

    }
}
