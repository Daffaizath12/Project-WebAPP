package com.example.project_webapp.Service.HTTP;

import com.example.project_webapp.Adapter.ProgresData;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProgresResponse {

    @SerializedName("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String id_pemesanan;
        private String status;
        private String keterangan;
        private String foto;
        private String tanggal;
        private String nama_pemesan;

        public String getIdPemesanan() {
            return id_pemesanan;
        }

        public void setIdPemesanan(String id_pemesanan) {
            this.id_pemesanan = id_pemesanan;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getNamaPemesan() {
            return nama_pemesan;
        }

        public void setNamaPemesan(String nama_pemesan) {
            this.nama_pemesan = nama_pemesan;
        }
    }
}

