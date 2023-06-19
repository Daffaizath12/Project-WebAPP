package com.example.project_webapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.PembayaranDpService;
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

public class PembayaranDpActivity extends AppCompatActivity {

    EditText txttanggal;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    ImageView backBtn;
    TextView textviewfile, idpemesanan;
    private static final int REQUEST_PICK_IMAGE = 1;
    private static final int REQUEST_CAMERA = 2;

    private File selectedFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_detaildp);

        Button pickImageButton = findViewById(R.id.btnUpload);
        Button submitButton = findViewById(R.id.btnBayarSekarang);
        idpemesanan = findViewById(R.id.idPemesanan);
        textviewfile = findViewById(R.id.textviewfile);

        submitButton.setOnClickListener(view -> submitOrder());

        // Mendapatkan id_pemesanan_rumah dari intent
        String idPemesananRumah = getIntent().getStringExtra("id_pemesanan_rumah");

        // Menampilkan id_pemesanan_rumah pada TextView
        idpemesanan.setText(idPemesananRumah);

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
                Intent intent = new Intent(PembayaranDpActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(v);
            }
        });
    }

    private String getFileNameFromPath(String filePath) {
        String[] segments = filePath.split("/");
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
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_PICK_IMAGE);
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

        submitOrderWithFile(selectedFile);
    }

    private void submitOrderWithFile(File selectedFile) {
        RequestBody idPemesananRumah = RequestBody.create(MediaType.parse("text/plain"),idpemesanan.getText().toString());
        RequestBody tglPembayaranDp = RequestBody.create(MediaType.parse("text/plain"), txttanggal.getText().toString());

        File file = new File(selectedFile.getPath());
        RequestBody fileRequestBody = RequestBody.create(file, MediaType.parse("image/jpeg"));
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("bukti_pembayaran_dp", file.getName(), fileRequestBody);

        PembayaranDpService service = ApiClient.getPembayaranService(PembayaranDpActivity.this);
        Call<GlobalResponse> call = service.submitOrder(idPemesananRumah, tglPembayaranDp, filePart);

        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {
                if (response.isSuccessful()) {
                    GlobalResponse globalResponse = response.body();
                    if (globalResponse != null) {
                        Toast.makeText(PembayaranDpActivity.this, globalResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PembayaranDpActivity.this, "Gagal mengirim pesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                Toast.makeText(PembayaranDpActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_IMAGE && data != null) {
                Uri selectedImageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    selectedFile = createImageFile(bitmap);
                    textviewfile.setText(selectedFile.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA && data != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                selectedFile = createImageFile(bitmap);
                textviewfile.setText(selectedFile.getName());
            }
        }
    }

    private File createImageFile(Bitmap bitmap) {
        File filesDir = getApplicationContext().getFilesDir();
        File imageFile = new File(filesDir, "image.jpg");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        byte[] bitmapData = bos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageFile;
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(PembayaranDpActivity.this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txttanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}
