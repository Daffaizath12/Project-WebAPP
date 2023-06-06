package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.LoginRequest;
import com.example.project_webapp.Service.HTTP.LoginResponse;
import com.example.project_webapp.Service.SharedPreference.Preferences;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    TextView registerBtn;

    private Context context;
    Activity thisActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi view
        editTextEmail = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.startBtn);
        registerBtn = findViewById(R.id.register_btn);
        context = this.getBaseContext();
        thisActivity = this;

        checkPreferences(false);
        
        // Set listener untuk button login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke RegisterActivity jika pengguna belum memiliki akun
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkPreferences(boolean finish) {
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            if (finish) {
                finish();
            } else {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(editTextEmail.getText().toString());
        loginRequest.setPassword(editTextPassword.getText().toString());


        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()){
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess()) {
                        Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        Preferences.setLoggedInToken(context, loginResponse.getId());
                        Preferences.setLoggedInStatus(context, true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                thisActivity.finishAfterTransition();

                                Intent intent = new Intent(thisActivity, HomeActivity.class);
                                startActivity(intent);
                            }
                        }, 700);
                    } else {
                        Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Login Gagal.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(context, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
