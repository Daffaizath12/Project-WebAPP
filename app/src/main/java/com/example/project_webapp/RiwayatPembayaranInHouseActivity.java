package com.example.project_webapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.RiwayatAdapter;
import com.example.project_webapp.Adapter.RiwayatIHAdapter;
import com.example.project_webapp.Adapter.RiwayatIHData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.RiwayatIHResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPembayaranInHouseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pembayaraninhouse);

        backbtn = findViewById(R.id.backbtn);

        recyclerView = findViewById(R.id.viewriwayatinhouse);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatPembayaranInHouseActivity.this, LinearLayoutManager.VERTICAL, false));


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiwayatPembayaranInHouseActivity.this, PembayaranActivity.class);
                startActivity(intent);
            }
        });


        // Inisialisasi SharedPreferences
        String idUser = Preferences.getLoggedInToken(RiwayatPembayaranInHouseActivity.this);

        // Panggil API untuk mendapatkan data simpan cluster
        getRiwayatinhouseDetail(idUser);
    }

    private void getRiwayatinhouseDetail(String idUser) {
        Call<RiwayatIHResponse> call = ApiClient.getUserService().getRiwayatinhouse(idUser);
        call.enqueue(new Callback<RiwayatIHResponse>() {
            @Override
            public void onResponse(Call<RiwayatIHResponse> call, Response<RiwayatIHResponse> response) {

                if (response.isSuccessful()) {
                    RiwayatIHResponse riwayatIHResponse = response.body();
                    if (riwayatIHResponse != null) {
                        List<RiwayatIHResponse.Data> dataList = riwayatIHResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        RiwayatIHData[] riwayatIHData = new RiwayatIHData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            RiwayatIHResponse.Data data = dataList.get(i);
                            String idPembayaran = data.getId_pembayaran();
                            String idCluster = data.getId_cluster();
                            String namaPemesan = data.getNama_pemesan();
                            String namaCluster = data.getNama_cluster();
                            String status = data.getStatus();
                            String tgl = data.getTgl();
                            String bukti = data.getBukti();

                            riwayatIHData[i] = new RiwayatIHData(idPembayaran, idCluster, namaPemesan, namaCluster, status, tgl, ApiClient.getBaseUrl()+"/api/"+bukti);
                        }

                        RiwayatIHAdapter inhouseAdapter = new RiwayatIHAdapter(riwayatIHData, RiwayatPembayaranInHouseActivity.this);
                        recyclerView.setAdapter(inhouseAdapter);
                    } else {
                        Toast.makeText(RiwayatPembayaranInHouseActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RiwayatPembayaranInHouseActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RiwayatIHResponse> call, Throwable t) {
                Toast.makeText(RiwayatPembayaranInHouseActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
