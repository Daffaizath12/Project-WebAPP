package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project_webapp.Fragment.HomeFragment;
import com.example.project_webapp.Fragment.SettingFragment;
import com.example.project_webapp.Service.SharedPreference.Preferences;

public class PembayaranActivity extends AppCompatActivity {
     Button btndp, btninhouse, riwayatdp, riwayatinhouse;
     ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        btndp = findViewById(R.id.btndp);
        btninhouse = findViewById(R.id.btninhouse);
        riwayatdp = findViewById(R.id.riwayatdp);
        riwayatinhouse = findViewById(R.id.riwayatinhouse);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PembayaranActivity.this, SettingFragment.class);
                startActivity(intent);
            }
        });

        btndp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(PembayaranActivity.this);

                // Intent ke Activity lain
                Intent intent = new Intent(PembayaranActivity.this, PembayaranDpActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });

        btninhouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mendapatkan id_user secara otomatis (misalnya dari SharedPreferences atau dari sumber data lainnya)
                String idUser = Preferences.getLoggedInToken(PembayaranActivity.this);

                // Intent ke Activity lain
                Intent intent = new Intent(PembayaranActivity.this, PembayaranInHouseActivity.class);
                intent.putExtra("id_user", idUser);
                startActivity(intent);
            }
        });
    }
}