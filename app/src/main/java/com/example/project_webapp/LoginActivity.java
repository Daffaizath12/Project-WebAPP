package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompact.app.AppCompactActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 10;
    EditText username, password;
    Button btnLogin, exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameKey = username.getText().toString();
                String passwordKey = password.getText().toString();
                if (usernameKey.equals("appank") && passwordKey.equals("1")){
                    //jika login berhasil
                    Toast.makeText(getApplicationContext(), "Login Berhasil",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //  MainActivity.this.startActivity(intent); aktifkan jika menghapus bagian bawah
                    // Data username di ambil menuju ke activity2
                    String value = username.getText().toString();
                    intent.putExtra("yourkey1", value);
                    startActivityForResult(intent, REQUEST_CODE);


                }else {
                    //jika login gagal
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Username atau Password Anda salah!")
                            .setNegativeButton("Ulangi", null).create().show();
                }
            }
        });
    }


}
    }
}