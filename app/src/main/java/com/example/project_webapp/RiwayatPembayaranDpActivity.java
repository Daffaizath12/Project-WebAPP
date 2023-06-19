package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.project_webapp.Adapter.RiwayatAdapter;
import com.example.project_webapp.Adapter.RiwayatData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.RiwayatResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPembayaranDpActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pembayarandp);

        recyclerView = findViewById(R.id.viewriwayatdp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatPembayaranDpActivity.this, LinearLayoutManager.VERTICAL, false));


        // Inisialisasi SharedPreferences
        String idUser = Preferences.getLoggedInToken(RiwayatPembayaranDpActivity.this);

        // Panggil API untuk mendapatkan data simpan cluster
        getRiwayatDpDetail(idUser);
    }

    private void getRiwayatDpDetail(String idUser) {
        Call<RiwayatResponse> call = ApiClient.getUserService().getRiwayatDp(idUser);
        call.enqueue(new Callback<RiwayatResponse>() {
            @Override
            public void onResponse(Call<RiwayatResponse> call, Response<RiwayatResponse> response) {

                if (response.isSuccessful()) {
                    RiwayatResponse riwayatResponse = response.body();
                    if (riwayatResponse != null) {
                        List<RiwayatResponse.Data> dataList = riwayatResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        RiwayatData[] riwayatData = new RiwayatData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            RiwayatResponse.Data data = dataList.get(i);
                            String idPembayaran = data.getId_pembayaran();
                            String idCluster = data.getId_cluster();
                            String namaPemesan = data.getNama_pemesan();
                            String namaCluster = data.getNama_cluster();
                            String status = data.getStatus();
                            String tgl = data.getTgl();
                            String bukti = data.getBukti();

                            riwayatData[i] = new RiwayatData(idPembayaran, idCluster, namaPemesan, namaCluster, status, tgl, ApiClient.getBaseUrl()+"/api/"+bukti);
                        }

                        RiwayatAdapter riwayatAdapter = new RiwayatAdapter(riwayatData, RiwayatPembayaranDpActivity.this);
                        recyclerView.setAdapter(riwayatAdapter);
                    } else {
                        Toast.makeText(RiwayatPembayaranDpActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RiwayatPembayaranDpActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RiwayatResponse> call, Throwable t) {
                Toast.makeText(RiwayatPembayaranDpActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}