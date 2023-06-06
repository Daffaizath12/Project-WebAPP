package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PemesananService {
    @Multipart
    @POST("pemesanan_api.php")
    Call<GlobalResponse> submitOrder(
            @Part("nama") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("telp") RequestBody telepon,
            @Part("cluster") RequestBody cluster,
            @Part("pembayaran") RequestBody pembayaran,
            @Part("tgl") RequestBody tgl,
            @Part("cicilandp") RequestBody cicilandp,
            @Part("cicilaninhouse") RequestBody cicilaninhouse,
            @Part("iduser") RequestBody user,
            @Part MultipartBody.Part gambar
    );
}
