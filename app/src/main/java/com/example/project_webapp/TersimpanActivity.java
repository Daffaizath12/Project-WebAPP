package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.ClusterData;
import com.example.project_webapp.Adapter.SimpanAdapter;
import com.example.project_webapp.Adapter.SimpanData;
import com.example.project_webapp.Adapter.SimpanAdapter;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.SimpanResponse;
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
                        List<SimpanResponse.Data> dataList = simpanResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        SimpanData[] simpanData = new SimpanData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            SimpanResponse.Data data = dataList.get(i);
                            String idSimpan = data.getIdSimpan();
                            String idCluster = data.getIdCluster();
                            String fotocluster = data.getFotoCluster();
                            String harga = data.getHarga();
                            String namacluster = data.getNamaCluster();

                            simpanData[i] = new SimpanData(idSimpan, idCluster, ApiClient.getBaseUrl()+"/img/images_cluster/"+fotocluster, harga, namacluster);
                        }

                        SimpanAdapter simpanAdapter = new SimpanAdapter(simpanData, TersimpanActivity.this);
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
                Toast.makeText(TersimpanActivity.this, "Terjadi kesalahan: Tidak Ada Data", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
