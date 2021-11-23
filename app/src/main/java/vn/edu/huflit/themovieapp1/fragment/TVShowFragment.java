package vn.edu.huflit.themovieapp1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.huflit.themovieapp1.ImageAPI;
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.TVAdapter;
import vn.edu.huflit.themovieapp1.TVItem;

public class TVShowFragment extends Fragment implements TVAdapter.Listener {
    private View mView;
    private ImageView trendingSingleImage,secondSingleImage , thirdSingleImage;
    private TextView trendingSingleTitle,secondSingleTitle,thirdSingleTitle;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tvshow,container,false);
        renderPopularTV();
        renderTopRatedTV();
        renderPopularTVFirstComponent();
        renderPopularTVSecondComponent();
        renderPopularTVThirdComponent();
        return mView;
    }
    public void renderPopularTVFirstComponent(){
        List<TVItem> list = api.getPopularTV();
        TVItem tv = list.get(0);
        trendingSingleImage = mView.findViewById(R.id.TrendingSingleImage);
        trendingSingleTitle = mView.findViewById(R.id.TrendingSingleTitle);
        ImageAPI.getCorner(tv.poster_path, 4, trendingSingleImage);
        trendingSingleTitle.setText(tv.name);
    }
    public void renderPopularTVSecondComponent(){
        List<TVItem> list = api.getPopularTV();
        TVItem tv = list.get(10);
        secondSingleImage = mView.findViewById(R.id.SecondSingleImage);
        secondSingleTitle = mView.findViewById(R.id.SecondSingleTitle);
        ImageAPI.getCorner(tv.poster_path, 4, secondSingleImage);
        secondSingleTitle.setText(tv.name);
    }
    public void renderPopularTVThirdComponent(){
        List<TVItem> list = api.getPopularTV();
        TVItem tv = list.get(20);
        thirdSingleImage = mView.findViewById(R.id.ThirdSingleImage);
        thirdSingleTitle = mView.findViewById(R.id.ThirdSingleTitle);
        ImageAPI.getCorner(tv.poster_path, 4, thirdSingleImage);
        thirdSingleTitle.setText(tv.name + " is happening");
    }

    public void renderPopularTV(){
        List<TVItem> listTV = api.getPopularTV();
        RecyclerView listTVView = mView.findViewById(R.id.PopularTV);
        TVAdapter tvAdapter = new TVAdapter(listTV, this);
        LinearLayoutManager layoutTV = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listTVView.setLayoutManager(layoutTV);
        listTVView.setAdapter(tvAdapter);
    }
    public void renderTopRatedTV(){
        List<TVItem> listTV = api.getTopRatedTV();
        RecyclerView listTVView = mView.findViewById(R.id.TopRatedTV);
        TVAdapter tvAdapter = new TVAdapter(listTV, this);
        LinearLayoutManager layoutTV = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        listTVView.setLayoutManager(layoutTV);
        listTVView.setAdapter(tvAdapter);
    }

    @Override
    public void onClick(TVItem item) {

    }
}
