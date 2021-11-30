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
            String name = tvDetail.name;
            String imageBg = tvDetail.backdrop_path;
            String image = tvDetail.poster_path;
            String overview = tvDetail.overview;
            Double popularity = tvDetail.popularity;
            Double vote_average = tvDetail.vote_average;
            Integer vote_count = tvDetail.vote_count;

            setIncomingIntent(name, imageBg, image, overview, popularity, vote_average, vote_count);
        }
    }

    private void setIncomingIntent(String name, String imageBg, String image, String overview, Double popularity, Double vote_average, Integer vote_count) {
        imageView = findViewById(R.id.imgBackroundDetail);
        ImageAPI.getCorner(imageBg, 5, imageView);

        imageView = findViewById(R.id.imgDetail);
        ImageAPI.getCorner(image, 5, imageView);

        txtTitle = findViewById(R.id.txtTitleDetail);
        txtTitle.setText(name);

        txtVoteCount = findViewById(R.id.txtVoteCountDetail);
        txtVoteCount.setText(vote_count.toString());

        txtOverview = findViewById(R.id.txtOverviewDetail);
        txtOverview.setText(overview);

//        txtPopularity = findViewById(R.id.txtPopularityDetail);
//        txtPopularity.setText(popularity.toString());

//        txtVoteAverage = findViewById(R.id.txtVoteAverageDetail);
//        txtVoteAverage.setText(vote_average.toString());
    }
}
