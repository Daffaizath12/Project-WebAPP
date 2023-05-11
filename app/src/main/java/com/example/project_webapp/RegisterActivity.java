package com.example.project_webapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.project_webapp.Config.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    private static final String URL = "http://bernadylandslawu.wsmif3a.id/api/register_api.php";

    private EditText Namalengkap, Username, Email, Password;

    Button registerBUtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Namalengkap = findViewById(R.id.nama_lengkap);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        Button registerButton = findViewById(R.id.startBtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Namalengkap.getText().toString().trim();
                String username = Username.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (!name.isEmpty() && !username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    String url = URL + "?name=" + name + "&username" + username + "&email=" + email + "&password=" + password;

                    DBHelper dbHelper = DBHelper.getInstance(RegisterActivity.this);

                    dbHelper.executeQuery(url, new DBHelper.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            if (result.equals("success")) {
                                // Registrasi berhasil, lakukan tindakan sesuai kebutuhan
                            } else {
                                // Registrasi gagal, tampilkan pesan kesalahan
                                Toast.makeText(RegisterActivity.this, "Registrasi gagal, silakan coba lagi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
