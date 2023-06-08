package com.example.project_webapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.DetailResponse;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.LoginResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;
import com.example.project_webapp.Service.HTTP.SimpanRequest;
import com.example.project_webapp.Service.SharedPreference.Preferences;
import com.example.project_webapp.Service.UserService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, pondasiTxt, dindingTxt, rangkaatapTxt, kusenTxt, plafondTxt, airTxt, listrikTxt, jumlahkamarTxt, luastanahTxt, priceTxt;
    private ImageView pic;
    private ImageButton backBtn;
    private Button buyBtn, btnsimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTxt = findViewById(R.id.titleTxt);
        pondasiTxt = findViewById(R.id.pondasiTxt);
        dindingTxt = findViewById(R.id.dindingTxt);
        rangkaatapTxt = findViewById(R.id.rangkaatapTxt);
        kusenTxt = findViewById(R.id.kusenTxt);
        plafondTxt = findViewById(R.id.plafonTxt);
        airTxt = findViewById(R.id.airTxt);
        listrikTxt = findViewById(R.id.listrikTxt);
        jumlahkamarTxt = findViewById(R.id.kamarTxt);
        luastanahTxt = findViewById(R.id.tanahTxt);
        priceTxt = findViewById(R.id.priceTxt);
        pic = findViewById(R.id.pic);
        buyBtn = findViewById(R.id.buyBtn);
        backBtn = findViewById(R.id.backbtn);
        btnsimpan = findViewById(R.id.btnsimpan);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = getIntent();
                String idDetailcluster = getIntent.getStringExtra("idcluster");
                Intent intent = new Intent(DetailActivity.this, PemesananActivity.class);
                intent.putExtra("idcluster", idDetailcluster);
                startActivity(intent);
            }
        });
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = getIntent();
                String idDetailcluster = getIntent.getStringExtra("idcluster");
                simpanCluster(Integer.valueOf(Preferences.getLoggedInToken(DetailActivity.this)), Integer.valueOf(idDetailcluster));
            }
        });
        getDetailCluster();
    }

    private void simpanCluster(int idUser, int idCluster) {
        SimpanRequest simpanRequest = new SimpanRequest();
        simpanRequest.setIdUser(String.valueOf(idUser));
        simpanRequest.setIdCluster(String.valueOf(idCluster));


        Call<GlobalResponse> call = ApiClient.getUserService().simpanCluster(simpanRequest);
        // Menjalankan permintaan secara asynchronous
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                if (response.isSuccessful()) {
                    GlobalResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        String message = apiResponse.getMessage();
                        showToast(message);
                    }
                } else {
                    showToast("Gagal menghubungi API");
                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                showToast("Kesalahan jaringan atau kesalahan lainnya");
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void getDetailCluster() {

        Intent intent = getIntent();
        String idDetailcluster = intent.getStringExtra("idcluster");


        Call<DetailResponse> detailResponseCall = ApiClient.getDetailService(DetailActivity.this).getServiceDetail(idDetailcluster);
        detailResponseCall.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {

                if (response.isSuccessful()){
                    DetailResponse detailResponse = response.body();
                    titleTxt.setText(detailResponse.getNamacluster());
                    pondasiTxt.setText(detailResponse.getPondasi());
                    dindingTxt.setText(detailResponse.getDinding());
                    rangkaatapTxt.setText(detailResponse.getRangkaatap());
                    kusenTxt.setText(detailResponse.getKusen());
                    plafondTxt.setText(detailResponse.getPlafond());
                    airTxt.setText(detailResponse.getAir());
                    listrikTxt.setText(detailResponse.getListrik());
                    jumlahkamarTxt.setText(detailResponse.getJumlahkamar());
                    luastanahTxt.setText(detailResponse.getLuastanah());
                    priceTxt.setText(detailResponse.getHarga());

                    Picasso.get().load(ApiClient.getBaseUrl()+"/img/images_cluster/"+detailResponse.getFotocluster()).into(pic);
                    }else {
                    Toast.makeText(DetailActivity.this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
