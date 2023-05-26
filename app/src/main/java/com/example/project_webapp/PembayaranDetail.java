package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class PembayaranDetail extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView, inputId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_detail);
    }
}
