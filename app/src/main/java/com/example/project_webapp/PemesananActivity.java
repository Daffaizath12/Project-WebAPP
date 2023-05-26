package com.example.project_webapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PemesananActivity extends AppCompatActivity {

    String[] item = {"Pinewood", "Boulevard Magnolia", "Camelia", "Edge Gardenia", "New Edge Gardenia", "Plumeria", "QBIX", "Ruko", "SOHO"};
    String[] item2 = {"InHouse", "KPR"};
    String[] item3 = {"InHouse", "KPR"};
    String[] item4 = {"InHouse", "KPR"};
    AutoCompleteTextView autoCompleteTextView, pembayaran, dp, inhouse;
    ArrayAdapter<String> adapterItem, adapterItemPembayaran, getAdapterItemdp, getAdapterIteminhouse;

    EditText txttanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_CAMERA = 2;

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

        dp = findViewById(R.id.uangmuka);
        getAdapterItemdp = new ArrayAdapter<String>(this,R.layout.list_cluster, item3);
        dp.setAdapter(getAdapterItemdp);

        inhouse = findViewById(R.id.inhouse);
        getAdapterIteminhouse = new ArrayAdapter<String>(this,R.layout.list_cluster, item4);
        inhouse.setAdapter(getAdapterIteminhouse);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        pembayaran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
        dp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

        inhouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
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

    public void showOptionsDialog(View view) {
        String[] options = {"Upload Gambar", "Ambil Foto"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Opsi");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openGallery();
                } else if (which == 1) {
                    openCamera();
                }
            }
        });
        builder.show();
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GALLERY) {
                Uri selectedImageUri = data.getData();
                // Lakukan tindakan yang sesuai dengan gambar yang dipilih dari galeri
                Toast.makeText(this, "Gambar berhasil dipilih dari galeri", Toast.LENGTH_SHORT).show();
            } else if (requestCode == REQUEST_CAMERA) {
                // Ambil gambar yang diambil dari kamera
                Toast.makeText(this, "Foto berhasil diambil", Toast.LENGTH_SHORT).show();
            }
        }
    }
}