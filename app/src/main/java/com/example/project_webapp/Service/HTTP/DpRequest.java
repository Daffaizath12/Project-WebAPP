package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

public class DpRequest {
    @SerializedName("id_pemesanan_rumah")
    private String idPemesananRumah;

    public DpRequest(String idPemesananRumah) {
        this.idPemesananRumah = idPemesananRumah;
    }

    public String getIdPemesananRumah() {
        return idPemesananRumah;
    }

    public void setIdPemesananRumah(String idPemesananRumah) {
        this.idPemesananRumah = idPemesananRumah;
    }
}
