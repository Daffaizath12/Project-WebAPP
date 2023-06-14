package com.example.project_webapp.Adapter;

import java.io.Serializable;

public class SimpanData implements Serializable {

    private String idSimpan;
    private String idCluster;
    private String fotocluster;
    private String harga;
    private String namacluster;

    public SimpanData(String idSimpan, String idCluster, String fotocluster, String harga, String namacluster) {
        this.idSimpan = idSimpan;
        this.idCluster = idCluster;
        this.fotocluster = fotocluster;
        this.harga = harga;
        this.namacluster = namacluster;
    }

    public String getIdSimpan() {
        return idSimpan;
    }

    public String getIdCluster() {
        return idCluster;
    }

    public String getFotocluster() {
        return fotocluster;
    }

    public String getHarga() {
        return harga;
    }

    public String getNamacluster() {
        return namacluster;
    }
}
