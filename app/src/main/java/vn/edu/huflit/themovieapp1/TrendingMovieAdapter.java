package vn.edu.huflit.themovieapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class TrendingMovieAdapter extends RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder> {
    private List<Entertainment> list;
    private Listener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entertainment entertainment = list.get(position);
//        if (entertainment.media_type.equals("movie")) {
//            MovieItem movie = (MovieItem) entertainment;
//            holder.textTrending.setText(movie.title);
//        } else {
//            TVItem tv = (TVItem) entertainment;
//            holder.textTrending.setText(tv.name);
//        }
        ImageAPI.getCorner(entertainment.poster_path, 3, holder.trending);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(entertainment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView trending;
//        private TextView textTrending;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trending = itemView.findViewById(R.id.trendingImage);
//            textTrending = itemView.findViewById(R.id.textTrending);
        }
    }

    public TrendingMovieAdapter(List<Entertainment> list, Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface Listener {
        void onClick(Entertainment item);
    }
}
