package com.example.project_webapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.GlobalResponse;
import com.example.project_webapp.Service.HTTP.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private  TextView loginBtn;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi view
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextName = findViewById(R.id.username);
        buttonRegister = findViewById(R.id.startBtn);
        loginBtn = findViewById(R.id.loginbtn);
        context = this.getBaseContext();

        // Set listener untuk button register
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke RegisterActivity jika pengguna belum memiliki akun
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void register(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setNama(editTextName.getText().toString());
        registerRequest.setEmail(editTextEmail.getText().toString());
        registerRequest.setPassword(editTextPassword.getText().toString());
        Toast.makeText(context, registerRequest.getEmail(), Toast.LENGTH_SHORT).show();

        Call<GlobalResponse> globalResponseCall = ApiClient.getUserService().userRegister(registerRequest);
        globalResponseCall.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {

                if (response.isSuccessful()){
                    GlobalResponse globalResponse = response.body();

                    if (!globalResponse.isError()) {
                        Toast.makeText(context, globalResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Activity thisActivity = RegisterActivity.this;
                                thisActivity.finishAfterTransition();

                                Intent intent = new Intent(thisActivity, LoginActivity.class);
                                startActivity(intent);
                            }
                        }, 700);
                    } else {
                        Toast.makeText(context, globalResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Register Gagal.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                Toast.makeText(context, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
