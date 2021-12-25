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

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {
    private List<Crew> list;
    private Listener listener;
    private Context context;

    public CrewAdapter(Context context, List<Crew> list, Listener listener, boolean b) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CrewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_cast, parent, false);
        return new CrewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crew crewList = list.get(position);
        holder.txtNameCast_Crew.setText(crewList.name);
        holder.txtCharacter_Department.setText(crewList.department);
        ImageAPI.getCircle(crewList.profile_path, 3, holder.imgCast_Crew);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(crewList);
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
        private ImageView imgCast_Crew;
        private TextView txtNameCast_Crew,txtCharacter_Department;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCast_Crew = itemView.findViewById(R.id.imgCast_Crew);
            txtNameCast_Crew = itemView.findViewById(R.id.txtNameCast_Crew);
            txtCharacter_Department = itemView.findViewById(R.id.txtCharacter_Department);
        }
    }

    public interface Listener {
        void onClick(Crew item);
    }
}

