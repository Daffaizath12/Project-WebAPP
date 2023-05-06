package com.example.project_webapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    EditText Txtusername, Txtpassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Txtusername = (EditText) findViewById(R.id.username);
        Txtpassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.startBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Txtusername.getText().toString();
                String password = Txtpassword.getText().toString();

                // Contoh validasi username dan password
                if (username.equals("admin") && password.equals("password")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}