package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.project_webapp.Adapter.ProgresAdapter;
import com.example.project_webapp.Adapter.ProgresData;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.ProgresResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgresPemesananActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgresAdapter progresAdapter;
    private List<ProgresData> progresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_pemesanan);

        recyclerView = findViewById(R.id.viewprogres);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progresList = new ArrayList<>();
        progresAdapter = new ProgresAdapter(this, progresList);
        recyclerView.setAdapter(progresAdapter);

        // Mendapatkan id_user dari Intent atau sesuai kebutuhan Anda
        String idUser = Preferences.getLoggedInToken(ProgresPemesananActivity.this);

        getProgresPemesanan(idUser);
    }

        private void getProgresPemesanan(String idUser) {
            // Mengirim request ke API dan mengambil data progres
            Call<List<ProgresResponse>> call = ApiClient.getUserService().getProgres(idUser);
            call.enqueue(new Callback<List<ProgresResponse>>() {
                @Override
                public void onResponse(Call<List<ProgresResponse>> call, Response<List<ProgresResponse>> response) {
                    if (response.isSuccessful()) {
                        List<ProgresResponse> progresResponses = response.body();
                        if (progresResponses != null) {
                            for (ProgresResponse progresResponse : progresResponses) {
                                String idPemesanan = progresResponse.getId_pemesanan();
                                String status = progresResponse.getStatus();
                                String keterangan = progresResponse.getKeterangan();
                                String foto = progresResponse.getFoto();
                                String tanggal = progresResponse.getTanggal();
                                String namaPemesan = progresResponse.getNama_pemesan();

                                ProgresData progresData = new ProgresData(idPemesanan, status, keterangan, foto, tanggal, namaPemesan);
                                progresList.add(progresData);
                            }

                            progresAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ProgresPemesananActivity.this, "Response API tidak valid", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ProgresPemesananActivity.this, "Gagal mendapatkan data progres", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<ProgresResponse>> call, Throwable t) {
                    Toast.makeText(ProgresPemesananActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
