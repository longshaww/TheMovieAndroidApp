package vn.edu.huflit.themovieapp1;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class SearchActivity extends AppCompatActivity implements Serializable, SearchAdapter.Listener {
    private RecyclerView rvSearchFilter;
    private Toolbar toolbar;
    private SearchView searchView;

    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = toolbar.findViewById(R.id.searchView);

        rvSearchFilter = findViewById(R.id.rvSearchFilter);

        renderSearchTrendingMovie();
    }

    public void renderSearchTrendingMovie() {
        List<Entertainment> list = api.getTrending();
        RecyclerView listView = findViewById(R.id.rvSearchFilter);
        SearchAdapter adapter = new SearchAdapter(getApplicationContext(), list, this);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(Entertainment item) {

    }
}
