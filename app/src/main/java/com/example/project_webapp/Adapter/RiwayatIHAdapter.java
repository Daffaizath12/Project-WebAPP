package com.example.project_webapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.R;
import com.squareup.picasso.Picasso;

public class RiwayatIHAdapter extends RecyclerView.Adapter<RiwayatIHAdapter.ViewHolder>{
    private Context context;
    RiwayatIHData[] riwayatList;

    public RiwayatIHAdapter(RiwayatIHData[] riwayatList, Context context) {
        this.context = context;
        this.riwayatList = riwayatList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_riwayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatIHData riwayatData = riwayatList[position];
        holder.bind(riwayatData);
    }

    @Override
    public int getItemCount() {
        return riwayatList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idpembayaran, namapemesan, namacluster, statuspembayaran, tanggal;
        private ImageView bukti;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idpembayaran = itemView.findViewById(R.id.idpembayaran);
            namapemesan = itemView.findViewById(R.id.namapemesan);
            namacluster = itemView.findViewById(R.id.namacluster);
            statuspembayaran = itemView.findViewById(R.id.statuspembayaran);
            tanggal = itemView.findViewById(R.id.tglpembayaran);
            bukti = itemView.findViewById(R.id.buktipembayaran);
        }
        public void bind(RiwayatIHData riwayatData) {
            idpembayaran.setText(riwayatData.getId_pembayaran());
            namapemesan.setText(riwayatData.getNama_pemesan());
            namacluster.setText(riwayatData.getNama_cluster());
            statuspembayaran.setText(riwayatData.getStatus());
            tanggal.setText(riwayatData.getTgl());
            // Load gambar menggunakan Picaso atau library lainnya
            Picasso.get().load(riwayatData.getBukti()).into(bukti);
        }
    }
}
