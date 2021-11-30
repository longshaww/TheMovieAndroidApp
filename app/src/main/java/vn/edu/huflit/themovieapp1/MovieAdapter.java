package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<MovieItem> list;
    private Listener listener;
    private Context context;

    public MovieAdapter(Context context, List<MovieItem> list, MovieAdapter.Listener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieItem movieItem = list.get(position);
//        holder.textPopularMovie.setText(movieList.title);
        ImageAPI.getCorner(movieItem.poster_path, 3, holder.MovieImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(movieItem);
                Intent intent = new Intent(context, DetailsMovieActivity.class);
                intent.putExtra("id", movieItem.id);
                intent.putExtra("media_type", movieItem.media_type);
                context.startActivity(intent);
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


    public interface Listener {
        void onClick(MovieItem item);
    }
}