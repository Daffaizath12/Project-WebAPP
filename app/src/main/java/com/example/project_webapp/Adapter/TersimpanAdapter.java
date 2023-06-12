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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TersimpanAdapter extends RecyclerView.Adapter<TersimpanAdapter.ViewHolder>{
    SimpanData [] simpanData;
    Context context;

    public TersimpanAdapter(SimpanData[] simpanData, Context context) {
        this.simpanData = simpanData;
        this.context = context;
    }

    @NonNull
    @Override
    public TersimpanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_tersimpan,parent,false);
        TersimpanAdapter.ViewHolder viewHolder = new TersimpanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TersimpanAdapter.ViewHolder holder, int position) {

        final Context context = holder.itemView.getContext();
        final SimpanData simpanDatalist = simpanData[position];
        Picasso.get().load(simpanDatalist.getFotocluster()).into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("idcluster", String.valueOf(simpanDatalist.getIdCluster()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return simpanData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic);

        }
    }
}
