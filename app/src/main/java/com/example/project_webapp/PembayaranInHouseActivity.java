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

import com.example.project_webapp.DetailActivity;
import com.example.project_webapp.R;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.PembayaranInHouseService;
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

public class PembayaranInHouseActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_pembayaran_detailinhouse);

        Button pickImageButton = findViewById(R.id.btnUpload);
        Button submitButton = findViewById(R.id.btnBayarSekarang);
        idpemesanan = findViewById(R.id.idPemesanan);
        textviewfile = findViewById(R.id.textviewfile);

        submitButton.setOnClickListener(view -> submitOrder());

        String idPemesananRumah = getIntent().getStringExtra("id_pemesanan_rumah");
        idpemesanan.setText(idPemesananRumah);

        txttanggal = findViewById(R.id.txttanggal);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        backBtn = findViewById(R.id.backbtn);
        txttanggal.setOnClickListener(view -> showDateDialog());
        backBtn.setOnClickListener(view -> {
            Intent intent = new Intent(PembayaranInHouseActivity.this, PembayaranActivity.class);
            startActivity(intent);
        });
        pickImageButton.setOnClickListener(view -> chooseImage());
    }

    private void chooseImage() {
        String[] options = {"Upload Gambar", "Ambil Foto"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih Opsi");
        builder.setItems(options, (dialog, which) -> {
            if (which == 0) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_PICK_IMAGE);
            } else if (which == 1) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
        builder.show();
    }

    private void submitOrder() {
        if (selectedFile == null) {
            Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        String idPemesananRumah = idpemesanan.getText().toString();
        String tglPembayaranInHouse = txttanggal.getText().toString();

        RequestBody requestBodyIdPemesananRumah = RequestBody.create(MediaType.parse("text/plain"), idPemesananRumah);
        RequestBody requestBodyTglPembayaranInHouse = RequestBody.create(MediaType.parse("text/plain"), tglPembayaranInHouse);

        RequestBody requestBodyBuktiPembayaranInHouse = RequestBody.create(selectedFile, MediaType.parse("image/jpeg"));
        MultipartBody.Part buktiPembayaranInHousePart = MultipartBody.Part.createFormData("bukti_pembayaran_dp", selectedFile.getName(), requestBodyBuktiPembayaranInHouse);

        PembayaranInHouseService service = ApiClient.getPembayaranInHouseService(this);
        Call<Void> call = service.submitOrder(requestBodyIdPemesananRumah, requestBodyTglPembayaranInHouse, buktiPembayaranInHousePart);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PembayaranInHouseActivity.this, "Pembayaran InHouse berhasil dikirim", Toast.LENGTH_SHORT).show();
                    // Lakukan tindakan setelah pembayaran InHouse berhasil dikirim
                } else {
                    Toast.makeText(PembayaranInHouseActivity.this, "Gagal mengirim pesanan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(PembayaranInHouseActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        datePickerDialog = new DatePickerDialog(PembayaranInHouseActivity.this, (view, year, monthOfYear, dayOfMonth) -> {
            Calendar newDate = Calendar.getInstance();
            newDate.set(year, monthOfYear, dayOfMonth);
            txttanggal.setText(dateFormatter.format(newDate.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}