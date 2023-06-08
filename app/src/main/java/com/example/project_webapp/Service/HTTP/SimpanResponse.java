package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

public class SimpanResponse {
    @SerializedName("id_simpan")
    private int idSimpan;

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

    @SerializedName("id_user")
    private int idUser;

    @SerializedName("id_cluster")
    private int idCluster;

    @SerializedName("foto_cluster")
    private String fotocluster;

    @SerializedName("harga")
    private String harga;

    @SerializedName("nama_cluster")
    private String namacluster;
}
