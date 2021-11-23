package vn.edu.huflit.themovieapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import vn.edu.huflit.themovieapp1.R;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // APIKEY is a key create in https://www.themoviedb.org/
//        MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");
//        System.out.println(api.isAccess);
//        List<Entertainment> list = api.getTrending();
//        ListData<Entertainment> dataTrending = api.trending;
//        List<MovieItem> listPopularMovie = api.getPopularMovie();

        viewPager2 = findViewById(R.id.viewpager2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.setUserInputEnabled(false);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_home) {
                    viewPager2.setCurrentItem(0);
                } else if (id == R.id.navigation_tvshow) {
                    viewPager2.setCurrentItem(1);
                } else if (id == R.id.navigation_search) {
                    viewPager2.setCurrentItem(2);
                } else if (id == R.id.navigation_account) {
                    viewPager2.setCurrentItem(3);
                } else if (id == R.id.navigation_more) {
                    viewPager2.setCurrentItem(4);
                }
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_tvshow).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_search).setChecked(true);
                        break;

                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_account).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_more).setChecked(true);
                        break;
                }
            }
        });
    }

}