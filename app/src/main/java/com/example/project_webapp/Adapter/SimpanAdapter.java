package com.example.project_webapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.DetailActivity;
import com.example.project_webapp.R;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.SimpanCluster;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimpanAdapter extends RecyclerView.Adapter<SimpanAdapter.ViewHolder> {

    private Context context;
    private List<SimpanCluster> simpanClusters;

    public SimpanAdapter(Context context, List<SimpanCluster> simpanClusters) {
        this.context = context;
        this.simpanClusters = simpanClusters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_viewholder,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        context = holder.itemView.getContext();
        SimpanCluster simpanCluster = simpanClusters.get(position);

        // Menampilkan gambar menggunakan Picasso
        // Menampilkan gambar menggunakan Picasso
        String imageUrl = getClusterImageUrl(simpanCluster.getFotoCluster());
        Picasso.get()
                .load(imageUrl)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("idcluster", String.valueOf(simpanCluster.getIdCluster()));
            context.startActivity(intent);
        });
    }

    private String getClusterImageUrl(String FotoCluster) {
        String baseUrl = ApiClient.getBaseUrl()+"/img/images_cluster/"+FotoCluster;
        return baseUrl;
    }

    @Override
    public int getItemCount() {
        return simpanClusters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}



