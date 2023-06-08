package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project_webapp.Adapter.SimpanData;
import com.example.project_webapp.Adapter.TersimpanAdapter;
import com.example.project_webapp.Adapter.SimpanData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.ClusterResponse;
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

        recyclerView = findViewById(R.id.viewtersimpan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(TersimpanActivity.this,2));

        getCluster(recyclerView);
    }
    private void getCluster(RecyclerView recyclerView) {
        Call<List<SimpanResponse>> simpanResponseCall = ApiClient.getUserService().getSimpanClusters(Integer.valueOf(Preferences.getLoggedInToken(TersimpanActivity.this)));
        simpanResponseCall.enqueue(new Callback<List<SimpanResponse>>() {
            @Override
            public void onResponse(Call<List<SimpanResponse>> call, Response<List<SimpanResponse>> response) {
                if (response.isSuccessful()){
                    List simpanResponse = response.body();
                    if (simpanResponse != null) {
                        List<SimpanResponse> dataList = simpanResponse;

                        // Mengubah List menjadi array ArticleData[]
                        SimpanData[] articleData = new SimpanData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            SimpanResponse data = dataList.get(i);

                            // Ambil data yang diperlukan dari objek data
                            int idSimpan = data.getIdSimpan();
                            int idCluster = data.getIdCluster();
                            int idUser = data.getIdUser();
                            String fotocluster = data.getFotocluster();
                            String harga = data.getHarga();
                            String namacluster = data.getNamacluster();

                            // Buat objek ArticleData dan tambahkan ke array
                            articleData[i] = new SimpanData(idSimpan, idCluster, idUser, fotocluster, harga, namacluster);
                        }

                        // Tambahkan kode untuk melakukan sesuatu dengan articleData, seperti mengatur adapter RecyclerView
                        TersimpanAdapter tersimpanAdapter = new TersimpanAdapter(articleData, TersimpanActivity.this);
                        recyclerView.setAdapter(tersimpanAdapter);
                    } else {
                        Toast.makeText(TersimpanActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TersimpanActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SimpanResponse>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}