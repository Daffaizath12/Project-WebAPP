package com.example.project_webapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_webapp.Config.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private ProgressBar loading;
    private  TextView loginBtn;
    private static String url = "http://localhost/_LaravelProject/Project-WSI/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inisialisasi view
        loading = findViewById(R.id.loading);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextName = findViewById(R.id.username);
        buttonRegister = findViewById(R.id.startBtn);
        loginBtn = findViewById(R.id.loginbtn);

        // Set listener untuk button register
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
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

    private void register() {
        loading.setVisibility(View.VISIBLE);
        buttonRegister.setVisibility(View.GONE);

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name = editTextName.getText().toString().trim();

        // Kirim request registrasi ke server menggunakan Volley
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("success");

                            if (succes.equals("1")){
                                Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register error" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.VISIBLE);
                            buttonRegister.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error dari server saat registrasi gagal
                        // Misalnya, menampilkan pesan error pada EditText atau menampilkan dialog error
                        Toast.makeText(getApplicationContext(), "Registration failed: " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.VISIBLE);
                        buttonRegister.setVisibility(View.GONE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("name", name);
                return params;
            }
        };

        // Tambahkan request ke queue Volley
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
