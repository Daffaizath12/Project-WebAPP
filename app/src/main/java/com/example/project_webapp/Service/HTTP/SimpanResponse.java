package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimpanResponse {
    @SerializedName("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String id_simpan;
        private String idUser;
        private String id_cluster;
        private String namacluster;
        private String fotocluster;
        private String harga;

        public String getIdSimpan() {
            return id_simpan;
        }

        public void setIdSimpan(String id_simpan) {
            this.id_simpan = id_simpan;
        }

        public String getIdUser() {
            return idUser;
        }

        public void setIdUser(String idUser) {
            this.idUser = idUser;
        }

        public String getIdCluster() {
            return id_cluster;
        }

        public void setIdCluster(String id_cluster) {
            this.id_cluster = id_cluster;
        }

        public String getNamaCluster() {
            return namacluster;
        }

        public void setNamaCluster(String namacluster) {
            this.namacluster = namacluster;
        }

        public String getFotoCluster() {
            return fotocluster;
        }

        public void setFotoCluster(String fotocluster) {
            this.fotocluster = fotocluster;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }
    }
}
