package com.example.project_webapp.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project_webapp.Adapter.ClusterAdapter;
import com.example.project_webapp.Adapter.ClusterData;
import com.example.project_webapp.R;
import com.example.project_webapp.Service.ApiClient;
import com.example.project_webapp.Service.HTTP.ClusterResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private View rootview;
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
        rootview = inflater.inflate(R.layout.fragment_cluster, container, false);

        RecyclerView recyclerViewall = rootview.findViewById(R.id.viewall);
        RecyclerView recyclerViewBoulevard = rootview.findViewById(R.id.viewBoulevard);
        RecyclerView recyclerViewCamelia = rootview.findViewById(R.id.viewCamelia);
        RecyclerView recyclerViewEdge = rootview.findViewById(R.id.viewEdge);
        RecyclerView recyclerViewNewedge = rootview.findViewById(R.id.viewNewedge);
        RecyclerView recyclerViewPinewood = rootview.findViewById(R.id.biewPinewood);
        RecyclerView recyclerViewPlumeria = rootview.findViewById(R.id.viewPlumeria);
        RecyclerView recyclerViewQbix = rootview.findViewById(R.id.viewQbix);
        RecyclerView recyclerViewRuko = rootview.findViewById(R.id.viewRuko);
        RecyclerView recyclerViewSoho = rootview.findViewById(R.id.viewSoho);

        recyclerViewall.setHasFixedSize(true);
        recyclerViewBoulevard.setHasFixedSize(true);
        recyclerViewCamelia.setHasFixedSize(true);
        recyclerViewEdge.setHasFixedSize(true);
        recyclerViewNewedge.setHasFixedSize(true);
        recyclerViewPinewood.setHasFixedSize(true);
        recyclerViewPlumeria.setHasFixedSize(true);
        recyclerViewQbix.setHasFixedSize(true);
        recyclerViewRuko.setHasFixedSize(true);
        recyclerViewSoho.setHasFixedSize(true);

        recyclerViewall.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewBoulevard.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCamelia.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewEdge.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNewedge.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPinewood.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPlumeria.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewQbix.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRuko.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewSoho.setLayoutManager(new LinearLayoutManager(rootview.getContext(), LinearLayoutManager.HORIZONTAL, false));

        getCluster(recyclerViewall);
        getCluster(recyclerViewBoulevard);
        getCluster(recyclerViewCamelia);
        getCluster(recyclerViewEdge);
        getCluster(recyclerViewNewedge);
        getCluster(recyclerViewPinewood);
        getCluster(recyclerViewPlumeria);
        getCluster(recyclerViewQbix);
        getCluster(recyclerViewRuko);
        getCluster(recyclerViewSoho);

        return rootview;
    }

    private void getCluster(RecyclerView recyclerView) {
        Call<ClusterResponse> clusterResponseCall = ApiClient.getDetailService(rootview.getContext()).getCluster();
        clusterResponseCall.enqueue(new Callback<ClusterResponse>() {
            @Override
            public void onResponse(Call<ClusterResponse> call, Response<ClusterResponse> response) {

                if (response.isSuccessful()){
                    ClusterResponse clusterResponse = response.body();
                    if (clusterResponse != null) {
                        List<ClusterResponse.Data> dataList = clusterResponse.getData();

                        // Mengubah List menjadi array ArticleData[]
                        ClusterData[] articleData = new ClusterData[dataList.size()];

                        for (int i = 0; i < dataList.size(); i++) {
                            ClusterResponse.Data data = dataList.get(i);

                            // Ambil data yang diperlukan dari objek data
                            int id = data.getId();
                            String pondasi = data.getPondasi();
                            String dinding = data.getDinding();
                            String rangkaatap = data.getRangkaatap();
                            String kusen = data.getKusen();
                            String plafond = data.getPlafond();
                            String air = data.getAir();
                            String listrik = data.getListrik();
                            String jumlahkamar = data.getJumlahkamar();
                            String luastanah = data.getLuastanah();
                            String fotocluster = data.getFotocluster();
                            String harga = data.getHarga();
                            String namacluster = data.getNamacluster();

                            // Buat objek ArticleData dan tambahkan ke array
                            articleData[i] = new ClusterData(id, pondasi, dinding, rangkaatap, kusen, plafond, air, listrik, jumlahkamar, luastanah,
                                    ApiClient.getBaseUrl()+"/img/images_cluster/"+fotocluster, harga, namacluster);
                        }

                        // Tambahkan kode untuk melakukan sesuatu dengan articleData, seperti mengatur adapter RecyclerView
                        ClusterAdapter clusterAdapter = new ClusterAdapter(articleData, rootview.getContext());
                        recyclerView.setAdapter(clusterAdapter);
                    } else {
                        Toast.makeText(rootview.getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(rootview.getContext(), "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClusterResponse> call, Throwable t) {

                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}