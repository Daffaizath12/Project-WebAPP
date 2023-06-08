package com.example.project_webapp.Adapter;

import java.io.Serializable;

public class SimpanData implements Serializable {

    private int idSimpan;
    private int idUser;

    public int getIdSimpan() {
        return idSimpan;
    }

    public void setIdSimpan(int idSimpan) {
        this.idSimpan = idSimpan;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(int idCluster) {
        this.idCluster = idCluster;
    }

    public String getFotocluster() {
        return fotocluster;
    }

    public void setFotocluster(String fotocluster) {
        this.fotocluster = fotocluster;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamacluster() {
        return namacluster;
    }

    public void setNamacluster(String namacluster) {
        this.namacluster = namacluster;
    }

    private int idCluster;
    private String fotocluster;
    private String harga;
    private String namacluster;

    public SimpanData(int idSimpan, int idCluster, int idUser, String fotocluster, String harga, String namacluster) {
        this.idSimpan = idSimpan;
        this.idCluster = idCluster;
        this.idUser = idUser;
        this.fotocluster = fotocluster;
        this.harga = harga;
        this.namacluster = namacluster;
    }
}
