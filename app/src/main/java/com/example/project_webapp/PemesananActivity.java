package com.example.project_webapp;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PemesananActivity extends AppCompatActivity {

    String[] item = {"Pinewood", "Boulevard Magnolia", "Camelia", "Edge Gardenia", "New Edge Gardenia", "Plumeria", "QBIX", "Ruko", "SOHO"};
    String[] item2 = {"InHouse", "KPR"};
    AutoCompleteTextView autoCompleteTextView, pembayaran;
    ArrayAdapter<String> adapterItem, adapterItemPembayaran;

    EditText txttanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        txttanggal = (EditText) findViewById(R.id.txttanggal);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        txttanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


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

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                txttanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}