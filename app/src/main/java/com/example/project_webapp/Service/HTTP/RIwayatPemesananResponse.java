package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RIwayatPemesananResponse {
    @SerializedName("data")
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data{
        private String nama_pemesan;
        private String alamat;
        private String telp;
        private String nama_cluster;
        private String tgl;
        private String pembayaran;
        private String dp;
        private String inhouse;
        private String blok;
        private String surat_bangunan;
        private String ktp;

        public String getNama_pemesan() {
            return nama_pemesan;
        }

        public void setNama_pemesan(String nama_pemesan) {
            this.nama_pemesan = nama_pemesan;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getTelp() {
            return telp;
        }

        public void setTelp(String telp) {
            this.telp = telp;
        }

        public String getNama_cluster() {
            return nama_cluster;
        }

        public void setNama_cluster(String nama_cluster) {
            this.nama_cluster = nama_cluster;
        }

        public String getTgl() {
            return tgl;
        }

        public void setTgl(String tgl) {
            this.tgl = tgl;
        }

        public String getPembayaran() {
            return pembayaran;
        }

        public void setPembayaran(String pembayaran) {
            this.pembayaran = pembayaran;
        }

        public String getDp() {
            return dp;
        }

        public void setDp(String dp) {
            this.dp = dp;
        }

        public String getInhouse() {
            return inhouse;
        }

        public void setInhouse(String inhouse) {
            this.inhouse = inhouse;
        }

        public String getBlok() {
            return blok;
        }

        public void setBlok(String blok) {
            this.blok = blok;
        }

        public String getSurat_bangunan() {
            return surat_bangunan;
        }

        public void setSurat_bangunan(String surat_bangunan) {
            this.surat_bangunan = surat_bangunan;
        }

        public String getKtp() {
            return ktp;
        }

        public void setKtp(String ktp) {
            this.ktp = ktp;
        }
    }
}
