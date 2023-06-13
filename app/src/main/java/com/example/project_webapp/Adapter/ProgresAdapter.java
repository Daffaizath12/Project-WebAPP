package com.example.project_webapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.ProgresData;
import com.example.project_webapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProgresAdapter extends RecyclerView.Adapter<ProgresAdapter.ProgresViewHolder> {
    private Context context;
    private List<ProgresData> progresList;

    public ProgresAdapter(Context context, List<ProgresData> progresList) {
        this.context = context;
        this.progresList = progresList;
    }

    @NonNull
    @Override
    public ProgresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_progres, parent, false);
        return new ProgresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgresViewHolder holder, int position) {
        ProgresData progresData = progresList.get(position);

        holder.txtIdPemesanan.setText(progresData.getId_pemesanan());
        holder.txtStatus.setText(progresData.getStatus());
        holder.txtKeterangan.setText(progresData.getKeterangan());
        holder.txtTanggal.setText(progresData.getTanggal());
        holder.txtNamaPemesan.setText(progresData.getNama_pemesan());

        Picasso.get().load(progresData.getFoto()).into(holder.imgFoto);
    }

    @Override
    public int getItemCount() {
        return progresList.size();
    }

    public static class ProgresViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdPemesanan;
        public TextView txtStatus;
        public TextView txtKeterangan;
        public TextView txtTanggal;
        public TextView txtNamaPemesan;
        public ImageView imgFoto;

        public ProgresViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdPemesanan = itemView.findViewById(R.id.idpesan);
            txtStatus = itemView.findViewById(R.id.status);
            txtKeterangan = itemView.findViewById(R.id.keterangan);
            txtTanggal = itemView.findViewById(R.id.progrestgl);
            txtNamaPemesan = itemView.findViewById(R.id.namapemesan);
            imgFoto = itemView.findViewById(R.id.fotoprogres);
        }
    }