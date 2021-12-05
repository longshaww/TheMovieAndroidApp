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
import android.widget.Toolbar;

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
import vn.edu.huflit.themovieapp1.SearchActivity;
import vn.edu.huflit.themovieapp1.SearchAdapter;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;


public class MyListFragment extends Fragment implements SearchAdapter.Listener {
    private View mView;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fraqment_mylist, container, false);
        api.getTrending();
        renderTrendingMovie();
        return mView;
    }
    public void renderTrendingMovie() {
        List<Entertainment> list = api.getTrending();
        RecyclerView listView = mView.findViewById(R.id.rvMyList);
        SearchAdapter adapter = new SearchAdapter(getContext(), list, this);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(Entertainment item) {
        Toast.makeText(getContext(), item.id, Toast.LENGTH_SHORT).show();
    }

}
