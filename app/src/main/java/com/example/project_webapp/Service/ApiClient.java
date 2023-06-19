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

    public static String getBaseUrl(){
        return "http://192.168.1.3/Project-WSI";
    }
    public static  String getImgUrl(){
        return "http://192.168.1.3/Project-WSI/img";
    }
}