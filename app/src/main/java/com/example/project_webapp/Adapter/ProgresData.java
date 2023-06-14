package com.example.project_webapp.Adapter;

import java.io.Serializable;
import java.util.Date;

public class ProgresData {
    private String idPemesanan;
    private String status;
    private String keterangan;
    private String foto;
    private String tanggal;
    private String namaPemesan;

    public ProgresData(String idPemesanan, String status, String keterangan, String foto, String tanggal, String namaPemesan) {
        this.idPemesanan = idPemesanan;
        this.status = status;
        this.keterangan = keterangan;
        this.foto = foto;
        this.tanggal = tanggal;
        this.namaPemesan = namaPemesan;
    }

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public String getStatus() {
        return status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getFoto() {
        return foto;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }
}
