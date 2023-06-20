package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.PembayaranResponse;
import com.example.project_webapp.Service.HTTP.RiwayatResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface PembayaranInHouseService {
    @Multipart
    @POST("pembayaran-inhouse_api.php")
    Call<Void> submitOrder(
            @Part("id_pemesanan_rumah") RequestBody idPemesananRumah,
            @Part("tgl_pembayaran_inhouse") RequestBody tglPembayaranInHouse,
            @Part MultipartBody.Part buktiPembayaranInHouse
    );
}
