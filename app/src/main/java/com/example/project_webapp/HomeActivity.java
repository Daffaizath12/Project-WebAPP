package com.example.project_webapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.project_webapp.Fragment.ClusterFragment;
import com.example.project_webapp.Fragment.HomeFragment;
import com.example.project_webapp.Fragment.LayananFragment;
import com.example.project_webapp.Fragment.SettingFragment;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private int selectedTab = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        initRecycleView();


        final LinearLayout homeLayout = findViewById(R.id.homeLayout);
        final LinearLayout layananLayout = findViewById(R.id.layananLayout);
        final LinearLayout clusterLayout = findViewById(R.id.clusterLayout);
        final LinearLayout settingLayout = findViewById(R.id.settingLayout);

        final ImageView homeButton = findViewById(R.id.homeButton);
        final ImageView layananButton = findViewById(R.id.layananButton);
        final ImageView clusterButton = findViewById(R.id.clusterButton);
        final ImageView settingButton = findViewById(R.id.settingButton);

        // set home layout by default
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, HomeFragment.class, null)
                .commit();
        homeButton.setColorFilter(ContextCompat.getColor(HomeActivity.this, R.color.navbar), PorterDuff.Mode.SRC_IN);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, HomeFragment.class, null)
                        .commit();
                homeButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.navbar), PorterDuff.Mode.SRC_IN);
                // check if home is already selected or not
                if (selectedTab != 1){
                    layananButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    clusterButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    settingButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                }
                selectedTab = 1;
            }
        });


        layananLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, LayananFragment.class, null)
                        .commit();
                layananButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.navbar), PorterDuff.Mode.SRC_IN);
                // check if home is already selected or not
                if (selectedTab != 2){
                    homeButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    clusterButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    settingButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                }
                selectedTab = 2;
            }
        });

        clusterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, ClusterFragment.class, null)
                        .commit();
                clusterButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.navbar), PorterDuff.Mode.SRC_IN);
                // check if home is already selected or not
                if (selectedTab != 3){
                    layananButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    homeButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    settingButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                }
                selectedTab = 3;
            }
        });

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, SettingFragment.class, null)
                        .commit();
                settingButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.navbar), PorterDuff.Mode.SRC_IN);
                // check if home is already selected or not
                if (selectedTab != 4){
                    layananButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    clusterButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                    homeButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.white), PorterDuff.Mode.SRC_IN);
                }
                selectedTab = 4;
            }
        });
    }
}