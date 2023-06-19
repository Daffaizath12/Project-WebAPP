package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.LoginRequest;
import com.example.project_webapp.Service.HTTP.LoginResponse;
import com.example.project_webapp.Service.HTTP.ProgresResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;
import com.example.project_webapp.Service.HTTP.RiwayatIHResponse;
import com.example.project_webapp.Service.HTTP.RiwayatResponse;
import com.example.project_webapp.Service.HTTP.SimpanResponse;
import com.example.project_webapp.Service.HTTP.SimpanRequest;
import com.example.project_webapp.Service.HTTP.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("login_api.php")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @POST("register_api.php")
    Call<GlobalResponse> userRegister(@Body RegisterRequest registerRequest);

    @GET("user.php")
    Call<UserResponse> userDetail(@Query("id_user") String id_user);

    @POST("tersimpan_api.php")
    Call<GlobalResponse> simpanCluster(@Body SimpanRequest simpanRequest);

    @GET("cluster_tersimpan.php")
    Call<SimpanResponse> getSimpanCluster(@Query("id_user") String idUser);

    @GET("progres_api.php")
    Call<ProgresResponse> getProgres(@Query("id_user") String idUser);

    @GET("riwayat_pembayarandp.php")
    Call<RiwayatResponse> getRiwayatDp(@Query("id_user") String id_user);

    @GET("riwayat_pembayaraninhouse.php")
    Call<RiwayatIHResponse> getRiwayatinhouse(@Query("id_user") String id_user);
}

