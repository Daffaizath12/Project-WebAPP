package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PembayaranDpService {
    @Multipart
    @POST("pembayaran-dp_api.php")
    Call<GlobalResponse> submitOrder(
            @Part("id_pemesanan_rumah") RequestBody idPemesananRumah,
            @Part("tgl_pembayaran_dp") RequestBody tglPembayaranDp,
            @Part MultipartBody.Part filePart
    );
}
