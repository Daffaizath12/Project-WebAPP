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
import android.graphics.Bitmap;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;
import com.example.project_webapp.Service.PemesananService;
import com.example.project_webapp.Service.SharedPreference.Preferences;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananActivity extends AppCompatActivity {

    String[] item2 = {"DP", "Inhouse"};
    String[] item3 = {"1", "2", "3", "4"};
    String[] item4 = {"1", "2", "3", "4"};
    AutoCompleteTextView autoCompleteTextView, pembayaran, dp, inhouse;
    String autoCompleteTextViewvalue, pembayaranvalue, dpvalue, inhousevalue;
    ArrayAdapter<String> adapterItem, adapterItemPembayaran, getAdapterItemdp, getAdapterIteminhouse;

    EditText txttanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    ImageView backBtn;

    private static final int REQUEST_PICK_IMAGE = 1;
    private static final int REQUEST_CAMERA = 2;


    private File selectedFile;
    TextInputEditText inputNama, inputAlamat, inputTelepon;
    
    TextView fileNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        Button pickImageButton = findViewById(R.id.btnUpload);
        Button submitButton = findViewById(R.id.btnCheckout);
        inputNama = findViewById(R.id.inputNama);
        inputAlamat = findViewById(R.id.inputAlamat);
        inputTelepon = findViewById(R.id.inputTelepon);
        fileNameTextView = findViewById(R.id.textviewfile);

        submitButton.setOnClickListener(view -> submitOrder());

        txttanggal = (EditText) findViewById(R.id.txttanggal);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
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


        pembayaran = findViewById(R.id.pembayaran);
        adapterItemPembayaran = new ArrayAdapter<String>(this,R.layout.list_cluster, item2);
        pembayaran.setAdapter(adapterItemPembayaran);

        dp = findViewById(R.id.uangmuka);
        getAdapterItemdp = new ArrayAdapter<String>(this,R.layout.list_cluster, item3);
        dp.setAdapter(getAdapterItemdp);

        inhouse = findViewById(R.id.inhouse);
        getAdapterIteminhouse = new ArrayAdapter<String>(this,R.layout.list_cluster, item4);
        inhouse.setAdapter(getAdapterIteminhouse);

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

        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage (v);
            }
        });
    }

    private String getFileNameFromPath(String filePath) {
        // Menggunakan metode split() untuk memisahkan path berdasarkan karakter '/'
        String[] segments = filePath.split("/");
        // Mengambil segment terakhir yang merupakan nama file
        String fileName = segments[segments.length - 1];
        return fileName;
    }

    private void chooseImage(View v) {
        String[] options = {"Upload Gambar", "Ambil Foto"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Opsi");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,REQUEST_PICK_IMAGE);
                } else if (which == 1) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }
            }
        });
        builder.show();
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
        File file = new File(selectedFile.getPath());
        RequestBody fileRequestBody = RequestBody.create(file, MediaType.parse("image/jpeg"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("gambar",file.getName(), fileRequestBody);

        Intent getIntent = getIntent();
        String idDetailcluster = getIntent.getStringExtra("idcluster");

        // Mendapatkan objek ApiService
        PemesananService apiService = ApiClient.getPemesananService(PemesananActivity.this);

        // Melakukan request menggunakan service API
        Call<GlobalResponse> call = apiService.submitOrder(
                RequestBody.create(MediaType.parse("text/plain"), inputNama.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), inputAlamat.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), inputTelepon.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), idDetailcluster),
                RequestBody.create(MediaType.parse("text/plain"), pembayaranvalue),
                RequestBody.create(MediaType.parse("text/plain"), txttanggal.getText().toString()),
                RequestBody.create(MediaType.parse("text/plain"), dpvalue),
                RequestBody.create(MediaType.parse("text/plain"), inhousevalue),
                RequestBody.create(MediaType.parse("text/plain"), Preferences.getLoggedInToken(PemesananActivity.this)),
                filePart
        );
        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                if (response.isSuccessful()) {
                    GlobalResponse apiResponse = response.body();
                    // Handle respons sukses
                    Toast.makeText(PemesananActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    // Intent ke halaman home
                    Intent intent = new Intent(PemesananActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Menutup halaman pemesanan agar tidak dapat dikembalikan dengan tombol back
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_IMAGE && data != null) {
                // Mendapatkan URI dari gambar yang dipilih dari galeri
                Uri selectedImageUri = data.getData();
                try {
                    // Mengubah URI menjadi Bitmap
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    // Menampilkan gambar pada ImageView
                    selectedFile = bitmapToFile(bitmap);
                    // Mendapatkan path atau URI dari file yang diunggah
                    String filePath = selectedImageUri.getPath();
                    // Mengatur teks nama file pada TextView
                    fileNameTextView.setText(getFileNameFromPath(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA && data != null) {
                // Mendapatkan foto yang diambil menggunakan kamera
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                // Menampilkan foto pada ImageView
                selectedFile = bitmapToFile(photo);
                // Mendapatkan path atau URI dari file yang diunggah (misalnya menggunakan getAbsolutePath())
                String filePath = selectedFile.getAbsolutePath();
                // Mengatur teks nama file pada TextView
                fileNameTextView.setText(getFileNameFromPath(filePath));
            }
        }
    }
    public File bitmapToFile(Bitmap bitmap) {
        try {
            File file = new File(getCacheDir(), "temp_image.jpg");
            file.createNewFile();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bitmapData = byteArrayOutputStream.toByteArray();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bitmapData);
            fileOutputStream.flush();
            fileOutputStream.close();

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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