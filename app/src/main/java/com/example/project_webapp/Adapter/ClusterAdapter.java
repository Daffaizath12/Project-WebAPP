package com.example.project_webapp.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project_webapp.DetailActivity;
import com.example.project_webapp.R;
import com.example.project_webapp.Service.HTTP.ClusterResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClusterAdapter extends RecyclerView.Adapter<ClusterAdapter.ViewHolder> {
    ClusterData[] clusterData;
    Context context;

    public ClusterAdapter(ClusterData[] clusterData, Context context) {
        this.clusterData = clusterData;
        this.context = context;
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

        final Context context = holder.itemView.getContext();
        final ClusterData clusterDatalist = clusterData[position];
        Picasso.get().load(clusterDatalist.getFotocluster()).into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("idcluster", String.valueOf(clusterDatalist.getId()));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return clusterData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);

        }
    }
}