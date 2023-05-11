package com.example.project_webapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

import com.android.volley.VolleyError;
import com.example.project_webapp.Config.DBHelper;

public class LoginActivity extends AppCompatActivity {

    private static final String URL = "http://bernadylandslawu.wsmif3a.id/api/login_api.php";

    EditText Username, Password;
    Button startBtn;
    private TextView registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        TextView registerbtn = findViewById(R.id.register_btn);
        Button startBtn = findViewById(R.id.startBtn);



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Username.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    String url = URL + "?email=" + email + "&password=" + password;

                    DBHelper dbHelper = DBHelper.getInstance(LoginActivity.this);

                    dbHelper.executeQuery(url, new DBHelper.VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            if (result.equals("success")) {
                                // Login berhasil, lakukan tindakan sesuai kebutuhan
                            } else {
                                // Login gagal, tampilkan pesan kesalahan
                                Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}