package com.example.project_webapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class PemesananActivity extends AppCompatActivity {

    String[] item = {"Pinewood", "Boulevard Magnolia", "Camelia", "Edge Gardenia", "New Edge Gardenia", "Plumeria", "QBIX", "Ruko", "SOHO"};
    String[] item2 = {"InHouse", "KPR"};
    AutoCompleteTextView autoCompleteTextView, pembayaran;
    ArrayAdapter<String> adapterItem, adapterItemPembayaran;

    Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);


        autoCompleteTextView = findViewById(R.id.auto_complete_text);
        adapterItem = new ArrayAdapter<String>(this,R.layout.list_cluster, item);
        autoCompleteTextView.setAdapter(adapterItem);

        pembayaran = findViewById(R.id.pembayaran);
        adapterItemPembayaran = new ArrayAdapter<String>(this,R.layout.list_cluster, item2);
        pembayaran.setAdapter(adapterItemPembayaran);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(PemesananActivity.this, "item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        pembayaran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(PemesananActivity.this, "item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}