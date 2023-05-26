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
    private TextView titleTxt, pondasiTxt, dindingTxt, rangkaatapTxt, kusenTxt, plafondTxt, airTxt, listrikTxt, jumlahkamarTxt, luastanahTxt, priceTxt;
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
        pondasiTxt.setText(item.getPondasi());
        dindingTxt.setText(item.getDinding());
        rangkaatapTxt.setText(item.getRangkaatap());
        kusenTxt.setText(item.getKusen());
        plafondTxt.setText(item.getPlafond());
        airTxt.setText(item.getAir());
        listrikTxt.setText(item.getListrik());
        jumlahkamarTxt.setText(item.getJumlahkamar());
        luastanahTxt.setText(item.getLuastanah());
        priceTxt.setText("Rp" + formatter.format(item.getPrice()));


        int drawableResourceId = getResources().getIdentifier(item.getPic(), "drawable", getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(pic);
    }

    private void initView() {
        titleTxt = findViewById(R.id.titleTxt);
        pondasiTxt = findViewById(R.id.pondasiTxt);
        dindingTxt = findViewById(R.id.dindingTxt);
        rangkaatapTxt = findViewById(R.id.rangkaatapTxt);
        kusenTxt = findViewById(R.id.kusenTxt);
        plafondTxt = findViewById(R.id.plafonTxt);
        airTxt = findViewById(R.id.airTxt);
        listrikTxt = findViewById(R.id.listrikTxt);
        jumlahkamarTxt = findViewById(R.id.kamarTxt);
        luastanahTxt = findViewById(R.id.tanahTxt);
        pic = findViewById(R.id.pic);
        priceTxt = findViewById(R.id.priceTxt);

        buyBtn = findViewById(R.id.buyBtn);
        buyBtn.setOnClickListener(view -> startActivity(new Intent(DetailActivity.this, PemesananActivity.class)));
    }
}
