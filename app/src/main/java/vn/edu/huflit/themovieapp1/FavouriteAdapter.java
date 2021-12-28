package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {
    private List<Entertainment> list;
    private Listener listener;
    private Context context;
    private FavouriteHelper helper;

    public FavouriteAdapter(Context context, List<Entertainment> list, Listener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
        helper = new FavouriteHelper(context);
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_layout, parent, false);
        return new FavouriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entertainment entertainment = list.get(position);
        if (entertainment.media_type.equals("movie")) {
            MovieItem movie = (MovieItem) entertainment;
            holder.txtTitleItem.setText(movie.title);
        } else {
            TVItem tv = (TVItem) entertainment;
            holder.txtTitleItem.setText(tv.name);
        }
        ImageAPI.getCorner(entertainment.poster_path, 3, holder.trending);

        holder.trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(entertainment);
                if (entertainment.media_type.equals("movie")) {
                    MovieItem movie = (MovieItem) entertainment;
                    Intent intent = new Intent(context, DetailsMovieActivity.class);
                    intent.putExtra("id", movie.id);
                    intent.putExtra("media_type", movie.media_type);
                    context.startActivity(intent);
                } else {
                    TVItem tv = (TVItem) entertainment;
                    Intent intent = new Intent(context, DetailsTVActivity.class);
                    intent.putExtra("id", tv.id);
                    intent.putExtra("media_type", tv.media_type);
                    context.startActivity(intent);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    helper.deleteRow(entertainment.id);
                    list = helper.getAllFavorites();
                    notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView trending;
        private TextView txtTitleItem;
        private ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trending = itemView.findViewById(R.id.favourite_poster);
            txtTitleItem = itemView.findViewById(R.id.favourite_txt);
            delete = itemView.findViewById(R.id.deleteFav);

        }
    }

    public interface Listener {
        void onClick(Entertainment item);
    }
}
