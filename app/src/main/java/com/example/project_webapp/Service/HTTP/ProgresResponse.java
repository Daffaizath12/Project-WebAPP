package com.example.project_webapp.Service.HTTP;

import com.example.project_webapp.Adapter.ProgresData;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProgresResponse {
    @SerializedName("id_pemesanan")
    private String idPemesanan;

    @SerializedName("status")
    private String status;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("foto")
    private String foto;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("nama_pemesan")
    private String namaPemesan;

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

