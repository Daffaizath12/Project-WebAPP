package com.example.project_webapp.Service.HTTP;

import com.google.gson.annotations.SerializedName;

public class SimpanRequest {
    @SerializedName("id_user")
    private String idUser;

    @SerializedName("id_cluster")
    private String idCluster;

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
}
