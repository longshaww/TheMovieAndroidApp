package vn.edu.huflit.themovieapp1.fragment;

import android.content.Intent;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.huflit.themovieapp1.DetailsMovieActivity;
import vn.edu.huflit.themovieapp1.Entertainment;
import vn.edu.huflit.themovieapp1.FavouriteAdapter;
import vn.edu.huflit.themovieapp1.FavouriteHelper;
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieAdapter;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.SearchAdapter;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;


public class MyListFragment extends Fragment implements FavouriteAdapter.Listener{
    private View mView;
    private Button refreshBtn;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fraqment_mylist, container, false);



        refreshBtn = mView.findViewById(R.id.refreshBtn);
        renderFavouriteList();
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
                renderFavouriteList();
            }
        });

        return mView;
    }


    public void renderFavouriteList() {
        FavouriteHelper favouriteHelper = new FavouriteHelper(getContext());
        List<Entertainment> list = favouriteHelper.getAllFavorites();
        RecyclerView listView = mView.findViewById(R.id.favouriteView);
        FavouriteAdapter adapter = new FavouriteAdapter(getContext(), list, this);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(Entertainment item) {

    }

}
