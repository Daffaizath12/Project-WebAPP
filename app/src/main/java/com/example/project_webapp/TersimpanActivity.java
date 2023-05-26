package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.project_webapp.Adapter.ItemsAdapter;
import com.example.project_webapp.Adapter.ItemsDomain;

import java.util.ArrayList;

public class TersimpanActivity extends AppCompatActivity {

    private RecyclerView.Adapter adaptertersimpan;

    private RecyclerView recyclerViewtersimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tersimpan);

        initRecycleView();
    }

    private void initRecycleView() {

        ArrayList<ItemsDomain> ItemsArrayList = new ArrayList<>();

        ItemsArrayList.add(new ItemsDomain("House with a greate view", "San Frasisco, CA 94110", "This 2 Bed /1 bath home boasts an enormous, \n" +
                "open-living plan, accented by striking \n"+
                "Architectural features and high-end finishes \n" +
                "Feel inspired by open sight line thats \n" +
                "embrace the outdoors, crowned by stunning \n" +
                "coffered ceilings. ", 2,1, 1950000,"pic1", true));
        ItemsArrayList.add(new ItemsDomain("House with a greate view", "San Frasisco, CA 94110", "This 2 Bed /1 bath home boasts an enormous, \n" +
                "open-living plan, accented by striking \n"+
                "Architectural features and high-end finishes \n" +
                "Feel inspired by open sight line thats \n" +
                "embrace the outdoors, crowned by stunning \n" +
                "coffered ceilings. ", 3,1, 2950000,"pic2", false));
        ItemsArrayList.add(new ItemsDomain("House with a greate view", "San Frasisco, CA 94110", "This 2 Bed /1 bath home boasts an enormous, \n" +
                "open-living plan, accented by striking \n"+
                "Architectural features and high-end finishes \n" +
                "Feel inspired by open sight line thats \n" +
                "embrace the outdoors, crowned by stunning \n" +
                "coffered ceilings. ", 3,1, 3950000,"pic2", false));

        recyclerViewtersimpan = findViewById(R.id.viewtersimpan);

        recyclerViewtersimpan.setLayoutManager(new LinearLayoutManager(this));

        adaptertersimpan = new ItemsAdapter(ItemsArrayList);

        recyclerViewtersimpan.setAdapter(adaptertersimpan);
    }
}