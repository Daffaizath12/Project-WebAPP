package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_webapp.Adapter.ClusterAdapter;
import com.example.project_webapp.Adapter.ClusterData;

import java.util.ArrayList;

public class TersimpanActivity extends AppCompatActivity {

    private RecyclerView.Adapter adaptertersimpan;

    private RecyclerView recyclerViewtersimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tersimpan);

//        initRecycleView();
    }

//    private void initRecycleView() {
//
//        ArrayList<ClusterData> ItemsArrayList = new ArrayList<>();
//
//        ItemsArrayList.add(new ClusterData("New Edge Gardenia", "Menerus batu kali", "Pasangan batadi plaster Finish cat + Plamir dicat",
//                "Rangka galvalum, Penutup genteng flat beton dicat","alumunium", "Rangka Hallow", "PDAM", "PLN 1300 watt",
//                "2 kamar tidur", "60 m�",80000000,"pic"));
//
//        recyclerViewtersimpan = findViewById(R.id.viewtersimpan);
//
//        recyclerViewtersimpan.setLayoutManager(new LinearLayoutManager(this));
//
//        adaptertersimpan = new ClusterAdapter(ItemsArrayList);
//
//        recyclerViewtersimpan.setAdapter(adaptertersimpan);
//    }
}