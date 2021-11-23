package vn.edu.huflit.themovieapp1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ViewHolder> {
    private List<TVItem> list;
    private TVAdapter.Listener listener;

    @NonNull
    @Override
    public TVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_movie_image, parent, false);
        return new TVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVAdapter.ViewHolder holder, int position) {
        TVItem tvList = list.get(position);
//        holder.textPopularMovie.setText(movieList.title);
        ImageAPI.getCorner(tvList.poster_path, 3, holder.TVImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(tvList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView TVImage;
//        private TextView textPopularMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TVImage = itemView.findViewById(R.id.trendingImage);
//            textPopularMovie = itemView.findViewById(R.id.textTrending);
        }
    }

    public TVAdapter(List<TVItem> list, TVAdapter.Listener listener) {
        this.list = list;
        this.listener = listener;
    }

    public interface Listener {
        void onClick(TVItem item);
    }
}
