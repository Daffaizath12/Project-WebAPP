package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.example.project_webapp.RegisterActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

//    lateinit var binding: RegisterActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        binding.textToRegister.setOnClickListener {
//            Intent(Intent(this, RegisterActivity::class.java))
//        }
    }
}