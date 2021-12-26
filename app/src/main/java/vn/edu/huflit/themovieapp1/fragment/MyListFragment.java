package vn.edu.huflit.themovieapp1.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.huflit.themovieapp1.DetailsMovieActivity;
import vn.edu.huflit.themovieapp1.Entertainment;
import vn.edu.huflit.themovieapp1.Favourite;
import vn.edu.huflit.themovieapp1.FavouriteCrud;
import vn.edu.huflit.themovieapp1.FavouriteHelper;
import vn.edu.huflit.themovieapp1.ImageAPI;
import vn.edu.huflit.themovieapp1.MainActivity;
import vn.edu.huflit.themovieapp1.MovieAPI;
import vn.edu.huflit.themovieapp1.MovieAdapter;
import vn.edu.huflit.themovieapp1.MovieItem;
import vn.edu.huflit.themovieapp1.R;
import vn.edu.huflit.themovieapp1.SearchActivity;
import vn.edu.huflit.themovieapp1.SearchAdapter;
import vn.edu.huflit.themovieapp1.TrendingMovieAdapter;


public class MyListFragment extends Fragment {
    public static ListView LvFavourite;
    // dữ liệu để hiển thị lên listView
    public static ArrayList<Favourite> favourites;
    // Lớp để hỗ trợ truy vấn dữ liệu
    public static FavouriteCrud database;

    // Ô nhập liệu
//    private EditText favourite_txt, favourite_poster;
    private String title, poster;
    private static String id = "-1";

    Button btnSave;

    private View mView;
    MovieAPI api = new MovieAPI("743a82500e05c3b60a15c2d5030bc55f");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fraqment_mylist, container, false);
        LvFavourite = mView.findViewById(R.id.listFavourite);
//        favourite_txt = mView.findViewById(R.id.favourite_txt);
//        favourite_poster = mView.findViewById(R.id.favourite_poster);
        database = new FavouriteCrud(getContext());
        updateData();

        if (favourites != null) {
            LvFavourite.setAdapter(new FavouriteAdapter(getContext().getApplicationContext()));
        }

        // Bắt sự kiện khi click lên ListView
        LvFavourite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                txtName.setText(sinhViens.get(position).get_ten());
//                txtClass.setText(sinhViens.get(position).get_lop());
//                txtChampion.setText(sinhViens.get(position).get_tuongtu());
                MyListFragment.id = id;
            }
        });

        // call Add button
//        btnSave = mView.findViewById(R.id.addButton);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddData(view);
//                Toast.makeText(getContext().getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
//                if (favourites != null) {
//                    LvFavourite.setAdapter(new FavouriteAdapter(getContext().getApplicationContext()));
//                }
//            }
//        });
        return mView;
    }

    @SuppressLint("Range")
    public void updateData() {
        if (favourites == null) {
            favourites = new ArrayList<Favourite>();
        } else {
            favourites.removeAll(favourites);
        }
// Lấy dữ liệu, dùng Cursor nhận lại
        Cursor cursor = database.getAllData();
        if (cursor != null) {
            /*
             * Di chuyển đến từng dòng dữ liệu
             * thông qua phương thức moveToNext
             */
            while (cursor.moveToNext()) {
                Favourite favourite = new Favourite();
                /*
                 * Mỗi dòng dữ liệu chúng ra sẽ lấy
                 * theo cột và gán vào đối tượng
                 * SinhVien
                 */
                favourite.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavouriteHelper.ID_COLUMN))));
                favourite.setPoster_path(cursor.getString(cursor.getColumnIndex(FavouriteHelper.POSTER_PATH_COLUMN)));
                favourite.setTitle(cursor.getString(cursor.getColumnIndex(FavouriteHelper.TITLE_COLUMN)));
// thêm vào danh sách SinhVien
                favourites.add(favourite);
            }
        }
    }
//    public Favourite getFavourite(String id,String poster,String title){
//        Favourite favourite1 = new Favourite();
//        favourite1.setId(id);
//        favourite1.setPoster_path(poster);
//        favourite1.setTitle(title);
//    }


    public void AddData(View view ,String id , String poster , String title) {
        Favourite favourite1 = new Favourite();
        favourite1.setId(Integer.parseInt(id));
        favourite1.setPoster_path(poster);
        favourite1.setTitle(title);
        if (favourites == null) {
            favourites = new ArrayList<Favourite>();
        }
        if (favourite1 != null) {
            if (database.Add(favourite1) != -1) {
                favourites.add(favourite1);
                updateData();
                // Cập nhật lại danh sách
                LvFavourite.invalidateViews();
//                txtName.setText(null);
//                txtClass.setText(null);
//                txtChampion.setText(null);
//                id = -1;
            }
        }
    }


}
