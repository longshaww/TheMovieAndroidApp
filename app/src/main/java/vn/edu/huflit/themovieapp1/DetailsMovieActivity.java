package vn.edu.huflit.themovieapp1;

import android.graphics.Movie;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class DetailsMovieActivity extends AppCompatActivity implements Serializable{
    private static final String TAG ="SomeActivity";
    private TextView txtTitle,txtOverview,txtPopularity,txtVoteAverage,txtVoteCount;
    private ImageView imageView;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");
    private String title;
    private String image;
    private String overview;
    private Double popularity;
    private Double vote_average;
    private Integer vote_count;


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
            String media_type = getIntent().getStringExtra("media_type");
//            APIKey api = (APIKey) getIntent().getSerializableExtra("APIKey");
//            backdrop_path: String;
//            genres: Genres[];
//            homepage: String;
//            id: int;
//            original_language: String;
//            overview: String;
//            popularity: double;
//            poster_path: String;
//            production_companies: Company[];
//            production_countries: Country[];
//            spoken_languages: Language[];
//            status: String;
//            tagline: String;
//            vote_average: double;
//            vote_count: int;

            MovieDetail movieDetail = api.getDetailMovie(id);
            String title = movieDetail.title;
            String imageBg = movieDetail.backdrop_path;
            String image = movieDetail.poster_path;
            String overview = movieDetail.overview;
            Double popularity = movieDetail.popularity;
            Double vote_average = movieDetail.vote_average;
            Integer vote_count = movieDetail.vote_count;
            setIncomingIntent(title, imageBg, image,overview,popularity,vote_average,vote_count);
        }
    }

    private void setIncomingIntent(String title, String imageBg, String image,String overview,Double popularity,Double vote_average,Integer vote_count){
        imageView = findViewById(R.id.imgBackroundDetail);
        ImageAPI.getCorner(imageBg, 5, imageView);

        imageView = findViewById(R.id.imgDetail);
        ImageAPI.getCorner(image, 5, imageView);

        txtTitle = findViewById(R.id.txtTitleDetail);
        txtTitle.setText(title);

        txtVoteCount = findViewById(R.id.txtVoteCountDetail);
        txtVoteCount.setText(vote_count.toString());

        txtOverview = findViewById(R.id.txtOverviewDetail);
        txtOverview.setText(overview);

    }
}
