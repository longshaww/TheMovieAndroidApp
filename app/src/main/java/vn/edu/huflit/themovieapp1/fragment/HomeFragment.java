package vn.edu.huflit.themovieapp1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieAdapter;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.SearchActivity;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;


public class HomeFragment extends Fragment implements TrendingMovieAdapter.Listener, MovieAdapter.Listener {
    private View mView;
    private ImageView trendingSingleImage;
    private TextView trendingSingleTitle;

    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

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
        renderUpComingMovie();

//        txtSeeAllTrending = mView.findViewById(R.id.txtSeeAllTrending);
//        txtSeeAllTrending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplication(), SeeAllActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        txtSeeAllPopular = mView.findViewById(R.id.txtSeeAllPopular);
//        txtSeeAllPopular.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity().getApplication(), SeeAllActivity.class);
//                startActivity(intent);
//            }
//        });

        return mView;
    }

    public void renderUpComingMovie() {
        List<MovieItem> listMovie = api.getUpcoming();
        RecyclerView listMovieView = mView.findViewById(R.id.UpComing);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), listMovie, this, true);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    public void renderTrendingMovie() {
        List<Entertainment> list = api.getTrending();
        RecyclerView listView = mView.findViewById(R.id.TrendingList);
        TrendingMovieAdapter adapter = new TrendingMovieAdapter(getContext(), list, this, false);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    public void renderPopularMovie() {
        List<MovieItem> listMovie = api.getPopularMovie();
        RecyclerView listMovieView = mView.findViewById(R.id.PopularTV);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), listMovie, this, false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    public void renderTopRatedMovie() {
        List<MovieItem> listMovie = api.getTopRatedMovie();
        RecyclerView listMovieView = mView.findViewById(R.id.TopRatedMovie);
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), listMovie, this, false);
        LinearLayoutManager layoutMovie = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listMovieView.setLayoutManager(layoutMovie);
        listMovieView.setAdapter(movieAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSearch) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Entertainment item) {
        Toast.makeText(getContext(), item.id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(MovieItem item) {
        Toast.makeText(getContext(), item.id, Toast.LENGTH_SHORT).show();
    }

}
