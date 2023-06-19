package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PembayaranResponse {
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    private List<Data> data;

    public static class Data {
        private int id_pemesanan_rumah;
        private int id_cluster;
        private String nama_cluster;

        public int getId_pemesanan_rumah() {
            return id_pemesanan_rumah;
        }

        public void setId_pemesanan_rumah(int id_pemesanan_rumah) {
            this.id_pemesanan_rumah = id_pemesanan_rumah;
        }

        public int getId_cluster() {
            return id_cluster;
        }

        public void setId_cluster(int id_cluster) {
            this.id_cluster = id_cluster;
        }

        public String getNama_cluster() {
            return nama_cluster;
        }

        public void setNama_cluster(String nama_cluster) {
            this.nama_cluster = nama_cluster;
        }
    }
}
