package com.example.project_webapp.Service;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getBaseUrl()+"/api/")
                .client(okHttpClient)
                .build();

        return retrofit;

    }

    public static UserService getUserService() {
        UserService userService = getRetrofit().create(UserService.class);

        return userService;
    }

    public static DetailService getDetailService(Context detailActivity) {
        DetailService detailService = getRetrofit().create(DetailService.class);

        return detailService;
    }

    public static PemesananService getPemesananService(Context pemesananActivity) {
       PemesananService pemesananService = getRetrofit().create(PemesananService.class);

        return pemesananService;
    }
    public static PembayaranDpService getPembayaranService(Context pembayaranDpActivity) {
        PembayaranDpService pembayaranService = getRetrofit().create(PembayaranDpService.class);

        return pembayaranService;
    }


    public static PembayaranInHouseService getPembayaranInhouseService(Context pembayaranInHouseActivity) {
        PembayaranInHouseService pembayaranInhouseService = getRetrofit().create(PembayaranInHouseService.class);

        return pembayaranInhouseService;
    }

    public static String getBaseUrl(){
        return "http://bernadylandslawu.wsmif3a.id/api/register_api.php";
    }
    public static String getBaseLogin(){
        return "http://bernadylandslawu.wsmif3a.id/api/login_api.php";
    }
    public static  String getImgUrl(){
        return "http://bernadylandslawu.wsmif3a.id/api";
    }
}