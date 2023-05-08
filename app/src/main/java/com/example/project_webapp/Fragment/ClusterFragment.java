package com.example.project_webapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project_webapp.Adapter.ItemsAdapter;
import com.example.project_webapp.Adapter.ItemsDomain;
import com.example.project_webapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClusterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClusterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;

    private RecyclerView.Adapter adapterAll, adapterBoulevard, adapterCamelia, adapterEdge, adapterNewedge, adapterPinewood, adapterPlumeria, adapterQbix, adapterRuko, adapterSoho;

    private RecyclerView recyclerViewall, recyclerViewBoulevard, recyclerViewCamelia, recyclerViewEdge, recyclerViewNewedge, recyclerViewPinewood, recyclerViewPlumeria, recyclerViewQbix, recyclerViewRuko, recyclerViewSoho;

    public ClusterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClusterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClusterFragment newInstance(String param1, String param2) {
        ClusterFragment fragment = new ClusterFragment();
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
        rootView = inflater.inflate(R.layout.fragment_cluster, container, false);

        initRecycleView();

        return rootView;
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

        recyclerViewall = rootView.findViewById(R.id.viewall);
        recyclerViewBoulevard = rootView.findViewById(R.id.viewBoulevard);
        recyclerViewCamelia = rootView.findViewById(R.id.viewCamelia);
        recyclerViewEdge = rootView.findViewById(R.id.viewEdge);
        recyclerViewNewedge = rootView.findViewById(R.id.viewNewedge);
        recyclerViewPinewood = rootView.findViewById(R.id.biewPinewood);
        recyclerViewPlumeria = rootView.findViewById(R.id.viewPlumeria);
        recyclerViewQbix = rootView.findViewById(R.id.viewQbix);
        recyclerViewRuko = rootView.findViewById(R.id.viewRuko);
        recyclerViewSoho = rootView.findViewById(R.id.viewSoho);

        recyclerViewall.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewBoulevard.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCamelia.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewEdge.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewedge.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPinewood.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPlumeria.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewQbix.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRuko.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSoho.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.HORIZONTAL, false));


        adapterAll = new ItemsAdapter(ItemsArrayList);
        adapterBoulevard = new ItemsAdapter(ItemsArrayList);
        adapterCamelia = new ItemsAdapter(ItemsArrayList);
        adapterEdge = new ItemsAdapter(ItemsArrayList);
        adapterNewedge = new ItemsAdapter(ItemsArrayList);
        adapterPinewood = new ItemsAdapter(ItemsArrayList);
        adapterPlumeria = new ItemsAdapter(ItemsArrayList);
        adapterQbix = new ItemsAdapter(ItemsArrayList);
        adapterRuko = new ItemsAdapter(ItemsArrayList);
        adapterSoho = new ItemsAdapter(ItemsArrayList);


        recyclerViewall.setAdapter(adapterAll);
        recyclerViewBoulevard.setAdapter(adapterBoulevard);
        recyclerViewCamelia.setAdapter(adapterCamelia);
        recyclerViewEdge.setAdapter(adapterEdge);
        recyclerViewNewedge.setAdapter(adapterNewedge);
        recyclerViewPinewood.setAdapter(adapterPinewood);
        recyclerViewPlumeria.setAdapter(adapterPlumeria);
        recyclerViewQbix.setAdapter(adapterQbix);
        recyclerViewRuko.setAdapter(adapterRuko);
        recyclerViewSoho.setAdapter(adapterSoho);

    }
}