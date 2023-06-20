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

public class RiwayatPemesananAdapter extends RecyclerView.Adapter<RiwayatPemesananAdapter.ViewHolder>{
    private Context context;
    RiwayatPemesananData[] riwayatList;

    public RiwayatPemesananAdapter(RiwayatPemesananData[] riwayatList, Context context) {
        this.context = context;
        this.riwayatList = riwayatList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pemesanan_rumah, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatPemesananData riwayatPemesananData = riwayatList[position];
        holder.bind(riwayatPemesananData);
    }

    @Override
    public int getItemCount() {
        return riwayatList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namapemesan, alamat, notelp, namacluster, tglpesan, pembayaran, dp, inhouse, detailblok, srtbangunan;
        private ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namapemesan = itemView.findViewById(R.id.namapemesan);
            alamat = itemView.findViewById(R.id.alamat);
            notelp = itemView.findViewById(R.id.notelp);
            namacluster = itemView.findViewById(R.id.namacluster);
            tglpesan = itemView.findViewById(R.id.tglpesan);
            pembayaran = itemView.findViewById(R.id.pembayaran);
            dp = itemView.findViewById(R.id.dp);
            inhouse = itemView.findViewById(R.id.inhouse);
            detailblok = itemView.findViewById(R.id.detailblok);
            srtbangunan = itemView.findViewById(R.id.srtbangunan);
            pic = itemView.findViewById(R.id.pic);
        }
        public void bind(RiwayatPemesananData riwayatPemesananData) {
            namapemesan.setText(riwayatPemesananData.getNama_pemesan());
            alamat.setText(riwayatPemesananData.getAlamat());
            notelp.setText(riwayatPemesananData.getTelp());
            namacluster.setText(riwayatPemesananData.getNama_cluster());
            tglpesan.setText(riwayatPemesananData.getTgl());
            pembayaran.setText(riwayatPemesananData.getPembayaran());
            dp.setText(riwayatPemesananData.getDp());
            inhouse.setText(riwayatPemesananData.getInhouse());
            detailblok.setText(riwayatPemesananData.getBlok());
            srtbangunan.setText(riwayatPemesananData.getSurat_bangunan());
            // Load gambar menggunakan Picaso atau library lainnya
            Picasso.get().load(riwayatPemesananData.getKtp()).into(pic);
        }
    }
}
