package com.example.project_webapp.Service.HTTP;

public class SimpanResponse {
    private String idSimpan;
    private String idUser;
    private String idCluster;
    private String namaCluster;
    private String fotoCluster;
    private String harga;

    public String getIdSimpan() {
        return idSimpan;
    }

    public void setIdSimpan(String idSimpan) {
        this.idSimpan = idSimpan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdCluster() {
        return idCluster;
    }

    public void setIdCluster(String idCluster) {
        this.idCluster = idCluster;
    }

    public String getNamaCluster() {
        return namaCluster;
    }

    public void setNamaCluster(String namaCluster) {
        this.namaCluster = namaCluster;
    }

    public String getFotoCluster() {
        return fotoCluster;
    }

    public void setFotoCluster(String fotoCluster) {
        this.fotoCluster = fotoCluster;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
