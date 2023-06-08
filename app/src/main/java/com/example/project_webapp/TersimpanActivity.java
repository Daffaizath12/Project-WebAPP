package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project_webapp.Adapter.ClusterAdapter;
import com.example.project_webapp.Adapter.ClusterData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.ClusterResponse;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(TersimpanActivity.this, LinearLayoutManager.VERTICAL, false));

        getCluster(recyclerView);
    }
    private void getCluster(RecyclerView recyclerView) {
        Call<ClusterResponse> clusterResponseCall = ApiClient.getDetailService(TersimpanActivity.this).getCluster();
        clusterResponseCall.enqueue(new Callback<ClusterResponse>() {
            @Override
            public void onResponse(Call<ClusterResponse> call, Response<ClusterResponse> response) {

                if (response.isSuccessful()){
                    ClusterResponse clusterResponse = response.body();
                    if (clusterResponse != null) {
                        List<ClusterResponse.Data> dataList = clusterResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        ClusterData[] articleData = new ClusterData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            ClusterResponse.Data data = dataList.get(i);

                            // Ambil data yang diperlukan dari objek data
                            int id = data.getId();
                            String pondasi = data.getPondasi();
                            String dinding = data.getDinding();
                            String rangkaatap = data.getRangkaatap();
                            String kusen = data.getKusen();
                            String plafond = data.getPlafond();
                            String air = data.getAir();
                            String listrik = data.getListrik();
                            String jumlahkamar = data.getJumlahkamar();
                            String luastanah = data.getLuastanah();
                            String fotocluster = data.getFotocluster();
                            String harga = data.getHarga();
                            String namacluster = data.getNamacluster();

                            // Buat objek ArticleData dan tambahkan ke array
                            articleData[i] = new ClusterData(id, pondasi, dinding, rangkaatap, kusen, plafond, air, listrik, jumlahkamar, luastanah,
                                    ApiClient.getBaseUrl()+"/img/images_cluster/"+fotocluster, harga, namacluster);
                        }

                        // Tambahkan kode untuk melakukan sesuatu dengan articleData, seperti mengatur adapter RecyclerView
                        ClusterAdapter clusterAdapter = new ClusterAdapter(articleData, TersimpanActivity.this);
                        recyclerView.setAdapter(clusterAdapter);
                    } else {
                        Toast.makeText(TersimpanActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TersimpanActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClusterResponse> call, Throwable t) {

                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}