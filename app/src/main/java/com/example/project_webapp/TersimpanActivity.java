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
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.SimpanCluster;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TersimpanActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SimpanAdapter simpanAdapter;
    private List<SimpanCluster> simpanClusters;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tersimpan);

        // Inisialisasi RecyclerView dan Adapter
        recyclerView = findViewById(R.id.viewtersimpan);
        recyclerView.setLayoutManager(new LinearLayoutManager(TersimpanActivity.this));
        simpanClusters = new ArrayList<>();
        simpanAdapter = new SimpanAdapter(this, simpanClusters);
        recyclerView.setAdapter(simpanAdapter);


        // Inisialisasi SharedPreferences
        String idUser = Preferences.getLoggedInToken(TersimpanActivity.this);

        // Panggil API untuk mendapatkan data simpan cluster
        getSimpanClusters(idUser);
    }

    private void getSimpanClusters(String idUser) {
        Call<List<SimpanCluster>> call = ApiClient.getUserService().getSimpanClusters(idUser);
        call.enqueue(new Callback<List<SimpanCluster>>() {
            @Override
            public void onResponse(Call<List<SimpanCluster>> call, Response<List<SimpanCluster>> response) {
                if (response.isSuccessful()) {
                    List<SimpanCluster> responseSimpanClusters = response.body();
                    simpanClusters.clear();
                    simpanClusters.addAll(responseSimpanClusters);
                    simpanAdapter.notifyDataSetChanged();
                } else {
                    // Handle error response
                    Toast.makeText(TersimpanActivity.this, "Failed to get simpan clusters", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SimpanCluster>> call, Throwable t) {
                // Tangani kesalahan jaringan atau kegagalan permintaan
                Toast.makeText(TersimpanActivity.this, "Failed to get simpan clusters: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
