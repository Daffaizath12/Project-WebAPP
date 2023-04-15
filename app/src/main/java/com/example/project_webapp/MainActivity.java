package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
    }
}