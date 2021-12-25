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

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private List<Cast> list;
    private Listener listener;
    private Context context;

    public CastAdapter(Context context, List<Cast> list, Listener listener, boolean b) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_cast, parent, false);
        return new CastAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast tvList = list.get(position);
        holder.txtNameCast.setText(tvList.name);
        holder.txtCharacter.setText(tvList.character);
        ImageAPI.getCircle(tvList.profile_path, 3, holder.imgCast);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(tvList);
//                Intent intent = new Intent(context, DetailsTVActivity.class);
//                intent.putExtra("id", tvList.id);
//                intent.putExtra("media_type", tvList.credit_id);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCast;
        private TextView txtNameCast,txtCharacter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCast = itemView.findViewById(R.id.imgCast);
            txtNameCast = itemView.findViewById(R.id.txtNameCast);
            txtCharacter = itemView.findViewById(R.id.txtCharacter);
        }
    }

    public interface Listener {
        void onClick(Cast item);
    }
}
