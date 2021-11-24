package vn.edu.huflit.themovieapp1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.themovieapp1.Entertainment;
import vn.edu.huflit.themovieapp1.ImageAPI;
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieAdapter;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;


public class HomeFragment extends Fragment implements TrendingMovieAdapter.Listener, MovieAdapter.Listener {
    private View mView;
    private ImageView trendingSingleImage;
    private TextView trendingSingleTitle;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        api.getTrending();
        api.getTrending();
        api.getPopularMovie();
        renderTrendingMovie();
        renderPopularMovie();
        renderTopRatedMovie();
        renderPopularMovieSingleComponent();
        return mView;
    }
    public void renderPopularMovieSingleComponent(){
        List<MovieItem> list = api.getPopularMovie();
        MovieItem movie = list.get(0);
        trendingSingleImage = mView.findViewById(R.id.TrendingSingleImage);
        trendingSingleTitle = mView.findViewById(R.id.TrendingSingleTitle);
        ImageAPI.getCorner(movie.backdrop_path, 5, trendingSingleImage);
        trendingSingleTitle.setText(movie.title);
    }
    public void renderTrendingMovie() {
        List<Entertainment> list = api.getTrending();
        RecyclerView listView = mView.findViewById(R.id.TrendingList);
        TrendingMovieAdapter adapter = new TrendingMovieAdapter(list, this);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }
    public void renderPopularMovie(){
        List<MovieItem> listMovie = api.getPopularMovie();
        RecyclerView listMovieView = mView.findViewById(R.id.PopularTV);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(),listMovie,this);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    public void renderTopRatedMovie(){
        List<MovieItem> listMovie = api.getTopRatedMovie();
        RecyclerView listMovieView = mView.findViewById(R.id.TopRatedMovie);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(),listMovie,this);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    @Override
    public void onClick(Entertainment item) {
        Toast.makeText(getContext(), item.id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(MovieItem item) {
        Toast.makeText(getContext(), item.title, Toast.LENGTH_SHORT).show();
    }

}
