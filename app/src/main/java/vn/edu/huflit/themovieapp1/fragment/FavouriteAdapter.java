package vn.edu.huflit.themovieapp1.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.huflit.themovieapp1.MainActivity;
import vn.edu.huflit.themovieapp1.R;

public class FavouriteAdapter extends BaseAdapter {

    LayoutInflater inflater;
    TextView title, poster;
    Context context;

    public FavouriteAdapter(Context context) {
        /*
         * Khởi tạo lớp LayoutInflater
         * thông qua Context
         */
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return MyListFragment.favourites.size();
    }

    @Override
    public Object getItem(int position) {
        return MyListFragment.favourites.get(position);
    }

    @Override
    public long getItemId(int position) {
        return MyListFragment.favourites.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.favourite_layout, null);
        title = view.findViewById(R.id.favourite_txt);
        title.setText(MyListFragment.favourites.get(position).getTitle());

        poster = view.findViewById(R.id.favourite_poster);
        poster.setText(MyListFragment.favourites.get(position).getPoster_path());

        ((ImageView) view.findViewById(R.id.detele)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
                MyListFragment.database.Delete(MyListFragment.favourites.get(position));
                MyListFragment.favourites.remove(position);
                notifyDataSetChanged();

            }
        });
        return view;
    }
}
