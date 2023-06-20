package com.example.project_webapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_webapp.Adapter.RiwayatPemesananAdapter;
import com.example.project_webapp.Adapter.RiwayatPemesananData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.RIwayatPemesananResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemesananActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pemesanan);

        backbtn = findViewById(R.id.backbtn);

        recyclerView = findViewById(R.id.viewriwayatpemesanan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatPemesananActivity.this, LinearLayoutManager.VERTICAL, false));


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiwayatPemesananActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        // Inisialisasi SharedPreferences
        String idUser = Preferences.getLoggedInToken(RiwayatPemesananActivity.this);

        // Panggil API untuk mendapatkan data simpan cluster
        getRiwayatPemesananDetail(idUser);
    }

    private void getRiwayatPemesananDetail(String idUser) {
        Call<RIwayatPemesananResponse> call = ApiClient.getUserService().getRiwayatPemesanan(idUser);
        call.enqueue(new Callback<RIwayatPemesananResponse>() {
            @Override
            public void onResponse(Call<RIwayatPemesananResponse> call, Response<RIwayatPemesananResponse> response) {

                if (response.isSuccessful()) {
                    RIwayatPemesananResponse riwayatPemesananResponse = response.body();
                    if (riwayatPemesananResponse != null) {
                        List<RIwayatPemesananResponse.Data> dataList = riwayatPemesananResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        RiwayatPemesananData[] riwayatPemesananData = new RiwayatPemesananData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            RIwayatPemesananResponse.Data data = dataList.get(i);
                            String namaPemesan = data.getNama_pemesan();
                            String alamat = data.getAlamat();
                            String telp = data.getTelp();
                            String namaCluster = data.getNama_cluster();
                            String tgl = data.getTgl();
                            String pembayaran = data.getPembayaran();
                            String dp = data.getDp();
                            String inhouse = data.getInhouse();
                            String blok = data.getBlok();
                            String suratbangunan = data.getSurat_bangunan();
                            String ktp = data.getKtp();

                            riwayatPemesananData[i] = new RiwayatPemesananData(namaPemesan, alamat, telp, namaCluster, tgl, pembayaran, dp, inhouse, blok, suratbangunan, ApiClient.getBaseUrl()+"/api/"+ktp);
                        }

                        RiwayatPemesananAdapter riwayatPemesananAdapter = new RiwayatPemesananAdapter(riwayatPemesananData, RiwayatPemesananActivity.this);
                        recyclerView.setAdapter(riwayatPemesananAdapter);
                    } else {
                        Toast.makeText(RiwayatPemesananActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RiwayatPemesananActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RIwayatPemesananResponse> call, Throwable t) {
                Toast.makeText(RiwayatPemesananActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
