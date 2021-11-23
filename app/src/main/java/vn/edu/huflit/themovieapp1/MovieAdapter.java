package vn.edu.huflit.themovieapp1;

import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<MovieItem> list;
    private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieItem movieList = list.get(position);
//        holder.textPopularMovie.setText(movieList.title);
        ImageAPI.getCorner(movieList.poster_path, 3, holder.MovieImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(movieList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView MovieImage;
//        private TextView textPopularMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MovieImage = itemView.findViewById(R.id.trendingImage);
//            textPopularMovie = itemView.findViewById(R.id.textTrending);
        }
    }

    public MovieAdapter(List<MovieItem> list, MovieAdapter.Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface Listener {
        void onClick(MovieItem item);
    }
}

