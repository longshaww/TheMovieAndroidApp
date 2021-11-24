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

public class DetailsActivity extends AppCompatActivity implements Serializable{
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
        setContentView(R.layout.details);
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

            MovieDetail movie = api.getDetailMovie(id);
            String title = movie.title;
            String image = movie.backdrop_path;
            String overview = movie.overview;
            Double popularity = movie.popularity;
            Double vote_average = movie.vote_average;
            Integer vote_count = movie.vote_count;
            setIncomingIntent(title,image,overview,popularity,vote_average,vote_count);
        }
    }

    private void setIncomingIntent(String title,String image,String overview,Double popularity,Double vote_average,Integer vote_count){
        txtTitle = findViewById(R.id.DetailTitle);
        txtTitle.setText(title);

//        txtOverview,txtPopularity,txtVoteAverage,txtVoteCount
        txtOverview = findViewById(R.id.DetailOverview);
        txtOverview.setText(overview);

        imageView = findViewById(R.id.DetailImage);
        ImageAPI.getCorner(image,5,imageView);

        txtPopularity = findViewById(R.id.DetailPopularity);
        txtPopularity.setText(popularity.toString());

        txtVoteAverage = findViewById(R.id.DetailVoteAverage);
        txtVoteAverage.setText(vote_average.toString());

        txtVoteCount = findViewById(R.id.DetailVoteCount);
        txtVoteCount.setText(vote_count.toString());

    }
}
