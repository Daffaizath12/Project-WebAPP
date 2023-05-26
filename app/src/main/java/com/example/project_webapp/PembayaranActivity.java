package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class PembayaranActivity extends AppCompatActivity {
     Button btnBayar ;
     Button btnRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        initView();
    }

    private void initView() {
        btnBayar = findViewById(R.id.btnBayar);
        btnBayar.setOnClickListener(view -> startActivity(new Intent(PembayaranActivity.this, PembayaranDetail.class)));
    }
}