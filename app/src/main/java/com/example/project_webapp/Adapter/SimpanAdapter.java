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
import com.example.project_webapp.Service.HTTP.SimpanResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimpanAdapter extends RecyclerView.Adapter<SimpanAdapter.ViewHolder> {

    private Context context;
    private List<SimpanData> simpanList;

    public SimpanAdapter(List<SimpanData> simpanList, Context context) {
        this.context = context;
        this.simpanList = simpanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpanData simpanData = simpanList.get(position);
        holder.bind(simpanData);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("idcluster", String.valueOf(simpanData.getIdCluster()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return simpanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);
        }

        public void bind(SimpanData simpanData) {
            Picasso.get().load(simpanData.getFotocluster()).into(pic);
        }
    }
}



