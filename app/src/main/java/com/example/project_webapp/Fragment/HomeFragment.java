package com.example.project_webapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.project_webapp.Adapter.ItemsAdapter;
import com.example.project_webapp.Adapter.ItemsDomain;
import com.example.project_webapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private RecyclerView.Adapter adapterPopuler,adapterNew;
    private RecyclerView recyclerViewPopuler, recyclerViewNew;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initRecycleView();

        return rootView;
    }

    private void initRecycleView() {
        ArrayList<ItemsDomain> ItemsArrayList = new ArrayList<>();

        ItemsArrayList.add(new ItemsDomain("New Edge Gardenia", "Menerus batu kali", "Pasangan batadi plaster Finish cat + Plamir dicat",
                "Rangka galvalum, Penutup genteng flat beton dicat","alumunium", "Rangka Hallow", "PDAM", "PLN 1300 watt",
                "2 kamar tidur", "60 m�",80000000,"pic1"));
        ItemsArrayList.add(new ItemsDomain("New Edge Gardenia", "Menerus batu kali", "Pasangan batadi plaster Finish cat + Plamir dicat",
                "Rangka galvalum, Penutup genteng flat beton dicat","alumunium", "Rangka Hallow", "PDAM", "PLN 1300 watt",
                "2 kamar tidur", "60 m�",80000000,"pic2"));
        ItemsArrayList.add(new ItemsDomain("New Edge Gardenia", "Menerus batu kali", "Pasangan batadi plaster Finish cat + Plamir dicat",
                "Rangka galvalum, Penutup genteng flat beton dicat","alumunium", "Rangka Hallow", "PDAM", "PLN 1300 watt",
                "2 kamar tidur", "60 m�",80000000,"pic2"));

        recyclerViewPopuler = rootView.findViewById(R.id.viewPopuler);
        recyclerViewNew = rootView.findViewById(R.id.viewNew);

        recyclerViewPopuler.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));

        adapterNew = new ItemsAdapter(ItemsArrayList);
        adapterPopuler = new ItemsAdapter(ItemsArrayList);

        recyclerViewPopuler.setAdapter(adapterPopuler);
        recyclerViewNew.setAdapter(adapterNew);
    }
}