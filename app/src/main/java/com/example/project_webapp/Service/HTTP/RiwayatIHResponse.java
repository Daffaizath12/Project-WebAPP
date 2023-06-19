package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RiwayatIHResponse {
    @SerializedName("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data{
        private String id_pembayaran;
        private String id_cluster;
        private String nama_pemesan;
        private String nama_cluster;
        private String status;
        private String tgl;
        private String bukti;

        public String getId_pembayaran() {
            return id_pembayaran;
        }

        public void setId_pembayaran(String id_pembayaran) {
            this.id_pembayaran = id_pembayaran;
        }

        public String getId_cluster() {
            return id_cluster;
        }

        public void setId_cluster(String id_cluster) {
            this.id_cluster = id_cluster;
        }

        public String getNama_pemesan() {
            return nama_pemesan;
        }

        public void setNama_pemesan(String nama_pemesan) {
            this.nama_pemesan = nama_pemesan;
        }

        public String getNama_cluster() {
            return nama_cluster;
        }

        public void setNama_cluster(String nama_cluster) {
            this.nama_cluster = nama_cluster;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public String getBukti() {
            return bukti;
        }

        public void setBukti(String bukti) {
            this.bukti = bukti;
        }
    }
}
