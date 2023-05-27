package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.ClusterResponse;
import com.example.project_webapp.Service.HTTP.DetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailService {

    @GET("cluster_api.php")
    Call<ClusterResponse> getCluster();
    @GET("detail_api.php")
    Call<DetailResponse> getServiceDetail(@Query("id_cluster") String id_cluster);
}
