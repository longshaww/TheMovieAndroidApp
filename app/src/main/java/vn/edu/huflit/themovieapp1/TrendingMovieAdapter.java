package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrendingMovieAdapter extends RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder> {
    private List<Entertainment> list;
    private Listener listener;
    private Context context;
    private Boolean bool;

    public TrendingMovieAdapter(Context context, List<Entertainment> list, Listener listener, Boolean bool) {
        this.list = list;
        this.listener = listener;
        this.context = context;
        this.bool = bool;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (this.bool == true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backdrop_layout, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_image, parent, false);
            return new ViewHolder(view);
        }
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
        if (this.bool == true) {
            ImageAPI.getCorner(entertainment.backdrop_path, 5, holder.trending);
        } else {
            ImageAPI.getCorner(entertainment.poster_path, 3, holder.trending);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView trending;
        private TextView txtTitleItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trending = itemView.findViewById(R.id.trendingImage);
            txtTitleItem = itemView.findViewById(R.id.txtTitleItem);
        }
    }

    public interface Listener {
        void onClick(Entertainment item);
    }
}
