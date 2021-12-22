package vn.edu.huflit.themovieapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable  {
    private List<Entertainment> list;
    private Listener listener;
    private Context context;
    private String keyAPI = "743a82500e05c3b60a15c2d5030bc55f";

    public SearchAdapter(Context context, List<Entertainment> list, Listener listener) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entertainment entertainment = list.get(position);
        if (entertainment.media_type.equals("movie")) {
            MovieItem movie = (MovieItem) entertainment;
            holder.txtNameItemSearch.setText(movie.title);
        } else if (entertainment.media_type.equals("tv")) {
            TVItem tv = (TVItem) entertainment;
            holder.txtNameItemSearch.setText(tv.name);
        } else {
            PersonItem person = (PersonItem) entertainment;
            holder.txtNameItemSearch.setText(person.name);
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
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    TVItem tv = (TVItem) entertainment;
                    Intent intent = new Intent(context, DetailsTVActivity.class);
                    intent.putExtra("id", tv.id);
                    intent.putExtra("media_type", tv.media_type);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new searchData();
    }

    public class searchData extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            MovieAPI api = new MovieAPI(keyAPI);
            String query = charSequence.toString();

            if(query.isEmpty()) {
                list.clear();
            }else {
                list = api.search(query);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = list;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list = (List<Entertainment>) filterResults.values;
            notifyDataSetChanged();
        }
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
