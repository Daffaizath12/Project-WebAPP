package com.example.project_webapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
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

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;
import com.example.project_webapp.Service.PemesananService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananActivity extends AppCompatActivity {

    String[] item = {"Pinewood", "Boulevard Magnolia", "Camelia", "Edge Gardenia", "New Edge Gardenia", "Plumeria", "QBIX", "Ruko", "SOHO"};
    String[] item2 = {"InHouse", "KPR"};
    String[] item3 = {"InHouse", "KPR"};
    String[] item4 = {"InHouse", "KPR"};
    AutoCompleteTextView autoCompleteTextView, pembayaran, dp, inhouse;
    String autoCompleteTextViewvalue, pembayaranvalue, dpvalue, inhousevalue;
    ArrayAdapter<String> adapterItem, adapterItemPembayaran, getAdapterItemdp, getAdapterIteminhouse;

    EditText txttanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    ImageView backBtn;

    private static final int REQUEST_PERMISSION = 1;
    private static final int REQUEST_PICK_IMAGE = 2;

    private File selectedFile;
    TextInputEditText inputNama, inputAlamat, inputTelepon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        Button pickImageButton = findViewById(R.id.btnUpload);
        Button submitButton = findViewById(R.id.btnCheckout);
        inputNama = findViewById(R.id.inputNama);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputTelepon = findViewById(R.id.inputTelepon);

        submitButton.setOnClickListener(view -> submitOrder());

        txttanggal = (EditText) findViewById(R.id.txttanggal);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        backBtn = findViewById(R.id.backbtn);
        txttanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getIntent = getIntent();
                String idDetailcluster = getIntent.getStringExtra("idcluster");
                Intent intent = new Intent(PemesananActivity.this, DetailActivity.class);
                intent.putExtra("idcluster", idDetailcluster);
                startActivity(intent);
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
                autoCompleteTextViewvalue = adapterView.getItemAtPosition(i).toString();
            }
        });

        pembayaran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                pembayaranvalue = adapterView.getItemAtPosition(i).toString();
            }
        });
        dp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                dpvalue = adapterView.getItemAtPosition(i).toString();
            }
        });

        inhouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                inhousevalue = adapterView.getItemAtPosition(i).toString();
            }
        });
    }

    private void submitOrder() {
        if (selectedFile == null) {
            Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Melakukan proses pemesanan dengan mengirimkan file
        submitOrderWithFile(selectedFile);
    }

    private void submitOrderWithFile(File selectedFile) {

        // Mengonversi file menjadi RequestBody
        RequestBody fileRequestBody = RequestBody.create(selectedFile, MediaType.parse("multipart/form-data"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", selectedFile.getName(), fileRequestBody);

        // Mendapatkan objek ApiService
        PemesananService apiService = ApiClient.getPemesananService();

        // Melakukan request menggunakan service API
        Call<GlobalResponse> call = apiService.submitOrder(
                RequestBody.create(MediaType.parse("text/plain"), inputNama.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), inputAlamat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), inputTelepon.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), autoCompleteTextViewvalue),
                RequestBody.create(MediaType.parse("text/plain"), pembayaranvalue),
                RequestBody.create(MediaType.parse("text/plain"), txttanggal.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), dpvalue),
                RequestBody.create(MediaType.parse("text/plain"), inhousevalue),
                filePart
        );
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                if (response.isSuccessful()) {
                    GlobalResponse apiResponse = response.body();
                    // Handle respons sukses
                    Toast.makeText(PemesananActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    // Handle respons error
                    Toast.makeText(PemesananActivity.this, "Gagal melakukan pemesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Jika izin diberikan, buka galeri untuk memilih gambar
                openGallery();
            } else {
                Toast.makeText(this, "Izin akses penyimpanan ditolak", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // Mendapatkan URI gambar yang dipilih
            Uri selectedImageUri = data.getData();

            // Mendapatkan path file dari URI
            String filePath = selectedImageUri.getPath();

            if (filePath != null) {
                selectedFile = new File(filePath);
            } else {
                Toast.makeText(this, "Gagal memilih gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
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