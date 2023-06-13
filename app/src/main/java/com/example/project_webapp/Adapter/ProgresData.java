package com.example.project_webapp.Adapter;

import java.io.Serializable;
import java.util.Date;

public class ProgresData implements Serializable {
    private String id_pemesanan;
    private String nama_pemesan;
    private String status;
    private String keterangan;
    private String foto;
    private String tanggal;

    public ProgresData(String id_pemesanan, String nama_pemesan, String status, String keterangan, String foto, String tanggal) {
        this.id_pemesanan = id_pemesanan;
        this.nama_pemesan = nama_pemesan;
        this.status = status;
        this.keterangan = keterangan;
        this.foto = foto;
        this.tanggal = tanggal;
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public String getNama_pemesan() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan = nama_pemesan;
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
}
