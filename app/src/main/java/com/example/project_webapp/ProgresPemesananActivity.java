package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgresPemesananActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_pemesanan);

        recyclerView = findViewById(R.id.viewprogres);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Mendapatkan id_user dari Intent atau sesuai kebutuhan Anda
        String idUser = Preferences.getLoggedInToken(ProgresPemesananActivity.this);

        getProgresPemesanan(recyclerView, idUser);
    }

    private void getProgresPemesanan(RecyclerView recyclerView, String idUser) {
        // Mengirim request ke API dan mengambil data progres
        Call<ProgresResponse> call = ApiClient.getUserService().getProgres(idUser);
        call.enqueue(new Callback<ProgresResponse>() {
            @Override
            public void onResponse(Call<ProgresResponse> call, Response<ProgresResponse> response) {

                if (response.isSuccessful()) {
                    ProgresResponse progresResponse = response.body();
                    if (progresResponse != null) {
                        String idPemesanan = progresResponse.getIdPemesanan();
                        String status = progresResponse.getStatus();
                        String keterangan = progresResponse.getKeterangan();
                        String foto = progresResponse.getFoto();
                        String tanggal = progresResponse.getTanggal();
                        String namaPemesan = progresResponse.getNamaPemesan();

                        ProgresData progresData = new ProgresData(idPemesanan, status, keterangan, ApiClient.getImgUrl()+"/proggres/"+foto, tanggal, namaPemesan);
                        List<ProgresData> progresList = new ArrayList<>();
                        progresList.add(progresData);

                        ProgresAdapter progresAdapter = new ProgresAdapter(progresList, ProgresPemesananActivity.this);
                        recyclerView.setAdapter(progresAdapter);
                    } else {
                        Toast.makeText(ProgresPemesananActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProgresPemesananActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProgresResponse> call, Throwable t) {
                Toast.makeText(ProgresPemesananActivity.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

