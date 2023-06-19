package com.example.project_webapp.Service;

import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.PembayaranResponse;
import com.example.project_webapp.Service.HTTP.UserResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    @GET("pembayaran.php")
    Call<PembayaranResponse> pembayaranDetail(@Query("id_user") String id_user);

}
