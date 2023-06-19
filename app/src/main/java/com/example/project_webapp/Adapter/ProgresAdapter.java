package com.example.project_webapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.ProgresData;
import com.example.project_webapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProgresAdapter extends RecyclerView.Adapter<ProgresAdapter.ProgresViewHolder> {
    private ProgresData[] progresList;
    private Context context;

    public ProgresAdapter(ProgresData[] progresList, Context context) {
        this.progresList = progresList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProgresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_progres, parent, false);
        return new ProgresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgresViewHolder holder, int position) {
        ProgresData progresData = progresList[position];
        holder.bind(progresData);
    }

    @Override
    public int getItemCount() {
        return progresList.length;
    }

    public static class ProgresViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIdPemesanan, tvStatus, tvKeterangan, tvTanggal, tvNamaPemesan;
        private ImageView ivFoto;

        public ProgresViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPemesanan = itemView.findViewById(R.id.idpesan);
            tvStatus = itemView.findViewById(R.id.statusprogres);
            tvKeterangan = itemView.findViewById(R.id.keterangan);
            tvTanggal = itemView.findViewById(R.id.progrestgl);
            tvNamaPemesan = itemView.findViewById(R.id.namapemesan);
            ivFoto = itemView.findViewById(R.id.fotoprogres);
        }

        public void bind(ProgresData progresData) {
            tvIdPemesanan.setText(progresData.getIdPemesanan());
            tvStatus.setText(progresData.getStatus());
            tvKeterangan.setText(progresData.getKeterangan());
            tvTanggal.setText(progresData.getTanggal());
            tvNamaPemesan.setText(progresData.getNamaPemesan());
            // Load gambar menggunakan Picaso atau library lainnya
            Picasso.get().load(progresData.getFoto()).into(ivFoto);
        }
    }
}
