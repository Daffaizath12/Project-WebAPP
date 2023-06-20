package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.project_webapp.Adapter.ClusterAdapter;
import com.example.project_webapp.Adapter.ClusterData;
import com.example.project_webapp.Fragment.HomeFragment;
import com.example.project_webapp.Fragment.SettingFragment;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.ClusterResponse;
import com.example.project_webapp.Service.HTTP.PembayaranResponse;
import com.example.project_webapp.Service.HTTP.UserResponse;
import com.example.project_webapp.Service.PemesananService;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity {
     Button btndp, btninhouse, riwayatdp, riwayatinhouse;
     ImageView backbtn;
     AutoCompleteTextView pembayarandp, pembayaraninhouse;

     int idPembayaranDP, idPembayaranInHouse;
    private HashMap<String, Integer> clusterMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        btndp = findViewById(R.id.btndp);
        btninhouse = findViewById(R.id.btninhouse);
        riwayatdp = findViewById(R.id.riwayatdp);
        riwayatinhouse = findViewById(R.id.riwayatinhouse);
        backbtn = findViewById(R.id.backbtn);
        pembayarandp = findViewById(R.id.pembayarandp);
        pembayaraninhouse = findViewById(R.id.pembayaraninhouse);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembayaranActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        riwayatdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser = Preferences.getLoggedInToken(PembayaranActivity.this);

                // Intent ke Activity lain
                Intent intent = new Intent(PembayaranActivity.this, RiwayatPembayaranDpActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        riwayatinhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser = Preferences.getLoggedInToken(PembayaranActivity.this);

                // Intent ke Activity lain
                Intent intent = new Intent(PembayaranActivity.this, RiwayatPembayaranInHouseActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        btndp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembayaranActivity.this, PembayaranDpActivity.class);
                intent.putExtra("id_pemesanan_rumah", String.valueOf(idPembayaranDP));
                startActivity(intent);
            }
        });

        btninhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent ke Activity lain
                Intent intent = new Intent(PembayaranActivity.this, PembayaranInHouseActivity.class);
                intent.putExtra("id_pemesanan_rumah", String.valueOf(idPembayaranInHouse));
                startActivity(intent);
            }
        });

        // Mendapatkan ID pengguna dari SharedPreference atau intent, sesuaikan dengan kode Anda
        String idUser = Preferences.getLoggedInToken(getBaseContext());

        getPembayaranDetail(idUser);
    }

    private void getPembayaranDetail(String idUser) {
        PemesananService service = ApiClient.getPemesananService(this);
        Call<PembayaranResponse> call = service.pembayaranDetail(idUser);

        call.enqueue(new Callback<PembayaranResponse>() {
            @Override
            public void onResponse(Call<PembayaranResponse> call, Response<PembayaranResponse> response) {
                if (response.isSuccessful()) {
                    List<PembayaranResponse.Data> pembayaranList = response.body().getData();
                    List<String> clusterList = new ArrayList<>();
                    HashMap<String, Integer> clusterMap = new HashMap<>();

                    for (int i = 0; i < pembayaranList.size(); i++) {
                        PembayaranResponse.Data pembayaran = pembayaranList.get(i);
                        if (i==0){
                            idPembayaranDP = pembayaran.getId_pemesanan_rumah();
                            idPembayaranInHouse = pembayaran.getId_pemesanan_rumah();
                        }
                        String namaCluster = pembayaran.getNama_cluster();
                        int idCluster = pembayaran.getId_pemesanan_rumah();

                        // Tambahkan namaCluster ke dalam clusterList
                        clusterList.add(namaCluster);

                        // Tambahkan namaCluster dan idCluster ke dalam clusterMap
                        clusterMap.put(namaCluster, idCluster);
                    }

                    // Inisialisasi ArrayAdapter dengan clusterList
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(PembayaranActivity.this, android.R.layout.simple_dropdown_item_1line, clusterList);

                    // Mengatur adapter ke AutoCompleteTextView
                    pembayarandp.setAdapter(adapter);

                    // Mengatur adapter ke AutoCompleteTextView
                    pembayaraninhouse.setAdapter(adapter);

                    // Mengatur tindakan yang dilakukan ketika nama cluster dipilih
                    pembayarandp.setOnItemClickListener((parent, view, position, id) -> {
                        String selectedCluster = (String) parent.getItemAtPosition(position);
                        int selectedClusterId = clusterMap.get(selectedCluster);
                        // Lakukan sesuatu dengan ID cluster yang dipilih
                        idPembayaranDP = selectedClusterId;
                    });

                    // Mengatur tindakan yang dilakukan ketika nama cluster dipilih
                    pembayaraninhouse.setOnItemClickListener((parent, view, position, id) -> {
                        String selectedCluster = (String) parent.getItemAtPosition(position);
                        int selectedClusterId = clusterMap.get(selectedCluster);
                        // Lakukan sesuatu dengan ID cluster yang dipilih
                        idPembayaranInHouse = selectedClusterId;
                    });
                }
            }

            @Override
            public void onFailure(Call<PembayaranResponse> call, Throwable t) {
                // Handle failure case
            }
        });
    }
}