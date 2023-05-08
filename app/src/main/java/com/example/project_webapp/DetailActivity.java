package com.example.project_webapp;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.project_webapp.Adapter.ItemsDomain;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTxt, addressTxt, bedTxt, bathTxt, wifiTxt, descriptionTxt, priceTxt;
    private ItemsDomain item;
    private ImageView pic;
    private Button buyBtn;


    DecimalFormat formatter = new DecimalFormat("###,###,###,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        setVarible();
    }

    private void setVarible() {
        item = (ItemsDomain) getIntent().getSerializableExtra("object");

        titleTxt.setText(item.getTitle());
        addressTxt.setText(item.getAddress());
        bedTxt.setText(item.getBed()+ "bed");
        bathTxt.setText(item.getBath()+ "bath");
        descriptionTxt.setText(item.getDescription());
        priceTxt.setText("Rp" + formatter.format(item.getPrice()));

        if(item.isWifi()){
            wifiTxt.setText("wifi");
        }else{
            wifiTxt.setText("No-Wifi");
        }
        int drawableResourceId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(pic);
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        addressTxt = findViewById(R.id.addressTxt);
        bedTxt = findViewById(R.id.bedTxt);
        bathTxt = findViewById(R.id.bathTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        pic = findViewById(R.id.pic);
        priceTxt = findViewById(R.id.priceTxt);

        buyBtn = findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(view -> startActivity(new Intent(DetailActivity.this, PemesananActivity.class)));
    }
}
