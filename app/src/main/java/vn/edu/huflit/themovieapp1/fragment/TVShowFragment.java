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
import vn.edu.huflit.themovieapp1.ImageAPI;
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.SearchActivity;
import vn.edu.huflit.themovieapp1.TVAdapter;
import vn.edu.huflit.themovieapp1.TVItem;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;

public class TVShowFragment extends Fragment implements TVAdapter.Listener, TrendingMovieAdapter.Listener {
    private View mView;
    private ImageView trendingSingleImage,secondSingleImage , thirdSingleImage;
    private TextView trendingSingleTitle,secondSingleTitle,thirdSingleTitle;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tvshow,container,false);
        renderPopularTV();
        renderTopRatedTV();
        renderTrendingList();
        return mView;
    }
    public void renderTrendingList(){
        List<Entertainment> list = api.getTrending();
        RecyclerView listView = mView.findViewById(R.id.Trending);
        TrendingMovieAdapter adapter = new TrendingMovieAdapter(getContext(), list, this,true);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    public void renderPopularTV(){
        List<TVItem> listTV = api.getPopularTV();
        RecyclerView listTVView = mView.findViewById(R.id.PopularTV);
        TVAdapter tvAdapter = new TVAdapter(getContext(), listTV, this);
        LinearLayoutManager layoutTV = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listTVView.setLayoutManager(layoutTV);
        listTVView.setAdapter(tvAdapter);
    }
    public void renderTopRatedTV(){
        List<TVItem> listTV = api.getTopRatedTV();
        RecyclerView listTVView = mView.findViewById(R.id.TopRatedTV);
        TVAdapter tvAdapter = new TVAdapter(getContext(), listTV, this);
        LinearLayoutManager layoutTV = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listTVView.setLayoutManager(layoutTV);
        listTVView.setAdapter(tvAdapter);
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
    public void onClick(TVItem item) {
        Toast.makeText(getContext(), item.id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Entertainment item) {

    }
}
