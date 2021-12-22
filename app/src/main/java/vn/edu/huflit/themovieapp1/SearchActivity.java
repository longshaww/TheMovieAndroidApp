package vn.edu.huflit.themovieapp1;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

public class SearchActivity extends AppCompatActivity implements Serializable, SearchAdapter.Listener{
    private RecyclerView rvSearchFilter, rvSearchData;
    private Toolbar toolbar;
    private SearchView searchView;
    private SearchAdapter searchAdapter, adapter;
    private RelativeLayout relativeLayout;

    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rvSearchFilter = findViewById(R.id.rvSearchFilter);
        rvSearchData = findViewById(R.id.rvSearchData);
        relativeLayout = findViewById(R.id.relativeLayout);

        renderSearchMovie();
        renderSearchTop();

        searchView = toolbar.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                if(newText.isEmpty()) {
                    setVisible(false);
                }else {
                    setVisible(true);
                }
                return false;
            }
        });
    }

    public void setVisible(boolean flag) {
        if(!flag){
            relativeLayout.setVisibility(View.VISIBLE);
            rvSearchFilter.setVisibility(View.GONE);
        }else {
            relativeLayout.setVisibility(View.GONE);
            rvSearchFilter.setVisibility(View.VISIBLE);
        }
    }

    public void renderSearchMovie() {
        List<Entertainment> list = new ArrayList<>();
        RecyclerView listView = findViewById(R.id.rvSearchFilter);
        searchAdapter = new SearchAdapter(getApplicationContext(), list, this);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(searchAdapter);
    }

    public void renderSearchTop() {
        List<Entertainment> listTop = api.getTrending();
        RecyclerView listView = findViewById(R.id.rvSearchData);
        adapter = new SearchAdapter(getApplicationContext(), listTop, this);
        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layout);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(Entertainment item) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
