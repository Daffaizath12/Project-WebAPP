package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.SimpanAdapter;
import com.example.project_webapp.Adapter.SimpanData;
import com.example.project_webapp.Adapter.SimpanAdapter;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.SimpanResponse;
import com.example.project_webapp.Service.HTTP.SimpanResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TersimpanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tersimpan);

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.viewtersimpan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(TersimpanActivity.this, LinearLayoutManager.VERTICAL, false));


        // Inisialisasi SharedPreferences
        String idUser = Preferences.getLoggedInToken(TersimpanActivity.this);

        // Panggil API untuk mendapatkan data simpan cluster
        getSimpanClusters(idUser);
    }

    private void getSimpanClusters(String idUser) {
        Call<SimpanResponse> call = ApiClient.getUserService().getSimpanCluster(idUser);
        call.enqueue(new Callback<SimpanResponse>() {
            @Override
            public void onResponse(Call<SimpanResponse> call, Response<SimpanResponse> response) {

                if (response.isSuccessful()) {
                    SimpanResponse simpanResponse = response.body();
                    if (simpanResponse != null) {
                        String idSimpan = simpanResponse.getIdSimpan();
                        String idCluster = simpanResponse.getIdCluster();
                        String fotocluster = simpanResponse.getFotocluster();
                        String harga = simpanResponse.getHarga();
                        String namacluster = simpanResponse.getNamacluster();

                        SimpanData simpanData = new SimpanData(idSimpan, idCluster, ApiClient.getBaseUrl()+"img//images_cluster/"+fotocluster, harga, namacluster);
                        List<SimpanData> simpanList = new ArrayList<>();
                        simpanList.add(simpanData);

                        SimpanAdapter simpanAdapter = new SimpanAdapter(simpanList, TersimpanActivity.this);
                        recyclerView.setAdapter(simpanAdapter);
                    } else {
                        Toast.makeText(TersimpanActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TersimpanActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SimpanResponse> call, Throwable t) {
                Toast.makeText(TersimpanActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
