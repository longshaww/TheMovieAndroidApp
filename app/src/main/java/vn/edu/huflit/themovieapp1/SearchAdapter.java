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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<Entertainment> list;
    private Listener listener;
    private Context context;

    public SearchAdapter(Context context, List<Entertainment> list, Listener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entertainment entertainment = list.get(position);
        if (entertainment.media_type.equals("movie")) {
            MovieItem movie = (MovieItem) entertainment;
            holder.txtNameItemSearch.setText(movie.title);
        } else {
            TVItem tv = (TVItem) entertainment;
            holder.txtNameItemSearch.setText(tv.name);
        }
        ImageAPI.getCorner(entertainment.backdrop_path, 3, holder.imgItemSearch);
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
        private ImageView imgItemSearch;
        private TextView txtNameItemSearch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemSearch = itemView.findViewById(R.id.imgItemSearch);
            txtNameItemSearch = itemView.findViewById(R.id.txtNameItemSearch);
        }
    }

    public interface Listener {
        void onClick(Entertainment item);
    }
}
